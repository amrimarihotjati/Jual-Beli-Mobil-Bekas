import json

base_url = "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/assets/logos/"
logo_map = {
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

with open('config.json', 'r') as f:
    data = json.load(f)

for mp in data['marketplaces']:
    name = mp['name']
    if name in logo_map:
        mp['logo_url'] = base_url + logo_map[name]

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
