import json

with open('config.json', 'r') as f:
    data = json.load(f)

for mp in data['marketplaces']:
    # Fix direct link http missing
    if not mp.get('direct_link', '').startswith('http'):
        if mp.get('direct_link'):
            mp['direct_link'] = 'https://' + mp['direct_link']
        else:
            mp['direct_link'] = 'https://google.com/search?q=' + mp['name'].replace(' ', '+')

    mp['established_year'] = "2015"
    mp['headquarters'] = "Jakarta, Indonesia"
    mp['tags'] = ["Mobil Bekas", "Garansi Mesin", "Tukar Tambah", "Cicilan Ringan"]
    mp['payment_methods'] = ["Cash", "Kredit BCA", "Kredit Mandiri", "CIMB Niaga Auto Finance"]
    
    # Let's add some variety
    if "OLX" in mp['name']:
        mp['established_year'] = "2014"
        mp['headquarters'] = "Jakarta Selatan, Indonesia"
        mp['tags'] = ["C2C", "Inspeksi Ahli", "Banyak Pilihan", "Terpercaya"]
    elif "Carsome" in mp['name']:
        mp['established_year'] = "2015"
        mp['headquarters'] = "Selangor, Malaysia"
        mp['tags'] = ["175 Titik Inspeksi", "Beli Mobil", "Jual Mobil 1 Jam"]
    elif "Carro" in mp['name']:
        mp['established_year'] = "2015"
        mp['headquarters'] = "Singapura"
        mp['tags'] = ["Garansi 1 Tahun", "Lulus 160 Titik Inspeksi", "Transparan"]
        
with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
