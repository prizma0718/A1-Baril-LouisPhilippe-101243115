Feature: Book Library Operations
  As a borrower
  I want to perform basic library operations
  So that I can borrow books on my name

  Scenario: A1_scenario
    Given 2 users and 1 book
    When 1 user borrow a book
    Then that book becomes unavailable to everyone
    When 1 user return a book
    Then that book becomes available to everyone

  Scenario: multiple_holds_queue_processing
    Given 3 users and 1 book
    When 1 user place a hold on a book
    Then that book can be held
    When another user try to place a hold on that book
    Then that user is placed on a fifo queue
    When the book becomes available
    Then the first user in the fifo queue is alerted
    And only that user can borrow that book
    When that user borrow that book
    Then that book is held from the next user in fifo queue

  Scenario: borrowing_limit_and_hold_interactions
    Given 1 user with 3 books
    When 1 user try to borrow a 4th book
    Then that book cannot be borrowed
    And that book can be held
    When 1 user return a book
    Then that user can borrow another book
    And the first user in the fifo queue is alerted

  Scenario: no_books_borrowed_scenario
    Given 3 users with no books
    When the user does not have books
    Then the system display a prompt
    When 1 user return a book
    Then the system display a prompt
    When 1 user borrow a book
    Then the system display all books as available