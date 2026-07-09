import json
import time
import requests
from duckduckgo_search import DDGS

logo_map = {
    "OLX Autos": "olx autos indonesia logo png",
    "Carsome": "carsome logo png",
    "Carro": "carro logo png",
    "Mobil123": "mobil123 logo png",
    "Carmudi": "carmudi logo png",
    "OTO": "oto.com logo png",
    "Caroline.id": "caroline.id logo png",
    "Seva": "seva.id logo png",
    "CintaMobil": "cintamobil.com logo png",
    "Momobil": "momobil.id logo png",
    "Garasi.id": "garasi.id logo png",
    "Auto2000": "auto2000 logo png"
}

file_names = {
    "OLX Autos": "olx.png",
    "Carsome": "carsome.png",
    "Carro": "carro.png",
    "Mobil123": "mobil123.png",
    "Carmudi": "carmudi.png",
    "OTO": "oto.png",
    "Caroline.id": "caroline.png",
    "Seva": "seva.png",
    "CintaMobil": "cintamobil.png",
    "Momobil": "momobil.png",
    "Garasi.id": "garasi.png",
    "Auto2000": "auto2000.png"
}

def download_image(query, filename):
    print(f"Searching for {query}...")
    try:
        with DDGS() as ddgs:
            results = list(ddgs.images(query, max_results=3))
            
            for res in results:
                url = res.get('image')
                if url:
                    print(f"Downloading {url}...")
                    try:
                        headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'}
                        r = requests.get(url, headers=headers, timeout=10)
                        if r.status_code == 200:
                            with open(f"assets/logos/{filename}", 'wb') as f:
                                f.write(r.content)
                            print(f"Successfully downloaded {filename}")
                            return True
                    except Exception as e:
                        print(f"Failed to download {url}: {e}")
    except Exception as e:
        print(f"Search failed for {query}: {e}")
    
    print(f"Could not download image for {query}")
    return False

for name, query in logo_map.items():
    download_image(query, file_names[name])
    time.sleep(2)
