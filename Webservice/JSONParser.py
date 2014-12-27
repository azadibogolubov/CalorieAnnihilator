import json, requests
url = "http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&max=1"
response = requests.get(url=url).json()
print(response["report"]["foods"][0]["name"])
print(response["report"]["foods"][0]["measure"])
print(response["report"]["foods"][0]["nutrients"][0]["value"] + " calories")
