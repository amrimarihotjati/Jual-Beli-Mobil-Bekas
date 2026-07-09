import json

with open('config.json', 'r') as f:
    data = json.load(f)

# Expanded list of best-selling cars in Indonesia
used_cars = [
    {
        "id": "c1",
        "name": "Toyota Avanza",
        "brand": "Toyota",
        "year": "2015-2022",
        "price_range": "Rp 120 Jt - Rp 230 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/4/4e/2021_Toyota_Avanza_1.5_G_W101RE_%2820211126%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/c/c8/2021_Toyota_Avanza_1.5_G_W101RE_rear_%2820211126%29.jpg"
        ],
        "description": "Mobil sejuta umat. Perawatan super mudah, irit, dan harga jual kembali stabil. Cocok untuk keluarga dan sangat diminati untuk taksi online.",
        "tags": ["MPV", "Keluarga", "Irit", "Populer"]
    },
    {
        "id": "c2",
        "name": "Honda Brio Satya",
        "brand": "Honda",
        "year": "2016-2023",
        "price_range": "Rp 115 Jt - Rp 180 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2018_Honda_Brio_Satya_1.2_E_DD1_%2820200115%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/7/7b/2018_Honda_Brio_Satya_1.2_E_DD1_rear_%2820200115%29.jpg"
        ],
        "description": "Rajanya city car Indonesia. Gesit, sangat irit BBM, dan punya desain sporty yang tak lekang oleh waktu.",
        "tags": ["City Car", "LCGC", "Anak Muda"]
    },
    {
        "id": "c3",
        "name": "Toyota Innova Reborn",
        "brand": "Toyota",
        "year": "2016-2022",
        "price_range": "Rp 250 Jt - Rp 450 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/9/91/2016_Toyota_Kijang_Innova_2.4_V_wagon_%28GUN140R%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/4/49/2016_Toyota_Kijang_Innova_2.4_V_wagon_%28GUN140R%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "Medium MPV ternyaman. Varian mesin diesel sangat diburu karena badak (tangguh) dan torsi besar.",
        "tags": ["MPV Premium", "Nyaman", "Diesel"]
    },
    {
        "id": "c4",
        "name": "Mitsubishi Xpander",
        "brand": "Mitsubishi",
        "year": "2017-2023",
        "price_range": "Rp 170 Jt - Rp 270 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2018_Mitsubishi_Xpander_1.5_Sport_NC1W_%2820191206%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/8/86/2018_Mitsubishi_Xpander_1.5_Sport_NC1W_rear_%2820191206%29.jpg"
        ],
        "description": "LMPV dengan kabin terlega dan bantingan suspensi paling nyaman di kelasnya, dipadukan desain Dynamic Shield yang garang.",
        "tags": ["LMPV", "Keluarga", "Nyaman"]
    },
    {
        "id": "c5",
        "name": "Daihatsu Sigra",
        "brand": "Daihatsu",
        "year": "2016-2023",
        "price_range": "Rp 90 Jt - Rp 140 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/d/da/2019_Daihatsu_Sigra_1.2_R_B401RS_%2820200216%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/eb/2019_Daihatsu_Sigra_1.2_R_B401RS_rear_%2820200216%29.jpg"
        ],
        "description": "Mobil 7-penumpang termurah dan paling ekonomis. Pilihan rasional sebagai mobil keluarga pertama.",
        "tags": ["LCGC", "7-Seater", "Murah"]
    },
    {
        "id": "c6",
        "name": "Toyota Fortuner (VRZ)",
        "brand": "Toyota",
        "year": "2016-2023",
        "price_range": "Rp 380 Jt - Rp 600 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2016_Toyota_Fortuner_2.4_VRZ_wagon_%28GUN165R%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2016_Toyota_Fortuner_2.4_VRZ_wagon_%28GUN165R%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "SUV ladder frame idaman. Tampil intimidatif, mesin diesel badak, dan kaki-kaki kuat.",
        "tags": ["SUV Besar", "Gagah", "Diesel"]
    },
    {
        "id": "c7",
        "name": "Honda HR-V",
        "brand": "Honda",
        "year": "2015-2021",
        "price_range": "Rp 180 Jt - Rp 290 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2018_Honda_HR-V_1.5_E_Special_Edition_wagon_%28RU1%3B_11-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/ba/2018_Honda_HR-V_1.5_E_Special_Edition_wagon_%28RU1%3B_11-20-2019%29_rear.jpg"
        ],
        "description": "Compact SUV paling stylish. Interior mewah, stabil, dan tenaga melimpah khas Honda.",
        "tags": ["SUV", "Sporty", "Premium"]
    },
    {
        "id": "c8",
        "name": "Mitsubishi Pajero Sport",
        "brand": "Mitsubishi",
        "year": "2016-2022",
        "price_range": "Rp 400 Jt - Rp 620 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/a/ae/2016_Mitsubishi_Pajero_Sport_Dakar_%2820190222%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ea/2016_Mitsubishi_Pajero_Sport_Dakar_rear_%2820190222%29.jpg"
        ],
        "description": "Rival utama Fortuner. Menawarkan torsi yang lebih menjambak dan desain fascia depan yang sangat garang.",
        "tags": ["SUV Besar", "Diesel", "Garang"]
    },
    {
        "id": "c9",
        "name": "Toyota Rush",
        "brand": "Toyota",
        "year": "2018-2023",
        "price_range": "Rp 190 Jt - Rp 270 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/3/36/2018_Toyota_Rush_1.5_TRD_Sportivo_F800RE_%2820181223%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2018_Toyota_Rush_1.5_TRD_Sportivo_F800RE_rear_%2820181223%29.jpg"
        ],
        "description": "Low SUV andalan keluarga. Penggerak roda belakang (RWD) membuatnya sangat bisa diandalkan untuk tanjakan dan jalan rusak.",
        "tags": ["LSUV", "RWD", "Tangguh"]
    },
    {
        "id": "c10",
        "name": "Suzuki Ertiga",
        "brand": "Suzuki",
        "year": "2018-2022",
        "price_range": "Rp 150 Jt - Rp 230 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/4/4b/2018_Suzuki_Ertiga_GL_1.5_NC22S_%2820190529%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/2/23/2018_Suzuki_Ertiga_GL_1.5_NC22S_rear_%2820190529%29.jpg"
        ],
        "description": "LMPV dengan efisiensi BBM terbaik di kelasnya serta kabin yang sangat senyap dan halus.",
        "tags": ["LMPV", "Irit", "Nyaman"]
    },
    {
        "id": "c11",
        "name": "Daihatsu Ayla",
        "brand": "Daihatsu",
        "year": "2013-2022",
        "price_range": "Rp 70 Jt - Rp 120 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/6/64/2013_Daihatsu_Ayla_1.0_X_B400RS_%2820190303%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/05/2013_Daihatsu_Ayla_1.0_X_B400RS_rear_%2820190303%29.jpg"
        ],
        "description": "Mobil LCGC paling murah meriah. Mudah parkir, perawatan murah, dan suku cadang melimpah.",
        "tags": ["City Car", "LCGC", "Sangat Murah"]
    },
    {
        "id": "c12",
        "name": "Toyota Calya",
        "brand": "Toyota",
        "year": "2016-2023",
        "price_range": "Rp 100 Jt - Rp 145 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/fe/2016_Toyota_Calya_1.2_G_B401RA_%2820190224%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/6/6e/2016_Toyota_Calya_1.2_G_B401RA_rear_%2820190224%29.jpg"
        ],
        "description": "Kembaran Sigra dengan lambang Toyota. Kualitas terjamin dan nilai purna jual sangat baik.",
        "tags": ["LCGC", "7-Seater", "Keluarga"]
    },
    {
        "id": "c13",
        "name": "Suzuki XL7",
        "brand": "Suzuki",
        "year": "2020-2023",
        "price_range": "Rp 200 Jt - Rp 270 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/a/ae/2020_Suzuki_XL7_Zeta_%2820200215%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b5/2020_Suzuki_XL7_Zeta_rear_%2820200215%29.jpg"
        ],
        "description": "Versi SUV dari Ertiga. Lebih tinggi, lebih maskulin, dan dilengkapi fitur canggih seperti Smart E-Mirror.",
        "tags": ["LSUV", "Modern", "Nyaman"]
    },
    {
        "id": "c14",
        "name": "Honda BR-V",
        "brand": "Honda",
        "year": "2016-2021",
        "price_range": "Rp 150 Jt - Rp 230 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2016_Honda_BR-V_1.5_E_Prestige_wagon_%28DG1%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2016_Honda_BR-V_1.5_E_Prestige_wagon_%28DG1%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "Crossover 7-penumpang dengan performa mesin i-VTEC terkuat di kelasnya. Desain kokoh khas SUV.",
        "tags": ["LSUV", "7-Seater", "Bertenaga"]
    },
    {
        "id": "c15",
        "name": "Toyota Agya",
        "brand": "Toyota",
        "year": "2013-2022",
        "price_range": "Rp 80 Jt - Rp 130 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2013_Toyota_Agya_1.0_G_TRD_S_B400RA_%2820191223%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/7/77/2013_Toyota_Agya_1.0_G_TRD_S_B400RA_rear_%2820191223%29.jpg"
        ],
        "description": "Hatchback kompak favorit anak muda. Sangat reliable, pajak murah, dan konsumsi bensin super irit.",
        "tags": ["City Car", "LCGC", "Populer"]
    }
]

data['used_cars'] = used_cars

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
