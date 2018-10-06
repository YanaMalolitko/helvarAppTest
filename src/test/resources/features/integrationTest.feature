Feature: integration test of devices storage app
  I should be able to send devices information
  as JSON file, stored it redis database
  and get the data in a correct format



  Scenario Outline: data sending check
    Given The JSON "initialLoad.json" file is sent to Redis
    When I see that 2 devices are in the DB
    Then I request the information about all devices
    And receive full "<id>" "<information>" data
    And for concrete "<id>" the device "<information>" is correct
    Examples:
      | id   | information |
      | 1000 | Device A    |
      | 1001 | Device B    |


  Scenario Outline: data updating check
    Given The JSON "fileUpdated.json" file is sent to Redis
    When I see that 3 devices are in the DB
    Then I request the information about all devices
    And receive full "<id>" "<information>" data
    And for concrete "<id>" the device "<information>" is correct
    Examples:
      | id   | information |
      | 1000 | Device A updated|
      | 1001 | Device B    |
      | 1002 | Device C    |

  Scenario: data deletion check
    When I delete the data by "helvar" key from DB
    Then I see that 0 devices are in the DB


  Scenario: incorrectly formatted data sending check
    When I send the devices information in a wrong format
      | headerDeleted.json  |
      | payloadDeleted.json |
      | payloadNoId.json    |
      | payloadNoInfo.json  |
      | payloadNoArray.json |
    Then I get the error message
    And I see that 0 devices are in the DB






