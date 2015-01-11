//
//  AvoidanceModeViewController.m
//  CalorieAnnihilator
//
//  Created by Azadi on 1/10/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

#import "AvoidanceModeViewController.h"

@interface AvoidanceModeViewController ()

@end

@implementation AvoidanceModeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    _annihilateBtn.layer.borderWidth=1.0f;
    _annihilateBtn.layer.borderColor=[[UIColor whiteColor] CGColor];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)annihilateClick:(UIButton *)sender
{
    NSString *temp1 = @"http://api.data.gov/usda/ndb/search/?format=json&q=";
    NSString *temp2 = _searchTxt.text;
    NSString *temp3 = @"&max=25&offset=0&api_key=DEMO_KEY";
    NSString *formattedURL = [NSString stringWithFormat:@"%@/%@/%@", temp1, temp2, temp3];
    NSURL *url = [NSURL URLWithString:formattedURL];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    NSURLResponse *response = NULL;
    NSError *requestError = NULL;
    NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&requestError];
    NSString *responseString = [[NSString alloc] initWithData:responseData encoding: NSUTF8StringEncoding];
    NSLog(@"%@", responseString);

}
@end
