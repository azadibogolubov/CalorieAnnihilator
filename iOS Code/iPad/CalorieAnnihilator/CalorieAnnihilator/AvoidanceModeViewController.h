//
//  AvoidanceModeViewController.h
//  CalorieAnnihilator
//
//  Created by Azadi on 1/10/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AvoidanceModeViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *searchTxt;

- (IBAction)annihilateClick:(UIButton *)sender;

@end

