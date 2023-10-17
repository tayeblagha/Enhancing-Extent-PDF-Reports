@Google

Feature: Seach on google

  Background:
    Given I Connect to google

  Scenario: Search on google chrome
#    When  I delete the facture
    When I go to google
    And I put my search
    Then I should be presented with the successful result



