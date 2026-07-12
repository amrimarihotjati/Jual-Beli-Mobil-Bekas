import urllib.request
import re
import json
import os
import time

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images"

def slugify(text):
    text = text.lower()
    text = text.replace(' (turbo)', '').replace(' hatchback', '').replace(' (double cabin)', '').replace(' (minibus)', '')
    text = text.replace(' ', '-')
    text = text.replace('cr-v', 'crv').replace('br-v', 'brv').replace('hr-v', 'hrv')
    return text

with open('config.json', 'r') as f:
    data = json.load(f)

for car in data.get('used_cars', []):
    if 'wikimedia' in car.get('image_urls', [''])[0]:
        car_id = car['id']
        brand = car['brand'].lower()
        model = car['name'].replace(car['brand'], '').strip()
        model_slug = slugify(model)
        
        # Override some tricky ones
        if "kijang innova zenix" in model.lower(): model_slug = "kijang-innova-zenix"
        elif "confero" in model.lower(): model_slug = "confero"
        
        url = f"https://www.oto.com/mobil-baru/{brand}/{model_slug}/gambar"
        
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        try:
            print(f"Scraping {url} for {car_id} ({car['name']})...")
            html = urllib.request.urlopen(req).read().decode('utf-8')
            matches = re.findall(r'https://imgcdn.oto.com/large/[^"\']*?\.jpg', html)
            unique_urls = list(dict.fromkeys(matches))
            
            if not unique_urls:
                continue
                
            perspectives = {}
            for u in unique_urls:
                if 'front' in u or 'grille' in u: perspectives.setdefault('front', []).append(u)
                elif 'rear' in u or 'tail' in u: perspectives.setdefault('rear', []).append(u)
                elif 'side' in u or 'wheel' in u: perspectives.setdefault('side', []).append(u)
                elif 'interior' in u or 'dashboard' in u or 'seat' in u or 'steering' in u: perspectives.setdefault('interior', []).append(u)
                else: perspectives.setdefault('other', []).append(u)
            
            selected_urls = []
            if 'front' in perspectives and perspectives['front']: selected_urls.append(perspectives['front'][0])
            if 'rear' in perspectives and perspectives['rear']: selected_urls.append(perspectives['rear'][0])
            if 'interior' in perspectives and perspectives['interior']: selected_urls.append(perspectives['interior'][0])
            if len(selected_urls) < 3 and 'side' in perspectives and perspectives['side']: selected_urls.append(perspectives['side'][0])
            
            for u in unique_urls:
                if len(selected_urls) >= 3: break
                if u not in selected_urls: selected_urls.append(u)
                
            new_urls = []
            if not os.path.exists('images'): os.makedirs('images')

            for idx, dl_url in enumerate(selected_urls[:3]):
                filename = f"{car_id}_{idx}.jpg"
                filepath = os.path.join('images', filename)
                req_img = urllib.request.Request(dl_url, headers={'User-Agent': 'Mozilla/5.0'})
                try:
                    with urllib.request.urlopen(req_img, timeout=10) as response, open(filepath, 'wb') as out_file:
                        out_file.write(response.read())
                    if os.path.getsize(filepath) > 2000:
                        new_urls.append(f"{repo_raw_base}/{filename}")
                except Exception:
                    pass
                time.sleep(0.5)
            
            if new_urls:
                car['image_urls'] = new_urls
                print(f"--> Success {car_id} : {len(new_urls)} images")
            
        except Exception as e:
            print(f"Error for {car_id}: {e}")

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
print("Config updated!")
