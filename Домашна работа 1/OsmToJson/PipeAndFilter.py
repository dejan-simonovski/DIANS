import json

class Filter:
    def execute(self, value):
        pass

class AddNewIdFilter(Filter):
    _i = 1
    
    def execute(self, value):
        value["id"] = AddNewIdFilter._i
        AddNewIdFilter._i += 1
        return value

class RemoveNodesFilter(Filter):
    def execute(self, value):
        if value.get("nodes",None) != None: 
            value.pop("nodes")
        return value

class FindUniqueCategoriesFilter(Filter):
    idByCategory = {}
    _i = 1
    
    def execute(self, value):
        category = value["category"]
        if FindUniqueCategoriesFilter.idByCategory.get(category, None) == None:
            FindUniqueCategoriesFilter.idByCategory[category] = FindUniqueCategoriesFilter._i
            FindUniqueCategoriesFilter._i += 1
        return value

    @classmethod
    def categoriesToList(cls):
        return [ { "id": v, "name" : k } for k, v in cls.idByCategory.items() ]

class ReplaceCategoryWithRefferenceFilter(Filter):
    def execute(self, value):
        category = value.pop("category")
        value["categoryId"] = FindUniqueCategoriesFilter.idByCategory[category]
        return value

class Pipe:
    def __init__(self):
        self.filters = []

    def executeAllFilters(self, value):
        result = value
        for fil in self.filters:
            result = fil.execute(result)
        return result

def writeToJsonFile(data, name):
    file = open(name,"w",encoding="utf-8")
    file.write(json.dumps(data, indent=4).encode('ascii').decode('unicode-escape'))
    file.close()

pipe = Pipe()
pipe.filters.append(AddNewIdFilter())
pipe.filters.append(RemoveNodesFilter())
pipe.filters.append(FindUniqueCategoriesFilter())
pipe.filters.append(ReplaceCategoryWithRefferenceFilter())

file = open("output.json",encoding="utf-8")
data = json.loads(file.read())
file.close()

locations = []
locations.extend(data["nodes"].values())
locations.extend(data["ways"].values())

for loc in locations:
    pipe.executeAllFilters(loc)

writeToJsonFile(locations, "locations.json")
writeToJsonFile(FindUniqueCategoriesFilter.categoriesToList(), "categories.json")


