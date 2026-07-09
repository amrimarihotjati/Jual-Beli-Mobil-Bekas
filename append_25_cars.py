import json

with open('config.json', 'r') as f:
    data = json.load(f)

more_cars_25 = [
    {
        "id": "c26",
        "name": "Daihatsu Terios",
        "brand": "Daihatsu",
        "year": "2018-2023",
        "price_range": "Rp 180 Jt - Rp 250 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/e/e0/2018_Daihatsu_Terios_1.5_R_Deluxe_F800RV_%2820200222%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2018_Daihatsu_Terios_1.5_R_Deluxe_F800RV_rear_%2820200222%29.jpg"
        ],
        "description": "Kembaran Toyota Rush. Low SUV 7-penumpang dengan penggerak RWD, sangat tangguh dan ground clearance tinggi.",
        "tags": ["LSUV", "RWD", "Keluarga"]
    },
    {
        "id": "c27",
        "name": "Daihatsu Xenia",
        "brand": "Daihatsu",
        "year": "2015-2022",
        "price_range": "Rp 110 Jt - Rp 200 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/5/52/2019_Daihatsu_Xenia_1.3_R_F653RV_%2820200215%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/0f/2019_Daihatsu_Xenia_1.3_R_F653RV_rear_%2820200215%29.jpg"
        ],
        "description": "Kembaran Toyota Avanza. MPV andalan dengan harga yang sedikit lebih terjangkau, irit BBM, dan perawatannya sangat murah.",
        "tags": ["MPV", "Keluarga", "Irit"]
    },
    {
        "id": "c28",
        "name": "Honda Mobilio",
        "brand": "Honda",
        "year": "2014-2021",
        "price_range": "Rp 120 Jt - Rp 190 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/2/23/2017_Honda_Mobilio_1.5_E_wagon_%28DD4%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b5/2017_Honda_Mobilio_1.5_E_wagon_%28DD4%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "LMPV dengan handling terbaik dan mesin i-VTEC terkuat di kelasnya. Desain *sporty* yang disukai keluarga muda.",
        "tags": ["LMPV", "Handling", "Sporty"]
    },
    {
        "id": "c29",
        "name": "Honda Jazz",
        "brand": "Honda",
        "year": "2014-2021",
        "price_range": "Rp 160 Jt - Rp 270 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2018_Honda_Jazz_1.5_RS_hatchback_%28GK5%3B_11-21-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/3/36/2018_Honda_Jazz_1.5_RS_hatchback_%28GK5%3B_11-21-2019%29_rear.jpg"
        ],
        "description": "Hatchback legendaris yang selalu menjadi idola. Desain sporty abadi, kabin ultra lega berkat fitur Ultra Seat.",
        "tags": ["Hatchback", "Legendaris", "Ultra Seat"]
    },
    {
        "id": "c30",
        "name": "Toyota Raize",
        "brand": "Toyota",
        "year": "2021-2023",
        "price_range": "Rp 190 Jt - Rp 280 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/7/7b/2021_Toyota_Raize_1.0_Turbo_GR_Sport_A250RA_%2820210609%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2021_Toyota_Raize_1.0_Turbo_GR_Sport_A250RA_rear_%2820210609%29.jpg"
        ],
        "description": "Compact SUV bermesin 1.0L Turbo yang sangat responsif, dilengkapi fitur keselamatan canggih TSS (Toyota Safety Sense).",
        "tags": ["Compact SUV", "Turbo", "TSS"]
    },
    {
        "id": "c31",
        "name": "Daihatsu Rocky",
        "brand": "Daihatsu",
        "year": "2021-2023",
        "price_range": "Rp 180 Jt - Rp 260 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2021_Daihatsu_Rocky_1.0_R_TC_ASA_A250RS_%2820210816%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2021_Daihatsu_Rocky_1.0_R_TC_ASA_A250RS_rear_%2820210816%29.jpg"
        ],
        "description": "Kembaran Toyota Raize dengan harga yang lebih terjangkau, menawarkan teknologi platform DNGA terbaru.",
        "tags": ["Compact SUV", "DNGA", "Modern"]
    },
    {
        "id": "c32",
        "name": "Honda WR-V",
        "brand": "Honda",
        "year": "2022-2023",
        "price_range": "Rp 230 Jt - Rp 290 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2022_Honda_WR-V_RS_with_Honda_Sensing_wagon_%28DG4id%3B_11-19-2022%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2022_Honda_WR-V_RS_with_Honda_Sensing_wagon_%28DG4id%3B_11-19-2022%29_rear.jpg"
        ],
        "description": "Small SUV dengan mesin 1.5L terkuat di kelasnya (121 PS). Desain sporty dengan teknologi Honda Sensing.",
        "tags": ["Small SUV", "Bertenaga", "Sensing"]
    },
    {
        "id": "c33",
        "name": "Toyota Kijang Innova Zenix",
        "brand": "Toyota",
        "year": "2022-2023",
        "price_range": "Rp 380 Jt - Rp 600 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/d/da/2022_Toyota_Kijang_Innova_Zenix_2.0_Q_Hybrid_TSS_%28MAGH10R%3B_11-26-2022%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/4/4e/2022_Toyota_Kijang_Innova_Zenix_2.0_Q_Hybrid_TSS_%28MAGH10R%3B_11-26-2022%29_rear.jpg"
        ],
        "description": "Evolusi Kijang ke platform monokok TNGA. Tersedia dalam varian Hybrid yang sangat efisien dan nyaman layaknya mobil premium.",
        "tags": ["MPV", "Hybrid", "TNGA"]
    },
    {
        "id": "c34",
        "name": "Wuling Air EV",
        "brand": "Wuling",
        "year": "2022-2023",
        "price_range": "Rp 180 Jt - Rp 280 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2022_Wuling_Air_ev_Long_Range_%2808-16-2022%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/3/36/2022_Wuling_Air_ev_Long_Range_%2808-16-2022%29_rear.jpg"
        ],
        "description": "Mobil listrik (EV) paling fenomenal di Indonesia. Sangat compact, praktis untuk di perkotaan, dan bebas ganjil-genap.",
        "tags": ["EV", "Listrik", "Compact"]
    },
    {
        "id": "c35",
        "name": "Hyundai Ioniq 5",
        "brand": "Hyundai",
        "year": "2022-2023",
        "price_range": "Rp 600 Jt - Rp 800 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/8/86/2022_Hyundai_Ioniq_5_Signature_Standard_Range_%28NE%3B_04-14-2022%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2022_Hyundai_Ioniq_5_Signature_Standard_Range_%28NE%3B_04-14-2022%29_rear.jpg"
        ],
        "description": "Mobil listrik (EV) premium buatan Cikarang dengan desain retro-futuristik, kabin super lega, dan pengisian daya ultra-cepat.",
        "tags": ["EV", "Premium", "Futuristik"]
    },
    {
        "id": "c36",
        "name": "Toyota Vios",
        "brand": "Toyota",
        "year": "2014-2022",
        "price_range": "Rp 130 Jt - Rp 250 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2018_Toyota_Vios_1.5_G_sedan_%28NSP151%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2018_Toyota_Vios_1.5_G_sedan_%28NSP151%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "Sedan andalan yang terkenal karena 'badak' (durabilitas tinggi). Banyak diminati sebagai mobil harian karena irit dan perawatannya gampang.",
        "tags": ["Sedan", "Bandel", "Irit"]
    },
    {
        "id": "c37",
        "name": "Toyota Camry",
        "brand": "Toyota",
        "year": "2015-2021",
        "price_range": "Rp 250 Jt - Rp 500 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2019_Toyota_Camry_2.5_V_sedan_%28ASV70R%3B_11-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2019_Toyota_Camry_2.5_V_sedan_%28ASV70R%3B_11-20-2019%29_rear.jpg"
        ],
        "description": "Sedan mid-size langganan para bos dan pejabat. Bantingan sangat empuk, kabin super kedap dan mewah.",
        "tags": ["Sedan Besar", "Mewah", "Pejabat"]
    },
    {
        "id": "c38",
        "name": "Toyota Corolla Altis",
        "brand": "Toyota",
        "year": "2014-2021",
        "price_range": "Rp 180 Jt - Rp 400 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/2/23/2017_Toyota_Corolla_Altis_1.8_V_sedan_%28ZRE172R%3B_11-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/9/91/2017_Toyota_Corolla_Altis_1.8_V_sedan_%28ZRE172R%3B_11-20-2019%29_rear.jpg"
        ],
        "description": "Sedan C-segment yang legendaris, menawarkan kenyamanan lebih baik dari Vios dengan desain yang elegan.",
        "tags": ["Sedan", "Legendaris", "Elegan"]
    },
    {
        "id": "c39",
        "name": "Honda City Hatchback",
        "brand": "Honda",
        "year": "2021-2023",
        "price_range": "Rp 260 Jt - Rp 330 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2021_Honda_City_Hatchback_RS_with_Honda_Sensing_%28GN5%3B_03-03-2021%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2021_Honda_City_Hatchback_RS_with_Honda_Sensing_%28GN5%3B_03-03-2021%29_rear.jpg"
        ],
        "description": "Penerus tongkat estafet Honda Jazz. Desain lebih dewasa, ruang kabin lapang, dan dibekali teknologi keselamatan Honda Sensing.",
        "tags": ["Hatchback", "Sporty", "Penerus Jazz"]
    },
    {
        "id": "c40",
        "name": "Suzuki Ignis",
        "brand": "Suzuki",
        "year": "2017-2022",
        "price_range": "Rp 110 Jt - Rp 160 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2017_Suzuki_Ignis_1.2_GL_hatchback_%2811-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/d/da/2017_Suzuki_Ignis_1.2_GL_hatchback_%2811-19-2019%29_rear.jpg"
        ],
        "description": "Urban SUV mungil dengan desain nyentrik ala retro Eropa. Ground clearance lumayan tinggi (180mm) dan mesin 1.2L sangat irit.",
        "tags": ["Urban SUV", "City Car", "Nyentrik"]
    },
    {
        "id": "c41",
        "name": "KIA Sonet",
        "brand": "KIA",
        "year": "2020-2023",
        "price_range": "Rp 200 Jt - Rp 270 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/3/36/2021_Kia_Sonet_1.5_Premiere_wagon_%28QY%3B_02-23-2021%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2021_Kia_Sonet_1.5_Premiere_wagon_%28QY%3B_02-23-2021%29_rear.jpg"
        ],
        "description": "Compact SUV dari Korea dengan fitur berlimpah di kelasnya, seperti Sunroof, Bose Speaker, dan Ventilated Seat.",
        "tags": ["Compact SUV", "Fitur Melimpah", "Sunroof"]
    },
    {
        "id": "c42",
        "name": "KIA Seltos",
        "brand": "KIA",
        "year": "2020-2023",
        "price_range": "Rp 250 Jt - Rp 350 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2020_Kia_Seltos_1.4_EX_wagon_%28SP2i%3B_11-19-2020%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/af/2020_Kia_Seltos_1.4_EX_wagon_%28SP2i%3B_11-19-2020%29_rear.jpg"
        ],
        "description": "SUV bermesin 1.4L Turbo + transmisi kopling ganda (DCT) yang memberikan akselerasi instan. Desain berotot dan sangat maskulin.",
        "tags": ["SUV", "Turbo", "DCT"]
    },
    {
        "id": "c43",
        "name": "Nissan X-Trail",
        "brand": "Nissan",
        "year": "2014-2020",
        "price_range": "Rp 180 Jt - Rp 300 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/b/b5/2015_Nissan_X-Trail_2.0_wagon_%28T32%3B_11-20-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2015_Nissan_X-Trail_2.0_wagon_%28T32%3B_11-20-2019%29_rear.jpg"
        ],
        "description": "Medium SUV terempuk di kelasnya, mengalahkan CR-V dalam hal kenyamanan bantingan suspensi. Kursi Zero Gravity sangat nyaman.",
        "tags": ["SUV Premium", "Nyaman", "Empuk"]
    },
    {
        "id": "c44",
        "name": "Nissan Serena",
        "brand": "Nissan",
        "year": "2013-2021",
        "price_range": "Rp 150 Jt - Rp 380 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/d/da/2014_Nissan_Serena_2.0_Highway_Star_wagon_%28C26%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2014_Nissan_Serena_2.0_Highway_Star_wagon_%28C26%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "High MPV *boxy* dengan ruang kabin masif. Spesialis membawa keluarga liburan berkat captain seat dan pintu geser elektrik (PSD).",
        "tags": ["High MPV", "Boxy", "Keluarga Besar"]
    },
    {
        "id": "c45",
        "name": "Toyota Hilux (Double Cabin)",
        "brand": "Toyota",
        "year": "2016-2022",
        "price_range": "Rp 250 Jt - Rp 450 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2016_Toyota_Hilux_2.4_V_pickup_%28GUN125R%3B_11-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/4/4e/2016_Toyota_Hilux_2.4_V_pickup_%28GUN125R%3B_11-19-2019%29_rear.jpg"
        ],
        "description": "Raja *Double Cabin* yang tak tertandingi soal durabilitas di tambang dan medan off-road ekstrem. Suku cadang bisa ditemukan di mana saja.",
        "tags": ["Double Cabin", "Offroad", "Pekerja Keras"]
    },
    {
        "id": "c46",
        "name": "Mitsubishi Triton (Double Cabin)",
        "brand": "Mitsubishi",
        "year": "2016-2022",
        "price_range": "Rp 230 Jt - Rp 430 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/9/91/2016_Mitsubishi_Triton_Exceed_pickup_%2811-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ec/2016_Mitsubishi_Triton_Exceed_pickup_%2811-19-2019%29_rear.jpg"
        ],
        "description": "Pesaing Hilux dengan kenyamanan suspensi yang sedikit lebih baik. Desainnya garang berkat bahasa desain Dynamic Shield.",
        "tags": ["Double Cabin", "Offroad", "Garang"]
    },
    {
        "id": "c47",
        "name": "Isuzu Panther",
        "brand": "Isuzu",
        "year": "2005-2020",
        "price_range": "Rp 80 Jt - Rp 180 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/0/07/2005_Isuzu_Panther_Grand_Touring_wagon_%28TBR541%3B_02-23-2020%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/3/36/2005_Isuzu_Panther_Grand_Touring_wagon_%28TBR541%3B_02-23-2020%29_rear.jpg"
        ],
        "description": "Rajanya Rajanya Diesel! Mesin legendaris yang tidak pernah rusak asal diisi Solar dan oli. Sangat digemari di luar kota.",
        "tags": ["MPV", "Diesel", "Legendaris", "Badak"]
    },
    {
        "id": "c48",
        "name": "Chevrolet Spin",
        "brand": "Chevrolet",
        "year": "2013-2015",
        "price_range": "Rp 70 Jt - Rp 110 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/f/ff/2014_Chevrolet_Spin_1.5_LTZ_wagon_%2811-19-2019%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b5/2014_Chevrolet_Spin_1.5_LTZ_wagon_%2811-19-2019%29_rear.jpg"
        ],
        "description": "LMPV dengan pelat bodi setebal mobil Eropa. Uniknya, memiliki varian mesin Diesel (1.3L) yang torsinya memuaskan.",
        "tags": ["LMPV", "Eropa", "Solid"]
    },
    {
        "id": "c49",
        "name": "Daihatsu Gran Max (Minibus)",
        "brand": "Daihatsu",
        "year": "2010-2023",
        "price_range": "Rp 75 Jt - Rp 140 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/2/23/2013_Daihatsu_Gran_Max_1.5_D_wagon_%28S402RV%3B_02-23-2020%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/9/91/2013_Daihatsu_Gran_Max_1.5_D_wagon_%28S402RV%3B_02-23-2020%29_rear.jpg"
        ],
        "description": "Minibus pekerja keras! Sangat lega, sanggup membawa banyak penumpang atau barang kargo logistik tanpa kendala berarti.",
        "tags": ["Minibus", "Kargo", "Pekerja Keras"]
    },
    {
        "id": "c50",
        "name": "Suzuki Carry (Minibus)",
        "brand": "Suzuki",
        "year": "2010-2019",
        "price_range": "Rp 50 Jt - Rp 90 Jt",
        "image_urls": [
            "https://upload.wikimedia.org/wikipedia/commons/5/53/2010_Suzuki_Carry_1.5_Futura_wagon_%28SL415%3B_02-23-2020%29.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/d/da/2010_Suzuki_Carry_1.5_Futura_wagon_%28SL415%3B_02-23-2020%29_rear.jpg"
        ],
        "description": "Mobil legendaris angkutan umum/keluarga desa di Indonesia (Futura). Bandel, perbaikan murah, dan muat hingga 9 orang.",
        "tags": ["Minibus", "Angkot", "Murah Meriah"]
    }
]

data['used_cars'].extend(more_cars_25)

with open('config.json', 'w') as f:
    json.dump(data, f, indent=4)
