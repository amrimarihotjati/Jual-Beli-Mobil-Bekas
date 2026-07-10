import urllib.request
urls = [
    "https://www.oto.com/mobil-baru/honda/hr-v/gambar",
    "https://www.oto.com/mobil-baru/honda/br-v/gambar",
    "https://www.oto.com/mobil-baru/honda/cr-v/gambar",
    "https://www.oto.com/mobil-baru/nissan/livina/gambar",
    "https://www.oto.com/mobil-baru/wuling/air-ev/gambar",
    "https://www.oto.com/mobil-baru/suzuki/carry/gambar"
]
for u in urls:
    req = urllib.request.Request(u, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        urllib.request.urlopen(req)
        print(f"SUCCESS: {u}")
    except Exception as e:
        print(f"FAILED: {u} - {e}")
