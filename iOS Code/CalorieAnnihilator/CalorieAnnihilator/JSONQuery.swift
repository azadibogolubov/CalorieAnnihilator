//
//  JSONQuery.swift
//  CalorieAnnihilator
//
//  Created by Azadi on 8/19/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class JSONQuery: NSObject {
    class func queryFoodDatabase() {
        // The query to make to receive the JSON information from the USDA database.
        let url = NSURL(string: "http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&ndbno=01218&max=1500")!
        let urlSession = NSURLSession.sharedSession()
        
        // Build the JSON query.
        let jsonQuery = urlSession.dataTaskWithURL(url, completionHandler: { data, response, error -> Void in
            if (error != nil) {
                println(error.localizedDescription)
            }
            var err: NSError?
            
            var jsonResult = NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions.MutableContainers, error: &err) as! NSDictionary
            
            // Parse all of the JSON values.
            if let report = jsonResult["report"] as? NSDictionary {
                if let foods = report["foods"] as? NSArray {
                    if let firstName = foods[0] as? NSDictionary {
                        if let name = firstName["name"] as? NSString {
                            let foodName = name as String
                            //self.foodList.name = "Name: \(foodName)"
                            println("Food name: \(foodName)")
                        }
                        if let nutrients = firstName["nutrients"] as? NSArray {
                            if let firstNutrientEntry = nutrients[0] as? NSDictionary {
                                if let calories = firstNutrientEntry["value"] as? String {
                                    //self.foodList.calories = "Calories: \(calories)"
                                    println("Calories \(calories)")
                                }
                            }
                        }
                    }
                }
            }
            
            //self.nameTxt = self.foodList.name!
            //self.caloriesTxt = self.foodList.calories!
            
            if (err != nil) {
                println("JSON Error \(err!.localizedDescription)")
            }
            
            dispatch_async(dispatch_get_main_queue(), {
                //self.nameLabel.text = self.nameTxt
                //self.caloriesLabel.text = self.caloriesTxt
            })
        })
        jsonQuery.resume()
    }
}
