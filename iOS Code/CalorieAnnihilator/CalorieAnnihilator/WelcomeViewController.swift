//
//  WelcomeViewController.swift
//  CalorieAnnihilator
//
//  Created by Azadi on 8/17/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class WelcomeViewController: UIViewController {

    @IBOutlet weak var avoidanceBtn: UIButton!
    @IBOutlet weak var bingeBtn: UIButton!
    @IBOutlet weak var aboutBtn: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initButtons()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func initButtons() {
    }
}
