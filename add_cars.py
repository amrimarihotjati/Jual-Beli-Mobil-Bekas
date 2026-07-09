import json

with open('config.json', 'r') as f:
    data = json.load(f)

used_cars = [
    {
        "id": "c1",
        "name": "Toyota Avanza",
        "brand": "Toyota",
        "year": "2015-2022",
        "price_range": "Rp 120.000.000 - Rp 230.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/4/4e/2021_Toyota_Avanza_1.5_G_W101RE_%2820211126%29.jpg",
        "description": "Mobil MPV sejuta umat. Sangat diminati karena perawatan mudah, irit, dan harga jual kembali yang stabil.",
        "tags": ["MPV", "Keluarga", "Irit"]
    },
    {
        "id": "c2",
        "name": "Honda Brio Satya",
        "brand": "Honda",
        "year": "2016-2023",
        "price_range": "Rp 115.000.000 - Rp 180.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/e/ec/2018_Honda_Brio_Satya_1.2_E_DD1_%2820200115%29.jpg",
        "description": "City car mungil yang sangat gesit, irit BBM, dan cocok untuk anak muda maupun keluarga kecil di perkotaan.",
        "tags": ["City Car", "LCGC", "Anak Muda"]
    },
    {
        "id": "c3",
        "name": "Toyota Innova Reborn",
        "brand": "Toyota",
        "year": "2016-2022",
        "price_range": "Rp 250.000.000 - Rp 450.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/9/91/2016_Toyota_Kijang_Innova_2.4_V_wagon_%28GUN140R%3B_11-19-2019%29.jpg",
        "description": "Medium MPV ternyaman dan paling tangguh di kelasnya. Varian diesel sangat diburu karena bertenaga dan bandel.",
        "tags": ["MPV Premium", "Nyaman", "Diesel"]
    },
    {
        "id": "c4",
        "name": "Mitsubishi Xpander",
        "brand": "Mitsubishi",
        "year": "2017-2023",
        "price_range": "Rp 170.000.000 - Rp 270.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/0/07/2018_Mitsubishi_Xpander_1.5_Sport_NC1W_%2820191206%29.jpg",
        "description": "Lawan terberat Avanza dengan desain futuristik, kabin lega, dan suspensi nyaman khas Mitsubishi.",
        "tags": ["LMPV", "Keluarga", "Nyaman"]
    },
    {
        "id": "c5",
        "name": "Honda HR-V",
        "brand": "Honda",
        "year": "2015-2021",
        "price_range": "Rp 180.000.000 - Rp 290.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/a/af/2018_Honda_HR-V_1.5_E_Special_Edition_wagon_%28RU1%3B_11-20-2019%29.jpg",
        "description": "Compact SUV favorit dengan desain abadi, performa responsif, dan interior yang terasa premium.",
        "tags": ["SUV", "Sporty", "Premium"]
    },
    {
        "id": "c6",
        "name": "Toyota Fortuner (VRZ)",
        "brand": "Toyota",
        "year": "2016-2023",
        "price_range": "Rp 380.000.000 - Rp 600.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/5/53/2016_Toyota_Fortuner_2.4_VRZ_wagon_%28GUN165R%3B_11-19-2019%29.jpg",
        "description": "SUV Ladder Frame gagah yang menjadi simbol kesuksesan. Mesin dieselnya sangat bertenaga dan tangguh melibas segala medan.",
        "tags": ["SUV Besar", "Gagah", "Diesel"]
    },
    {
        "id": "c7",
        "name": "Daihatsu Sigra",
        "brand": "Daihatsu",
        "year": "2016-2023",
        "price_range": "Rp 90.000.000 - Rp 140.000.000",
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/d/da/2019_Daihatsu_Sigra_1.2_R_B401RS_%2820200216%29.jpg",
        "description": "MPV LCGC 7-seater paling terjangkau. Pilihan utama untuk taksi online dan mobil pertama keluarga.",
        "tags": ["LCGC", "7-Seater", "Murah"]
    }
]

data['used_cars'] = used_cars

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
