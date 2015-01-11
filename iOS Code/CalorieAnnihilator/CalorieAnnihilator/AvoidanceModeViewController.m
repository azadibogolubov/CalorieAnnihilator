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
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)annihilateClick:(UIButton *)sender
{
    // Prepare URL for processing...
    NSString *temp1 = @"http://api.data.gov/usda/ndb/search/?format=json&q=";
    NSString *temp2 = _searchTxt.text;
    NSString *temp3 = @"&max=1&offset=0&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf";
    NSString *formattedURL = [NSString stringWithFormat:@"%@/%@/%@", temp1, temp2, temp3];
    
    // Construct the URL and issue request...
    NSURL *url = [NSURL URLWithString:formattedURL];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    NSURLResponse *response = NULL;
    NSError *requestError = NULL;
    
    // Get the response data and parse into JSON...
    NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&requestError];
    
    NSDictionary* object = [NSJSONSerialization
                       JSONObjectWithData:responseData
                       options:0
                       error:nil];
    NSDictionary* list = [object objectForKey:@"list"];
    NSArray* item = [list objectForKey:@"item"];
    NSString* name = [item[0] objectForKey:@"name"];
    NSLog(@"Name: %@\n NDB #: %@", name, [item[0] objectForKey:@"ndbno"]);
}
@end
