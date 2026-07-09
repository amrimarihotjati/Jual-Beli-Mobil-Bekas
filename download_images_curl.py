import json
import os
import subprocess
import time

repo_raw_base = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images"
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
            # Use curl via subprocess with a standard browser User-Agent
            print(f"Downloading {filename}...")
            result = subprocess.run([
                'curl', '-s', '-L',
                '-H', 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
                '-o', filepath,
                url
            ], capture_output=True)
            
            if result.returncode == 0 and os.path.getsize(filepath) > 1000: # Ensure it didn't download an HTML error page
                new_url = f"{repo_raw_base}/{filename}"
                new_urls.append(new_url)
                print(f"Success: {filename}")
            else:
                print(f"Failed to download valid image for {url}")
                new_urls.append(url)
                if os.path.exists(filepath):
                    os.remove(filepath)
            
            time.sleep(2) # 2 seconds delay to avoid 429
        except Exception as e:
            print(f"Error: {e}")
            new_urls.append(url)
            
    car['image_urls'] = new_urls

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
