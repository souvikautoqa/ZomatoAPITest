Feature: Zomato API Tests

  Scenario Outline: As an user I want to search for restaurants in my city
    Given I am in my <City>
    When I set mandatory fields for city search
    And I send the request
    Then I should receive response code 200
    When I set mandatory fields for restaurant search in <City>,<Country>
    And I send the request
    Then I should receive response code 200
    And I should be able to <Verify> the list of restaurant details

    Examples:
      | City | Country | Verify|
      | London | United Kingdom | true|
      | Hawaii | India | false|

  Scenario Outline: As an user I want to search for specific cuisine restaurants in my city
    Given I want to have <Cuisine> in my <CityID>
    When I set mandatory fields for cuisine search
    And I send the request
    Then I should receive response code 200
    When I set mandatory fields for <Cuisine> restaurants search
    And I send the request
    Then I should receive response code 200
    And I should be able to <Verify> the list of <Cuisine> restaurants

    Examples:
      | Cuisine | CityID | Verify|
      | Chinese | 61 | true |

  Scenario Outline: As an user I want to search for restaurants in my city by specific category
    Given I want to find restaurants with specific <Categories> in my <CityID>
    When I set mandatory fields for category search
    And I send the request
    Then I should receive response code 200
    When I set mandatory fields for restaurants search by <Categories>
    And I send the request
    Then I should receive response code 200
    And I should be able to <Verify> the list of restaurant details

    Examples:
      | Categories | CityID | Verify|
      | Nightlife | 61 | true |

  Scenario Outline: As an user I want to view reviews of my favourite restaurants
    Given I want to find reviews of the restaurant with <ResID>
    When I set mandatory fields for review search
    And I send the request
    Then I should receive response code 200
    And I should be able to view review ratings for the restaurant

    Examples:
      | ResID |
      | 6102866 |

  Scenario Outline: As an user I want to view the details of my restaurant
    Given I want to find the details of the restaurant with <ResID>
    When I set mandatory fields for restaurant details
    And I send the request
    Then I should receive response code 200
    And I should be able to view details of the restaurant

    Examples:
      | ResID |
      | 6102856 |
      | 6103902 |
      | 6102866 |
#      | 6100578 |
#      | 6116786 |
