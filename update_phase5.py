import urllib.request
import re
import json
import os
import time

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images"

new_cars_def = [
    {
        "id": "c17",
        "name": "Honda Brio Satya E CVT",
        "brand": "Honda",
        "price_range": "Rp 150 Juta - Rp 180 Juta",
        "description": "Mobil perkotaan lincah, sangat irit bahan bakar dan desain yang sporty cocok untuk kawula muda.",
        "year": "2020",
        "tags": ["LCGC", "Irit", "Hatchback"],
        "seats": "5 Seater",
        "transmission": "Automatic (AT)",
        "fuel_type": "Bensin",
        "mileage": "20,000 km",
        "location": "Jakarta Selatan"
    },
    {
        "id": "c18",
        "name": "Toyota Kijang Innova Reborn 2.4 G",
        "brand": "Toyota",
        "price_range": "Rp 320 Juta - Rp 380 Juta",
        "description": "MPV andalan keluarga Indonesia, mesin diesel tangguh, kabin sangat lega dan nyaman untuk perjalanan jauh.",
        "year": "2019",
        "tags": ["MPV", "Keluarga", "Diesel"],
        "seats": "7 Seater",
        "transmission": "Automatic (AT)",
        "fuel_type": "Diesel",
        "mileage": "60,000 km",
        "location": "Surabaya"
    },
    {
        "id": "c19",
        "name": "Honda CR-V 1.5L Turbo",
        "brand": "Honda",
        "price_range": "Rp 400 Juta - Rp 480 Juta",
        "description": "SUV premium dengan performa buas berkat turbo, desain mewah, dan fitur keselamatan yang mumpuni.",
        "year": "2021",
        "tags": ["SUV", "Turbo", "Premium"],
        "seats": "7 Seater",
        "transmission": "Automatic (AT)",
        "fuel_type": "Bensin",
        "mileage": "30,000 km",
        "location": "Jakarta Barat"
    },
    {
        "id": "c20",
        "name": "Suzuki All New Ertiga GL",
        "brand": "Suzuki",
        "price_range": "Rp 180 Juta - Rp 210 Juta",
        "description": "LMPV ternyaman di kelasnya dengan suspensi empuk, irit bensin dan biaya perawatan sangat terjangkau.",
        "year": "2020",
        "tags": ["LMPV", "Keluarga", "Nyaman"],
        "seats": "7 Seater",
        "transmission": "Manual (MT)",
        "fuel_type": "Bensin",
        "mileage": "45,000 km",
        "location": "Tangerang"
    },
    {
        "id": "c21",
        "name": "Toyota Agya 1.2 G TRD",
        "brand": "Toyota",
        "price_range": "Rp 120 Juta - Rp 140 Juta",
        "description": "City car andalan Toyota, lincah di kemacetan, irit BBM, dan perawatannya sangat mudah.",
        "year": "2019",
        "tags": ["LCGC", "Irit", "City Car"],
        "seats": "5 Seater",
        "transmission": "Automatic (AT)",
        "fuel_type": "Bensin",
        "mileage": "50,000 km",
        "location": "Bandung"
    },
    {
        "id": "c22",
        "name": "Daihatsu Ayla 1.2 R",
        "brand": "Daihatsu",
        "price_range": "Rp 115 Juta - Rp 135 Juta",
        "description": "Kembaran Agya dengan harga lebih terjangkau namun tetap menawarkan kenyamanan dan efisiensi tinggi.",
        "year": "2019",
        "tags": ["LCGC", "Irit", "City Car"],
        "seats": "5 Seater",
        "transmission": "Manual (MT)",
        "fuel_type": "Bensin",
        "mileage": "52,000 km",
        "location": "Bekasi"
    }
]

urls_to_scrape = {
    "c17": "https://www.oto.com/mobil-baru/honda/brio/gambar",
    "c18": "https://www.oto.com/mobil-baru/toyota/kijang-innova/gambar",
    "c19": "https://www.oto.com/mobil-baru/honda/crv/gambar",
    "c20": "https://www.oto.com/mobil-baru/suzuki/ertiga/gambar",
    "c21": "https://www.oto.com/mobil-baru/toyota/agya/gambar",
    "c22": "https://www.oto.com/mobil-baru/daihatsu/ayla/gambar"
}

with open('config.json', 'r') as f:
    data = json.load(f)

# 1. Add seats to existing cars
five_seaters = ["brio", "agya", "ayla", "hr-v", "creta"]
for car in data.get('used_cars', []):
    if 'seats' not in car:
        name_lower = car['name'].lower()
        if any(fs in name_lower for fs in five_seaters):
            car['seats'] = "5 Seater"
        else:
            car['seats'] = "7 Seater"

# 2. Append new cars
existing_ids = {c['id'] for c in data.get('used_cars', [])}
for nc in new_cars_def:
    if nc['id'] not in existing_ids:
        nc['image_urls'] = []
        data['used_cars'].append(nc)

# 3. Scrape images for new cars
for car in data.get('used_cars', []):
    car_id = car['id']
    if car_id in urls_to_scrape and len(car.get('image_urls', [])) == 0:
        req = urllib.request.Request(urls_to_scrape[car_id], headers={'User-Agent': 'Mozilla/5.0'})
        try:
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
print("Phase 5 Config updated!")
