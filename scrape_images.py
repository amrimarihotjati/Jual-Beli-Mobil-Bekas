import urllib.request
import re
import json
import os
import time

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images"

urls_to_scrape = {
    "c7": "https://www.oto.com/mobil-baru/honda/hrv/gambar",
    "c9": "https://www.oto.com/mobil-baru/toyota/rush/gambar",
    "c10": "https://www.oto.com/mobil-baru/suzuki/ertiga/gambar",
    "c11": "https://www.oto.com/mobil-baru/daihatsu/ayla/gambar",
    "c12": "https://www.oto.com/mobil-baru/toyota/calya/gambar",
    "c13": "https://www.oto.com/mobil-baru/suzuki/xl7/gambar",
    "c14": "https://www.oto.com/mobil-baru/honda/brv/gambar",
    "c15": "https://www.oto.com/mobil-baru/toyota/agya/gambar",
    "c16": "https://www.oto.com/mobil-baru/hyundai/creta/gambar",
    "c17": "https://www.oto.com/mobil-baru/hyundai/stargazer/gambar"
}

with open('config.json', 'r') as f:
    data = json.load(f)

for car in data.get('used_cars', []):
    car_id = car['id']
    if car_id in urls_to_scrape:
        req = urllib.request.Request(urls_to_scrape[car_id], headers={'User-Agent': 'Mozilla/5.0'})
        try:
            print(f"Scraping for {car_id} ({car['name']})...")
            html = urllib.request.urlopen(req).read().decode('utf-8')
            matches = re.findall(r'https://imgcdn.oto.com/large/[^"\']*?\.jpg', html)
            unique_urls = list(dict.fromkeys(matches))
            
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

            for idx, url in enumerate(selected_urls[:3]):
                filename = f"{car_id}_{idx}.jpg"
                filepath = os.path.join('images', filename)
                print(f"Downloading {url} to {filepath}...")
                req_img = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
                with urllib.request.urlopen(req_img) as response, open(filepath, 'wb') as out_file:
                    out_file.write(response.read())
                
                if os.path.getsize(filepath) > 2000:
                    new_urls.append(f"{repo_raw_base}/{filename}")
                time.sleep(0.5)
            
            if new_urls:
                car['image_urls'] = new_urls
            
        except Exception as e:
            print(f"Error for {car_id}: {e}")

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
print("Config updated!")
