import urllib.request
urls = [
    "https://www.oto.com/mobil-baru/honda/hrv/gambar",
    "https://www.oto.com/mobil-baru/wuling/airev/gambar"
]
for u in urls:
    req = urllib.request.Request(u, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        urllib.request.urlopen(req)
        print(f"SUCCESS: {u}")
    except Exception as e:
        print(f"FAILED: {u} - {e}")
