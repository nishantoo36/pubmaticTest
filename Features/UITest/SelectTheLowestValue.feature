Feature: SelectTheLowestValue


  Scenario: Test Validation for invalid UserName with correct format
    When user open "https://www.easemytrip.com/"
    When user First Select Location from default values
    When user select date as "09/09/2020"
    Then It should suggest the date which is lowest in list and nearest to the given date










