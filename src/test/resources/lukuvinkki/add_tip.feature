Feature: User can create a new tip

  Scenario: a proper form for adding tips exists
    Given command new tip is selected
    Then  a proper form with title, author, url and description is shown

  Scenario: user can create a new tip with some values
    Given command new tip is selected
    When  title "testtitle", author "testauthor", url "testurl" and description "testdesc" are given
    And command view tips is selected
    Then  a new tip is created with title "testtitle", author "testauthor", url "testurl" and description "testdesc"

  Scenario: user can create a new tip with tags
    Given command new tip is selected
    When title "Some book", author "Shakespeare", url "https://example.com", description "Foobar" and tags "book; tag; classics" are given
    And command view tips is selected
    Then following tags are found in newly created tip:
    |book|tag|classics|
#Scenario: user can not create a new tip without any values
#    Given command new tip is selected
#    When  title "", author "", url "" and description "" are given
#    Then  no new tip is created
