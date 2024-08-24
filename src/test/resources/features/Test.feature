Feature: Login to Salesforce Application

  @login @positive
  Scenario: Login with valid credentials

    Given I log into Salesforce API
    When I create an Account
    Then the Account Id is not null
