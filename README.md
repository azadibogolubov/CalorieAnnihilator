CalorieAnnihilator
==================
The concept is quite simple. An app for the rest of us. Finally, a place where we can see not how many calories 
we put in, but rather, how many we avoided putting in.

The Calorie Annihilator will allow the user to look up a food or drink they are candidating eating, 
and will see instantaneously how many calories they would be taking in, as well as how many pounds of sugar 
are contained.

There are two modes, Avoidance Mode and Binge Mode. 

1) Avoidance Mode allows the user to enter food they have avoided to see the rewards and have the calorie 
and sugar count be added to their lifetime achievements. 
2) Binge Mode is for when the user later decided to eat that food anyways, and will deduct from lifetime 
achievements.

The system will use a SQLite database stored on the Android device to keep track of a user's progress, 
and will grab food data from an online datastore. The current intention is to use a web service to query 
the datastore, sanitize the data, and prepare it for reading into the Android device, so that there are 
not issues with latency.
