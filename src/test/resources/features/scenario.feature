#code is based on cucumber.io examples
Feature: Calculator Operations
  As a user
  I want to perform basic arithmetic operations
  So that I can calculate values

  Scenario: Add two numbers
    Given I have a calculator
    When I add 5 and 3
    Then the result should be 8

  Scenario: Subtract two numbers
    Given I have a calculator
    When I subtract 3 from 10
    Then the result should be 7

  Scenario Outline: Multiple additions
    Given I have a calculator
    When I add <num1> and <num2>
    Then the result should be <expected>

    Examples:
      | num1 | num2 | expected |
      | 1    | 1    | 2        |
      | 10   | 20   | 30       |
      | -5   | 5    | 0        |
      | 100  | 200  | 300      |

  Scenario Outline: Multiple subtractions
    Given I have a calculator
    When I subtract <num2> from <num1>
    Then the result should be <expected>

    Examples:
      | num1 | num2 | expected |
      | 10   | 5    | 5        |
      | 20   | 8    | 12       |
      | 0    | 0    | 0        |
      | 5    | 10   | -5       |
