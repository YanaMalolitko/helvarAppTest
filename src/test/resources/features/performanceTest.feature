Feature: performance test of devices storage app
  I should be able to send devices information
  as JSON file, stored it redis database
  and get the data in a correct format


  Scenario: data updating check
    When I send JSON file is sent to Redis 1000 times
    Then it is successfully added
    And  I delete the data by "helvar" key from DB