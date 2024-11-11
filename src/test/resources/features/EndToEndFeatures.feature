Feature: Verify end to end features

  Scenario: Very various request methods
    Given user sets endpoint "/objects"
    And sets header "Content-Type" as "application/json"
    When user sets request body from "create-request.json"
    And performs post call
    Then verify status code is 200
    When user sets endpoint "/objects/@id"
    And perform get call
    Then verify status code is 200
    And verify json value of "data.year" is "2019"
    When user sets endpoint "/objects/@id"
    And sets header "Content-Type" as "application/json"
    When user sets request body from "create-request.json" as pojo with "name" value "Samsung s24"
    And performs put call
    Then verify status code is 200
    And verify json value of "name" is "Samsung s24"
    When user sets endpoint "/objects/@id"
    And sets header "Content-Type" as "application/json"
    And perform delete call
    Then verify status code is 200
    And verify response as "Object with id = @id has been deleted."



