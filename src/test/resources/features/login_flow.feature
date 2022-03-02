Feature: HealthAsyst Login
In order to access CheckInAsyst Application
As a login user account
I want to login to CheckInAsyst Application

Background:
Given I open 'dashBoardCheckinAsyst' url

@Testcase_10001
Scenario Outline: Login to Dashboard
When I login with "<userName>" and "<password>"
And I click on login button
Then I verify respective "<userName>" is logged in

Examples:
|userName 										 |password |
|ReceptionHA100@healthasyst.com|Rece@9999|