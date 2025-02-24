
Feature: Order Placement for Ecommerce Website 
  I want to use this template for my feature file


 Background: 
    Given I landed on Ecommerce Website
  @Regression 
  Scenario Outline: Positive testcase for Purchase Order 
    Given Logged in with username <username> and password <password>
    When I added the product <product> to cart and click on Checkout
    And  Click on Place Order 
    Then Verify "THANKYOU FOR THE ORDER." message is displayed 

    Examples: 
      | username             | password          | product        |
      | santhosh42@gmail.com |  Sanrahul324@#    | QWERTY         |
 
