import json

file = open("output.osm",encoding="utf-8")
lines = []
for line in file.readlines():
    line = line.lstrip().replace("\n","")
    if line.startswith("<?xml") or line.startswith("<osm") or line.startswith("<bounds") or line.startswith("</osm"):
        continue
    lines.append(line)
file.close()

nodes = {}
ways = {}
isNode = False
isWay = False
prevId = None

for line in lines:
    if line.startswith("<node"):
        parts = line.split(" ")
        Id = int(parts[1].replace("id=\"","").replace("\"",""))
        prevId = Id
        nodes[Id] = {
            "id": Id,
            "lat": float(parts[2].replace("lat=\"","").replace("\"","")),
            "lon": float(parts[3].replace("lon=\"","").replace("\"","")),
            "addr:city": None,
            "category": None,
        }
        isWay = False
        isNode = not(line.endswith("/>"))
        if not(line.endswith("/>")):
            nodes[Id]["name"] = None
            nodes[Id]["name:en"] = None
    elif line.startswith("<way"):
        parts = line.split(" ")
        Id = int(parts[1].replace("id=\"","").replace("\"",""))
        prevId = Id
        ways[Id] = {
            "id": Id,
            "name": None,
            "name:en": None,
            "lat": None,
            "lon": None,
            "addr:city": None,
            "category": None,
            "nodes": [],
        }
        isWay = True
        isNode = False
    elif line.startswith("</node"):
        isNode = False
    elif line.startswith("</way"):
        isway = False
    elif line.startswith("<tag"):
        parts = line.split("=")
        key = parts[1].replace("\"","").replace(" v","")
        value = parts[2].replace("\"","").replace("/>","")
        entry = nodes[prevId] if isNode else ways[prevId]
        if entry.get(key, "/") == None:
            entry[key] = value
    elif line.startswith("<nd"):
        parts = line.split(" ")
        value = int(parts[1].replace("ref=\"","").replace("\"/>",""))
        ways[prevId]["nodes"].append(value)
    
data = { "nodes": nodes, "ways": ways }
file = open("output.json", "w", encoding="utf-8")
file.write(json.dumps(data, indent=4).encode('ascii').decode('unicode-escape'))
file.close()
