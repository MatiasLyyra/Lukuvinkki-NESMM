Feature: User can add a new comment
  Scenario: comment is added to a tip
    Given there are some tips created
    And user is on a tip a page
    When new comment is created with nickname "Foo Bar" and content "Lorem ipsum"
    Then comment is found with nickname "Foo Bar" and content "Lorem ipsum"