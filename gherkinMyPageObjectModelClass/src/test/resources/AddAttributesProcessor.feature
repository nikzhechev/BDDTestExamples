Feature: Add Attributes items
  On admin page add attributes
  select Processor items

@regression @positive
  Scenario: Valid Add Attributes
    Given admin is on the admin area page
    And login whit admin "admin" and "parola123!"
    When he is on admin area he go to Attributes page
    And he click add New button
    And he enter "Core i7 V Pro" select processor enter sort order
    And he Submits new Attributes
    Then ensure the new Attributes is complete with "Success: You have modified attributes!" message

  @positive
  Scenario:After create then Dell Attributes
    Given admin is on the admin area page
    And login whit admin "admin" and "parola123!"
    When  he is on admin area he go to Attributes page
    And he enter "Core i7 V Pro" of Attributes to dell
    And he click button dell
    Then ensure the Attributes is complete dell whit "Success: You have modified attributes!" message

    @negative
      Scenario:Add Attributes without Attributes Group
      Given admin is on the admin area page
      And login whit admin "admin" and "parola123!"
      When he is on admin area he go to Attributes page
      And he click add New button
      And he enter "Core i7 V Pro" and not select group and enter Sort Order
      And he Submits new Attributes
      Then ensure the new Attributes is not complete with "Attribute Group Required!" message

      @negative
        Scenario Outline: Add Attributes whit name over 64 character
        Given admin is on the admin area page
        And login whit admin "admin" and "parola123!"
        When he is on admin area he go to Attributes page
        And he click add New button
        And he enter "<name>" select processor enter sort order
        And he Submits new Attributes
        Then ensure the new AttributesName is not complete with "<expMessages>" message
    Examples:
      | name                                                                 | expMessages                                         |
      | over64CharEnterFieldAttributesNameover64CharEnterFieldAttributesN    | Attribute Name must be between 1 and 64 characters! |
      | over64CharEnterFieldAttributesNameover64CharEnterFieldAttributesName | Attribute Name must be between 1 and 64 characters! |



