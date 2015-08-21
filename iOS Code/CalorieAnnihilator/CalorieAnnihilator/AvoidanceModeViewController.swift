//
//  AvoidanceModeViewController.swift
//  CalorieAnnihilator
//
//  Created by Azadi on 8/17/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class AvoidanceModeViewController: UIViewController {

    @IBOutlet weak var searchDatabaseBtn: UIButton!

    @IBOutlet weak var searchBtn: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        initButtons()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func initButtons() {
        searchBtn.layer.cornerRadius = 10
    }
    
    @IBAction func saerchDatabaseClick(sender: AnyObject) {
        JSONQuery.queryFoodDatabase()
    }
}
