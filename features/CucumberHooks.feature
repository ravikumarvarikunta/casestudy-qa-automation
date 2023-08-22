@CucumberHooks
Feature: Sauce Demo End To End Testing

  @TestCase1
  Scenario: Add Items to cart in saucedemo website
    Given I verify the user is in correct website
    When I login with standard user credentails
    Then I verify login is successfull
    When I add multiple items to cart
    Then I verify the items are added to cart
    Then I logout of the application
