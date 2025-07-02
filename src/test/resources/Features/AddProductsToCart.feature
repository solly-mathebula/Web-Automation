Feature: Add Products to Cart

  As a shopper on Takealot
  I want to add products to my shopping cart
  So that I can purchase them later

  Background:
    Given I am on the Takealot homepage

  Scenario: Add K3 Mechanical Keyboard to cart
    When I search for K3 mechanical keyboard
    And I select the K3 mechanical keyboard from the search results
    And I choose the Blue Switch keyboard option
    And I click the Add to Cart button
    Then I should see a confirmation message with text Item added to cart
    And I should see the Go to Cart button

  Scenario Outline: Add multiple products to cart
    When I search for <Item>
    And I select the <Item> from the search results
    And I click the Add to Cart button
    Then I should see a confirmation message with text Item added to cart
    And I should see the Go to Cart button

    Examples:
    |Item|
    |Dell Monitor|
    |HP Mouse    |
    |Rabbit Mouse|
