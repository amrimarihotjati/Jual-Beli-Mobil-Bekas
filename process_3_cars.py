import json
import os
import urllib.request
import time

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images"
os.makedirs('images', exist_ok=True)

with open('oto_urls.json', 'r') as f:
    oto_urls = json.load(f)

with open('config.json', 'r') as f:
    data = json.load(f)

for car in data.get('used_cars', []):
    car_id = car['id']
    if car_id in oto_urls:
        new_urls = []
        for idx, url in enumerate(oto_urls[car_id]):
            filename = f"{car_id}_{idx}.jpg"
            filepath = os.path.join('images', filename)
            
            try:
                print(f"Downloading {url} to {filepath}...")
                req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
                with urllib.request.urlopen(req) as response, open(filepath, 'wb') as out_file:
                    out_file.write(response.read())
                
                # Check file size to ensure it's not a dummy 1kb file
                if os.path.getsize(filepath) > 2000:
                    new_urls.append(f"{repo_raw_base}/{filename}")
                    print(f"Success: {filename}")
                else:
                    print(f"File too small, keeping original")
                    new_urls.append(car['image_urls'][idx] if idx < len(car['image_urls']) else url)
            except Exception as e:
                print(f"Error downloading {url}: {e}")
                new_urls.append(car['image_urls'][idx] if idx < len(car['image_urls']) else url)
                
            time.sleep(1)
            
        car['image_urls'] = new_urls

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
