//
//  BingeModeViewController.swift
//  CalorieAnnihilator
//
//  Created by Azadi on 8/17/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class BingeModeViewController: UIViewController {

    @IBOutlet weak var damageBtn: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        initButtons()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func initButtons() {
        damageBtn.layer.cornerRadius = 10;
    }
    
    @IBAction func bingeClick(sender: AnyObject) {
        JSONQuery.queryFoodDatabase()
    }
}