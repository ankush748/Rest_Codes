Feature: Login 
Scenario: Login Functionality
Given user navigates to the website javatpoint.com
And there user logs in through Login Window by using Username as "USER" and Password as "PASSWORD"
Then login must be successful.

Scenario: Login Functionality
Given user navigates to the website javatpoint.com
And there user logs in through Login Window by using Username as "USER1" and Password as "PASSWORD1"
Then login must be successful.

Scenario: Login Functionality for Krishna
Given user navigates to the website javatpoint.com
And there user logs in through Login Window by using Username as "krishna" and Password as "pass@123456"
Then login must be successful.