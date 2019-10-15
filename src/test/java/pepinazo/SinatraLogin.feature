Feature: Sinatra Login

Feature: Sinatra Login Parameters

  Scenario: Login Happy Path

    Given I have open the browser

    When I open Sinatra website

    And I log into Sinatra with frank and sinatra

    Then Sinatra Home Page must be displayed

  Scenario: Login empty fields

    Given I have open the browser

    When I open Sinatra website

    And I log into Sinatra with empty fields

    Then I can see Sinatra Login Page

    And An error is displayed


  Scenario Outline: User Can't log into Sinatra with bad Username and Password Combination

    Given I have open the browser
    When I open Sinatra website
    And I log into Sinatra with <username> and <password>
    Then I can see Sinatra Login Page
    And An error is displayed

    Examples:
      | username | password |
      | frenk    | sinotra  |
      | frenk    | sinatra  |
      | frank    | sinotra  |
