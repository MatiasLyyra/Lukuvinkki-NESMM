Feature: User can mark tip as read

  Scenario: user can mark tip as read
    Given there are some tips created
    And   command view tips is selected
    When  command Mark as Read is selected
    Then  page shows tip marked as read