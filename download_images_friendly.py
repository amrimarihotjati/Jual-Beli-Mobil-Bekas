import json
import os
import urllib.request
import time
import ssl

ssl._create_default_https_context = ssl._create_unverified_context
repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images"
os.makedirs('images', exist_ok=True)

with open('config.json', 'r') as f:
    data = json.load(f)

for car in data.get('used_cars', []):
    new_urls = []
    for idx, url in enumerate(car.get('image_urls', [])):
        if repo_raw_base in url:
            new_urls.append(url)
            continue
            
        ext = url.split('.')[-1][:4].split('%')[0].split('?')[0]
        if ext.lower() not in ['jpg', 'jpeg', 'png', 'webp']:
            ext = 'jpg'
            
        filename = f"{car['id']}_{idx}.{ext}"
        filepath = os.path.join('images', filename)
        
        try:
            # Wikimedia requires contact info in User-Agent
            headers = {
                'User-Agent': 'CarScraperBot/1.0 (mailto:amrimarihotjati@gmail.com)'
            }
            req = urllib.request.Request(url, headers=headers)
            with urllib.request.urlopen(req, timeout=10) as response, open(filepath, 'wb') as out_file:
                out_file.write(response.read())
            
            new_url = f"{repo_raw_base}/{filename}"
            new_urls.append(new_url)
            print(f"Success: {filename}")
            time.sleep(1) # Be nice to servers
        except Exception as e:
            print(f"Failed: {url} -> {e}")
            new_urls.append(url)
            
    car['image_urls'] = new_urls

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
