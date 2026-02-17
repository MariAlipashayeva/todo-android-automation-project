#Feature: Create Tasks
#  Scenario Outline: Create Valid Task
#    Given the user is on the task creation screen
#    When the user clicks on the "Create Task" button
#    And the user enters a title as "<taskTitle>"
#    And the user enters a description as "<description>"
#    And the user submits the task
#    Then the task should be successfully created with "<msg>"
#    And the user is back on the task creation screen
#
#    Examples:
#      | taskTitle | description                | msg        |
#      | Task 1    | Automation should be done. | Task added |
#      | Task 2    | Write unit tests.          | Task added |
#      | Task 3    | Deploy to production.      | Task added |
#
#  Scenario Outline: Cancel Task Creation
#    Given the user is on the task creation screen
#    When the user clicks on the "Create Task" button
#    And the user enters a title as "<taskTitle>"
#    And the user enters a description as "<description>"
#    And the user clicks "Return" button
#    Then the task should be successfully created with "<msg>"
#
#    Examples:
#      | taskTitle  | description            | msg  |
#      |Task2       |Automation Text2        | Task added               |
#      |            | Automation task 1      |   |
#      | Task1      |                        |  |

Feature: Task Management

  Scenario: Create Multiple Tasks in One Session
    Given the user is on the task creation screen
    When the user creates the following tasks:
      | taskTitle              | description                           |
      | Setup CI/CD Pipeline   | Configure Jenkins for automated builds |
      | Write Unit Tests       | Achieve 80% code coverage             |
      | Code Review            | Review PR #456 from dev team          |
      | Deploy to Staging      | Deploy version 2.3.0 to staging env   |
      | Update Documentation   | Add API endpoint documentation        |
      | Fix Login Bug          | Resolve issue with OAuth redirect     |
      | Refactor User Service  | Improve performance and readability   |
      | Security Audit         | Run penetration testing               |
      | Database Migration     | Migrate to PostgreSQL 15              |
      | Performance Testing    | Load test checkout flow               |
      | Performance Testing    | Load test checkout flow               |
      | Performance Testing    | Load test checkout flow               |
      | Refactor User Service  | Improve performance and readability   |
      | Security Audit         | Run penetration testing               |
    Then all tasks should be created successfully

  Scenario Outline: Create Single Task
    Given the user is on the task creation screen
    When the user clicks on the "Create Task" button
    And the user enters a title as "<taskTitle>"
    And the user enters a description as "<description>"
    And the user submits the task
    Then the task should be successfully created with "<msg>"
    And the user clicks "Return" button

    Examples:
      | taskTitle      | description                                                                                                    | msg        |
      | Empty Task     |                                                                                                                | Task added |
      | Long Title     | This is a very long description that tests the maximum character limit of the description field in the app    | Task added |