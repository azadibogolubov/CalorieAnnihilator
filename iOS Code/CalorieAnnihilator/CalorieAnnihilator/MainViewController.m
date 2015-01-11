//
//  ViewController.m
//  CalorieAnnihilator
//
//  Created by Azadi on 1/10/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

#import "MainViewController.h"

@interface MainViewController ()

@end

@implementation MainViewController

- (void)viewDidLoad
{
    [super viewDidLoad];

    _avoidanceModeBtn.layer.borderWidth=1.0f;
    _avoidanceModeBtn.layer.borderColor=[[UIColor whiteColor] CGColor];

    _bingeModeBtn.layer.borderWidth=1.0f;
    _bingeModeBtn.layer.borderColor=[[UIColor whiteColor] CGColor];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
