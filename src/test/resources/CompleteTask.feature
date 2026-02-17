Feature: Dynamic Task Completion

  Background:
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
    Then all tasks should be created successfully

  Scenario Outline: Complete first N tasks
    When the user completes the first <count> tasks
    Then <count> tasks should be marked as completed

    Examples:
      | count |
      | 2    |


  Scenario Outline: Complete N random tasks

    When the user completes <count> random tasks
    And <count> tasks should be marked as completed
    And the user clicks on the "More" button
    And the user clicks on the "Refresh" button
    And the user clicks on the "Clear completed" button
    Then completed task should be removed from list

    Examples:
      | count |
      | 5   |

    