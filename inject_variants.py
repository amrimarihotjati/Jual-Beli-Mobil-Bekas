import json
import re

variant_map = {
    "brio": ["Tipe S", "Tipe E", "Tipe RS"],
    "avanza": ["Tipe E", "Tipe G", "Veloz"],
    "innova": ["Tipe G", "Tipe V", "Venturer/Q"],
    "ertiga": ["Tipe GA", "Tipe GL", "Tipe GX"],
    "hr-v": ["Tipe S", "Tipe E", "Tipe Prestige"],
    "hrv": ["Tipe S", "Tipe E", "Tipe Prestige"],
    "cr-v": ["2.0L", "1.5L Turbo", "1.5L Prestige"],
    "crv": ["2.0L", "1.5L Turbo", "1.5L Prestige"],
    "rush": ["Tipe G", "TRD Sportivo", "GR Sport"],
    "agya": ["1.0 G", "1.2 G", "1.2 GR Sport"],
    "ayla": ["1.0 D", "1.0 X", "1.2 R"],
    "calya": ["1.2 E Non ABS", "1.2 E", "1.2 G"],
    "sigra": ["1.0 D", "1.2 X", "1.2 R"],
    "xpander": ["GLS", "Exceed", "Ultimate"],
    "pajero": ["Exceed", "Dakar", "Dakar Ultimate"],
    "fortuner": ["Tipe G", "VRZ", "GR Sport"],
    "stargazer": ["Active", "Style", "Prime"],
    "creta": ["Active", "Trend", "Prime"],
    "br-v": ["Tipe S", "Tipe E", "Tipe Prestige"],
    "brv": ["Tipe S", "Tipe E", "Tipe Prestige"],
    "xl7": ["Zeta", "Beta", "Alpha"],
    "yaris": ["Tipe E", "Tipe G", "TRD Sportivo"],
    "jazz": ["Tipe S", "Tipe E", "Tipe RS"],
    "xenia": ["1.3 M", "1.3 X", "1.5 R"],
    "terios": ["Tipe X", "Tipe R", "R Custom"],
    "civic": ["1.5L Turbo", "Turbo Prestige"],
    "binguo": ["Long Range", "Premium Range"],
    "air ev": ["Standard Range", "Long Range"],
    "wuling": ["Standard", "Comfort", "Lux"],
    "mazda": ["Core", "Touring", "Grand Touring"],
    "bmw": ["sDrive", "xDrive", "M Sport"],
    "mercedes": ["Avantgarde", "Exclusive", "AMG Line"]
}

def get_variants_for_car(name):
    name_lower = name.lower()
    for key, variants in variant_map.items():
        if key in name_lower:
            return variants
    return ["Tipe Standar", "Tipe Menengah", "Tipe Tertinggi"]

def format_price(value):
    if value >= 1000:
        return f"Rp {value/1000:.1f} Miliar".replace(".0", "")
    else:
        return f"Rp {int(value)} Juta"

def generate_car_variants():
    with open('config.json', 'r') as f:
        data = json.load(f)

    for car in data.get('used_cars', []):
        price_range_str = car.get('price_range', '')
        
        parsed_vals = []
        parts = price_range_str.split('-')
        for part in parts:
            part = part.lower().replace(',', '.')
            nums = re.findall(r'[0-9.]+', part)
            if not nums: continue
            val = float(nums[0])
            if "mili" in part or "mily" in part or "m" in part.replace("mili", "").split(): 
                if val < 100: val = val * 1000
            elif val > 1000 and "juta" not in part:
                val = val / 1000000 # raw Rp
            parsed_vals.append(val)
            
        if len(parsed_vals) == 2:
            min_p, max_p = parsed_vals[0], parsed_vals[1]
        elif len(parsed_vals) == 1:
            min_p, max_p = parsed_vals[0], parsed_vals[0] + (parsed_vals[0]*0.2)
        else:
            min_p, max_p = 100, 150
            
        if min_p > max_p: min_p, max_p = max_p, min_p
        
        variants = get_variants_for_car(car['name'])
        
        car_variants = []
        num_v = len(variants)
        
        if num_v == 2:
            car_variants.append({
                "name": variants[0],
                "price": format_price(min_p),
                "level": "Terendah"
            })
            car_variants.append({
                "name": variants[1],
                "price": format_price(max_p),
                "level": "Tertinggi"
            })
        elif num_v == 3:
            mid_p = min_p + (max_p - min_p) / 2
            car_variants.append({
                "name": variants[0],
                "price": format_price(min_p),
                "level": "Terendah"
            })
            car_variants.append({
                "name": variants[1],
                "price": format_price(mid_p),
                "level": "Menengah"
            })
            car_variants.append({
                "name": variants[2],
                "price": format_price(max_p),
                "level": "Tertinggi"
            })
            
        car['variants'] = car_variants

    with open('config.json', 'w') as f:
        json.dump(data, f, indent=4)
        
if __name__ == '__main__':
    generate_car_variants()
    print("Variants injected successfully.")
