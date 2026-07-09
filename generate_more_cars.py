import json

with open('config.json', 'r') as f:
    data = json.load(f)

more_cars = [
    {
        "id": "c16",
        "name": "Hyundai Creta",
        "brand": "Hyundai",
        "year": "2022-2023",
        "price_range": "Rp 240 Jt - Rp 350 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/4/4e/2022_Hyundai_Creta_Prime_wagon_%28SU2id%3B_02-23-2022%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/2/23/2022_Hyundai_Creta_Prime_wagon_%28SU2id%3B_02-23-2022%29_rear.jpg"
        ],
        "description": "Compact SUV modern dengan fitur canggih Hyundai SmartSense dan panoramic sunroof. Desain futuristik.",
        "tags": ["SUV", "Modern", "Fitur Lengkap"]
    },
    {
        "id": "c17",
        "name": "Hyundai Stargazer",
        "brand": "Hyundai",
        "year": "2022-2023",
        "price_range": "Rp 200 Jt - Rp 270 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/2/2e/2022_Hyundai_Stargazer_Prime_wagon_%28KS%3B_08-20-2022%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2022_Hyundai_Stargazer_Prime_wagon_%28KS%3B_08-20-2022%29_rear.jpg"
        ],
        "description": "LMPV dengan desain 'One Curve' yang unik layaknya pesawat ruang angkasa, kabin super lega dan captain seat.",
        "tags": ["LMPV", "Keluarga", "Futuristik"]
    },
    {
        "id": "c18",
        "name": "Wuling Almaz",
        "brand": "Wuling",
        "year": "2019-2023",
        "price_range": "Rp 220 Jt - Rp 330 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/d/dd/2019_Wuling_Almaz_Exclusive_wagon_%2810-18-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/ae/2019_Wuling_Almaz_Exclusive_wagon_%2810-18-2019%29_rear.jpg"
        ],
        "description": "Medium SUV bermesin Turbo dengan fitur Wuling Indonesian Command (WIND). Sangat *value for money*.",
        "tags": ["SUV", "Turbo", "Teknologi"]
    },
    {
        "id": "c19",
        "name": "Wuling Confero S",
        "brand": "Wuling",
        "year": "2017-2023",
        "price_range": "Rp 110 Jt - Rp 160 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/d/dc/2017_Wuling_Confero_S_1.5_L_wagon_%2811-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2017_Wuling_Confero_S_1.5_L_wagon_%2811-19-2019%29_rear.jpg"
        ],
        "description": "LMPV penggerak belakang termurah. Kabin sangat luas, plat bodi tebal, sangat nyaman untuk bepergian jauh.",
        "tags": ["LMPV", "RWD", "Murah"]
    },
    {
        "id": "c20",
        "name": "Honda CR-V",
        "brand": "Honda",
        "year": "2017-2022",
        "price_range": "Rp 320 Jt - Rp 500 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2017_Honda_CR-V_1.5_Prestige_Turbo_wagon_%28RW1%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/d/da/2017_Honda_CR-V_1.5_Prestige_Turbo_wagon_%28RW1%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "Medium SUV legendaris. Varian 1.5L Turbo sangat bertenaga, kabin mewah, dan suspensi nyaman layaknya sedan.",
        "tags": ["SUV Premium", "Turbo", "Mewah"]
    },
    {
        "id": "c21",
        "name": "Honda Civic (Turbo)",
        "brand": "Honda",
        "year": "2016-2021",
        "price_range": "Rp 350 Jt - Rp 450 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2016_Honda_Civic_1.5_ES_Turbo_sedan_%28FC1%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/3/36/2016_Honda_Civic_1.5_ES_Turbo_sedan_%28FC1%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "Sedan sporty paling dicari anak muda dan eksekutif muda. Desain coupe-like dengan mesin turbo yang sangat kencang.",
        "tags": ["Sedan", "Sporty", "Kencang"]
    },
    {
        "id": "c22",
        "name": "Toyota Yaris",
        "brand": "Toyota",
        "year": "2014-2022",
        "price_range": "Rp 150 Jt - Rp 250 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/3/3d/2018_Toyota_Yaris_1.5_TRD_Sportivo_hatchback_%28NSP151%3B_11-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/9/91/2018_Toyota_Yaris_1.5_TRD_Sportivo_hatchback_%28NSP151%3B_11-20-2019%29_rear.jpg"
        ],
        "description": "Hatchback favorit selain Honda Jazz. Terkenal bandel, perawatannya mudah, dan AC sangat dingin.",
        "tags": ["Hatchback", "Anak Muda", "Bandel"]
    },
    {
        "id": "c23",
        "name": "Mazda CX-5",
        "brand": "Mazda",
        "year": "2017-2022",
        "price_range": "Rp 350 Jt - Rp 550 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/c/cc/2017_Mazda_CX-5_2.5_Grand_Touring_wagon_%28KF%3B_11-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ea/2017_Mazda_CX-5_2.5_Grand_Touring_wagon_%28KF%3B_11-20-2019%29_rear.jpg"
        ],
        "description": "SUV Jepang dengan rasa Eropa. Desain KODO yang sangat cantik, handling tajam, dan interior berkualitas tinggi.",
        "tags": ["SUV Premium", "Cantik", "Handling"]
    },
    {
        "id": "c24",
        "name": "Nissan Grand Livina",
        "brand": "Nissan",
        "year": "2013-2018",
        "price_range": "Rp 90 Jt - Rp 140 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2013_Nissan_Grand_Livina_1.5_XV_wagon_%28L11%3B_11-21-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2013_Nissan_Grand_Livina_1.5_XV_wagon_%28L11%3B_11-21-2019%29_rear.jpg"
        ],
        "description": "LMPV dengan posisi duduk layaknya sedan. Sangat nyaman, bantingan empuk, cocok untuk perjalanan jauh.",
        "tags": ["LMPV", "Nyaman", "Keluarga"]
    },
    {
        "id": "c25",
        "name": "Suzuki Baleno Hatchback",
        "brand": "Suzuki",
        "year": "2017-2022",
        "price_range": "Rp 160 Jt - Rp 210 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/a/ae/2017_Suzuki_Baleno_1.4_hatchback_%2811-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/4/4e/2017_Suzuki_Baleno_1.4_hatchback_%2811-20-2019%29_rear.jpg"
        ],
        "description": "Hatchback *underrated* yang menawarkan ruang kabin sangat lega, irit bbm, dengan harga bekas sangat terjangkau.",
        "tags": ["Hatchback", "Irit", "Value For Money"]
    }
]

# append the new cars
data['used_cars'].extend(more_cars)

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
