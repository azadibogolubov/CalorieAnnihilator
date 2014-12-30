import json, requests
url = "http://api.data.gov/usda/ndb/search/?format=json&q=cheese&max=25&offset=0&api_key=DEMO_KEY"
nameResponse = requests.get(url=url).json()

for i in range(0, 5):
	name = nameResponse["list"]["item"][i]["name"]
	ndbno = nameResponse["list"]["item"][i]["ndbno"]
	print("Name: " + name)
	print("NDB #: " + ndbno)

	url2 = "http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&ndbno=" + ndbno + "&max=1"
	calResponse = requests.get(url=url2).json()
	print("Measure: " + calResponse["report"]["foods"][0]["measure"])
	print("Calories: " + calResponse["report"]["foods"][0]["nutrients"][0]["value"] + '\n')
