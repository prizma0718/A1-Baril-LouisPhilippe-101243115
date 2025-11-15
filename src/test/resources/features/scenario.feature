Feature: Book Library Operations
  As a borrower
  I want to perform basic library operations
  So that I can borrow books on my name

  Scenario: A1_scenario
    Given two users "alice" and "bob"
    When user "alice" borrow the book 1
    Then that book 1 becomes unavailable to "bob"
    When user "alice" return the book 1
    Then that book 1 becomes available to "bob"

  Scenario: multiple_holds_queue_processing
    Given user "charlie" borrowing book 1
    When user "alice" place a hold on the book 1
    Then that book 1 is held from user "alice"
    When another user "bob" try to place a hold on that book 1
    Then that user "bob" is placed on a fifo queue of the book 1
    When the book 1 becomes available from user "charlie"
    Then the first user in the fifo queue "alice" is alerted of the book 1
    And only the user "alice" can borrow that book 1
    When that user "alice" borrow that book 1
    Then that book 1 is held from the next user "bob" in fifo queue

  Scenario: borrowing_limit_and_hold_interactions
    Given user "alice" borrowing book three books and "bob" holding book 1
    When user "alice" try to borrow a fourth book 4
    Then that book cannot be borrowed
    When user "alice" place a hold on the book 4
    Then that book 4 is held from user "alice"
    When user "alice" return the book 1
    Then that user "alice" can borrow another book 5
    When user "bob" will be back in the session
    Then the first user in the fifo queue "bob" is alerted of the book 1

  Scenario: no_books_borrowed_scenario
    Given the user "alice" with no books
    When the user "alice" does not have books
    Then the system display a no books prompt
    When user "alice" borrow the book 1
    Then the system do not display a no books prompt
    When user "alice" return the book 1
    Then the system display all books as available