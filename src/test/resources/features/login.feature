@regression @login
Feature: Login page

  Scenario: Successful login
    Given user is on login page
    When user enters valid username and valid password
    Then user is on "Products" page

  Scenario: Unsuccessful login
    Given user is on login page
    When user enters invalid username and invalid password
    Then user sees the login error message