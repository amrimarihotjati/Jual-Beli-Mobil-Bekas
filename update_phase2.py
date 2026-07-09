import urllib.request
import re
import json
import os
import time
import random

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images"

urls_to_scrape = {
    "c4": "https://www.oto.com/mobil-baru/mitsubishi/xpander/gambar",
    "c5": "https://www.oto.com/mobil-baru/daihatsu/sigra/gambar",
    "c6": "https://www.oto.com/mobil-baru/toyota/fortuner/gambar",
    "c7": "https://www.oto.com/mobil-baru/honda/hrv/gambar",
    "c8": "https://www.oto.com/mobil-baru/mitsubishi/pajero-sport/gambar"
}

with open('config.json', 'r') as f:
    data = json.load(f)

# Mock Data generators
locations = ["Jakarta Selatan", "Jakarta Barat", "Tangerang", "Surabaya", "Bandung", "Bekasi"]
transmissions = ["Automatic (AT)", "Manual (MT)"]
fuel_types = {"Toyota": "Bensin", "Honda": "Bensin", "Mitsubishi": "Bensin/Diesel", "Daihatsu": "Bensin", "Suzuki": "Bensin"}

def get_fuel(brand):
    if brand == "Toyota Fortuner (VRZ)" or brand == "Mitsubishi Pajero Sport":
        return "Diesel"
    return fuel_types.get(brand, "Bensin")

for car in data.get('used_cars', []):
    car_id = car['id']
    
    # Inject new details if not present
    if 'transmission' not in car:
        car['transmission'] = random.choice(transmissions)
    if 'fuel_type' not in car:
        car['fuel_type'] = get_fuel(car.get('brand', 'Toyota'))
    if 'mileage' not in car:
        # Mileage between 10k to 90k km based on year
        car['mileage'] = f"{random.randint(15, 85)},000 km"
    if 'location' not in car:
        car['location'] = random.choice(locations)

    if car_id in urls_to_scrape:
        req = urllib.request.Request(urls_to_scrape[car_id], headers={'User-Agent': 'Mozilla/5.0'})
        try:
            html = urllib.request.urlopen(req).read().decode('utf-8')
            matches = re.findall(r'https://imgcdn.oto.com/large/[^"\']*?\.jpg', html)
            unique_urls = list(dict.fromkeys(matches))
            
            perspectives = {}
            for u in unique_urls:
                if 'front' in u or 'grille' in u:
                    perspectives.setdefault('front', []).append(u)
                elif 'rear' in u or 'tail' in u:
                    perspectives.setdefault('rear', []).append(u)
                elif 'side' in u or 'wheel' in u:
                    perspectives.setdefault('side', []).append(u)
                elif 'interior' in u or 'dashboard' in u or 'seat' in u or 'steering' in u:
                    perspectives.setdefault('interior', []).append(u)
                else:
                    perspectives.setdefault('other', []).append(u)
            
            selected_urls = []
            if 'front' in perspectives and perspectives['front']: selected_urls.append(perspectives['front'][0])
            if 'rear' in perspectives and perspectives['rear']: selected_urls.append(perspectives['rear'][0])
            if 'interior' in perspectives and perspectives['interior']: selected_urls.append(perspectives['interior'][0])
            
            if len(selected_urls) < 3 and 'side' in perspectives and perspectives['side']:
                selected_urls.append(perspectives['side'][0])
            
            for u in unique_urls:
                if len(selected_urls) >= 3: break
                if u not in selected_urls: selected_urls.append(u)
                
            new_urls = []
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
            
            car['image_urls'] = new_urls
            
        except Exception as e:
            print(f"Error for {car_id}: {e}")

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
print("Config updated!")
