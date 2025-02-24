
Feature: Error Validation
  I want to use this template for my feature file

  @Error
  Scenario Outline: Login Page Error Validation
    Given I landed on Ecommerce Website
    When  Logged in with username <username> and password <password>
    Then  "Incorrect email or password." message is displayed 

    Examples: 
      | username             | password          | 
      | santhosh2@gmail.com  |  Sanrahul324@#    | 
