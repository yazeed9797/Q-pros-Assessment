Feature: UI Automation for Demoblaze

  Scenario: Register and Login Pop-up Menu
    Given I am on the Demoblaze homepage
    When I register with a valid username and password
    And I login with the registered credentials
    Then I should be logged in successfully

  Scenario: Check Categories have Items
    Given I am logged in
    When I navigate to the Categories page
    Then I should see items listed under each category

  Scenario: Add Random Item to Cart
    Given I am logged in
    When I add a random item to the cart
    Then the item should be added successfully

  Scenario: Remove Item from Cart
    Given I have items in my cart
    When I remove an item from the cart
    Then the item should be removed successfully

  Scenario: Complete Successful Checkout with Random Item
    Given I have items in my cart
    When I proceed to checkout
    And I complete the checkout process
    Then the checkout should be successful
