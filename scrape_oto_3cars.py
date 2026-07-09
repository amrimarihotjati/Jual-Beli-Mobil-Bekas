import urllib.request
import re
import json

urls_to_scrape = {
    "c1": "https://www.oto.com/mobil-baru/toyota/avanza/gambar",
    "c2": "https://www.oto.com/mobil-baru/honda/brio/gambar",
    "c3": "https://www.oto.com/mobil-baru/toyota/kijang-innova/gambar"
}

car_images = {}

for car_id, url in urls_to_scrape.items():
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        html = urllib.request.urlopen(req).read().decode('utf-8')
        # Find all jpgs from imgcdn.oto.com
        matches = re.findall(r'https://imgcdn.oto.com/[^"\']*?\.jpg', html)
        
        # Deduplicate while preserving order
        unique_urls = list(dict.fromkeys(matches))
        # Take first 2
        car_images[car_id] = unique_urls[:2]
        print(f"Found for {car_id}: {car_images[car_id]}")
    except Exception as e:
        print(f"Error for {car_id}: {e}")

with open('oto_urls.json', 'w') as f:
    json.dump(car_images, f)
