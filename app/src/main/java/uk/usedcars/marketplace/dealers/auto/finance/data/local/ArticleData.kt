package uk.usedcars.marketplace.dealers.auto.finance.data.local

data class Article(
    val id: String,
    val title: String,
    val date: String,
    val imageUrl: String,
    val content: String,
    val author: String
)

object ArticleData {
    val articles = listOf(
    Article(
        id = "1",
        title = "Trik Jitu Inspeksi Bodi Mobil Bekas",
        date = "10 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c3_0.jpg",
        content = "Mengecek bodi mobil bekas adalah langkah krusial. Pastikan jarak antar panel simetris. Ketuk bodi secara perlahan; suara kaleng nyaring menandakan belum ada dempul tebal. Jangan lupa cek pilar A, B, dan C apakah ada bekas las-lasan yang tidak wajar akibat tabrakan.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "2",
        title = "Kenali Tanda Transmisi Matic Mulai Jebol",
        date = "09 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c7_0.jpg",
        content = "Transmisi matic yang bermasalah bisa menguras kantong. Tanda utamanya adalah jeda saat memindahkan gigi dari P ke R atau N ke D. Selain itu, jika terasa entakan kasar (jedug) atau putaran mesin tinggi tanpa diimbangi laju kendaraan (slip), waspadalah!",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "3",
        title = "5 Langkah Aman Beli Mobil Via Online",
        date = "08 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c12_0.jpg",
        content = "Berbelanja mobil online kini makin tren. Pertama, pastikan reputasi platform. Kedua, minta video detail mobil. Ketiga, manfaatkan fitur garansi uang kembali. Keempat, sewa jasa inspektor independen. Kelima, baca syarat dan ketentuan kredit secara teliti sebelum DP.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "4",
        title = "Waspada Odometer Putaran!",
        date = "07 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c20_0.jpg",
        content = "Odometer rendah tapi interior usang? Itu tanda bahaya. Cek pedal gas, rem, dan setir. Jika sudah sangat aus padahal KM di bawah 50rb, patut dicurigai. Bandingkan juga dengan buku servis berkala untuk melihat riwayat perawatan yang sah.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "5",
        title = "Panduan Cek Mesin Tanpa ke Bengkel",
        date = "06 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c2_0.jpg",
        content = "Nyalakan mesin dan dengarkan suaranya. Mesin yang sehat memiliki suara yang halus tanpa bunyi 'tek-tek' kasar. Cek dipstick oli; warna hitam pekat berarti jarang ganti oli, tapi warna seperti kopi susu berarti air radiator masuk ke mesin (turun mesin).",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "6",
        title = "Mitos atau Fakta: Mobil Bekas Taksi Cepat Rusak?",
        date = "05 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c50_0.jpg",
        content = "Banyak yang ragu membeli eks-taksi. Faktanya, jika Anda mendapatkan armada yang terawat dengan riwayat jelas, mobil ini sangat badak. Pastikan kaki-kaki, kompresor AC, dan power steering dicek ulang karena kilometer yang tinggi membebani komponen tersebut.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "7",
        title = "Mobil Bekas Tabrakan: Cara Mengenalinya",
        date = "04 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c14_0.jpg",
        content = "Cek ruang mesin. Tulang sasis atau apron yang bengkok, keriting, atau catnya belang adalah bukti nyata perbaikan pasca tabrakan frontal. Cek juga sealer di ujung pintu dan kap mesin; pabrikan biasanya rapi, sementara perbaikan bengkel sering berantakan.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "8",
        title = "Mobil Listrik Bekas: Apa Saja yang Harus Dicek?",
        date = "03 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c40_0.jpg",
        content = "Membeli EV bekas berbeda dengan mobil bensin. Fokuslah pada *State of Health* (SOH) baterai. Minta colokan OBD scanner atau cek dari layar instrumen. Pastikan garansi baterai (biasanya 8 tahun) masih berlaku dan catat riwayat servis resminya.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "9",
        title = "Cara Tawar-Menawar Harga Mobil Bekas",
        date = "02 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c32_0.jpg",
        content = "Gunakan data sebagai senjata. Jika Anda menemukan ban gundul, pajang mati, atau AC kurang dingin, gunakan itu untuk menawar. Tapi ingat, menawar dengan sopan dan realistis lebih berpeluang disetujui penjual daripada menawar sadis tanpa alasan.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "10",
        title = "Mengapa Harga Jual Mobil SUV Lebih Stabil?",
        date = "01 Jul 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c33_0.jpg",
        content = "SUV memiliki ground clearance tinggi yang sangat relevan dengan kontur jalan di Indonesia yang rawan banjir dan bergelombang. Permintaan yang terus tinggi membuat depresiasi harga SUV lebih rendah dibanding sedan atau hatchback.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "11",
        title = "Kenali Tanda Mobil Bekas Terendam Banjir",
        date = "30 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c35_0.jpg",
        content = "Aroma apak di kabin adalah indikasi pertama. Periksa kolong kursi dan rel jok; jika berkarat parah, hati-hati! Tarik sabuk pengaman sampai ujung, jika ada noda lumpur, tinggalkan. Cek juga kelistrikan, pastikan semua lampu dan power window lancar.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "12",
        title = "Menghitung Simulasi Kredit: DP Besar atau Cicilan Ringan?",
        date = "29 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c36_0.jpg",
        content = "DP besar berarti hutang pokok kecil, bunga lebih sedikit, dan asuransi lebih murah. Cicilan ringan berarti DP kecil tapi bunga membengkak. Sesuaikan dengan cash flow Anda. Ingat aturan 20/4/10: DP 20%, tenor 4 tahun, cicilan maksimal 10% dari pendapatan.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "13",
        title = "Jangan Abaikan Test Drive!",
        date = "28 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c37_0.jpg",
        content = "Melihat saja tidak cukup. Lakukan test drive minimal 15 menit. Rasakan perpindahan gigi, respons rem, dan bantingannya di jalan berlubang. Matikan audio dan dengarkan apakah ada suara aneh dari kolong mobil saat berbelok tajam.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "14",
        title = "Pentingnya Cek Keabsahan BPKB dan STNK",
        date = "27 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c38_0.jpg",
        content = "Bawa BPKB ke Samsat terdekat untuk cek fisik dan blokir. Pastikan nomor rangka dan nomor mesin di STNK sesuai dengan wujud aslinya. BPKB palsu biasanya memiliki hologram yang buram dan kertas yang teksturnya tidak wajar.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "15",
        title = "Keuntungan Beli Mobil di Showroom vs Perorangan",
        date = "26 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c39_0.jpg",
        content = "Beli di showroom lebih praktis, bisa kredit, dan ada garansi toko. Beli di perorangan harganya lebih murah dan bisa tahu sejarah mobil dari tangan pertama. Keduanya punya plus minus, pastikan Anda mengajak ahli saat mengecek.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "16",
        title = "Perawatan Pertama Setelah Beli Mobil Bekas",
        date = "25 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c41_0.jpg",
        content = "Setelah mobil di tangan, lakukan 'Reset'. Ganti oli mesin, oli transmisi, minyak rem, dan air radiator. Bersihkan filter AC dan cek kondisi timing belt/chain. Pengeluaran awal ini memastikan Anda berkendara tanpa was-was di kemudian hari.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "17",
        title = "Kenapa Warna Mobil Mempengaruhi Harga Jual?",
        date = "24 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c42_0.jpg",
        content = "Warna putih, hitam, dan silver adalah 'warna sejuta umat' yang sangat mudah dijual kembali. Warna terang seperti kuning, merah, atau hijau cenderung sulit laku, sehingga showroom sering kali menawarnya dengan harga lebih rendah.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "18",
        title = "Apakah Usia Kendaraan Menentukan Kualitas?",
        date = "23 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c43_0.jpg",
        content = "Usia hanyalah angka jika perawatannya konsisten. Mobil 10 tahun yang dirawat di bengkel resmi bisa jadi lebih sehat dari mobil 3 tahun yang sering telat ganti oli dan dipakai kasar. Fokuslah pada kondisi aktual, bukan sekadar tahun perakitan.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "19",
        title = "Mitos Ganti Oli Transmisi Manual yang Sering Diabaikan",
        date = "22 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c44_0.jpg",
        content = "Meski lebih tangguh, transmisi manual tetap butuh pergantian oli tiap 40.000 KM. Oli yang kotor membuat operan gigi keras dan mempercepat keausan sinkromes. Perawatannya murah namun sangat vital.",
        author = "Redaksi AutoBeli"
    ),
    Article(
        id = "20",
        title = "Beli Mobil Bekas Cash atau Kredit?",
        date = "21 Jun 2026",
        imageUrl = "https://raw.githubusercontent.com/amrimarihotjati/Jual-Beli-Mobil-Bekas/main/images/c45_0.jpg",
        content = "Beli cash membebaskan Anda dari bunga dan cicilan bulanan, namun menguras likuiditas. Beli kredit menjaga cash flow Anda, tapi total biaya membengkak. Pilihan terbaik? Beli cash jika ada dana lebih, atau DP 50% jika harus kredit.",
        author = "Redaksi AutoBeli"
    )
    )
}
