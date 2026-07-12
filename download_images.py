import json
import os
import urllib.request
import time

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images"

os.makedirs('images', exist_ok=True)

with open('config.json', 'r') as f:
    data = json.load(f)

for car in data.get('used_cars', []):
    new_urls = []
    for idx, url in enumerate(car.get('image_urls', [])):
        # Skip if already a raw.githubusercontent URL
        if repo_raw_base in url:
            new_urls.append(url)
            continue
            
        ext = url.split('.')[-1][:4].split('%')[0].split('?')[0]
        if ext.lower() not in ['jpg', 'jpeg', 'png']:
            ext = 'jpg'
            
        filename = f"{car['id']}_{idx}.{ext}"
        filepath = os.path.join('images', filename)
        
        try:
            req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
            with urllib.request.urlopen(req, timeout=15) as response, open(filepath, 'wb') as out_file:
                out_file.write(response.read())
            
            new_url = f"{repo_raw_base}/{filename}"
            new_urls.append(new_url)
            print(f"Downloaded {filename}")
            time.sleep(0.5)
        except Exception as e:
            print(f"Failed to download {url}: {e}")
            new_urls.append(url) # keep old url if failed
            
    car['image_urls'] = new_urls

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
