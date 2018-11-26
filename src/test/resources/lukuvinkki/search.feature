Feature: User can search tips

  Scenario: user finds tip with proper tag name
    Given tip is created with tag "supertag"
    And   command search is selected
    When  search is done with keyword "supertag"
    Then  list contains tip with tag "supertag"

  Scenario: user doesnt find tip with miswritten tag name
    Given tip is created with tag "elamankerta"
    And   command search is selected
    When  search is done with mismatching keyword "elamakerta"
    Then  list doesnt contain tip with tag "elamankerta"

  Scenario: user sees tips sorted by creation time after search
    Given there are some tips created
    And   command search is selected
    When  search is done with keyword ""
    Then  page contains a list of tips sorted by creation time

  Scenario: search shows tag matches before matches by other fields
    Given there are some tips created
    And   command search is selected
    When  search is done with keyword "foo"
    Then  page contains a list of tips with tag matches shown first