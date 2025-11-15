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

  Scenario Outline: multiple_holds_queue_processing
    Given user <user3> borrowing book <bookId>
    When user <user1> place a hold on the book <bookId>
    Then that unavailable book <bookId> is held from user <user1>
    When another user <user2> try to place a hold on that book <bookId>
    Then that user <user2> is placed on a fifo queue of the book <bookId>
    When the book <bookId> becomes available from user <user3>
    Then the first user in the fifo queue <user1> is alerted of the book <bookId>
    And only the user <user1> can borrow that book <bookId>
    When that user <user1> borrow that book <bookId>
    Then that book <bookId> is held from the next user <user2> in fifo queue

    Examples:
      | user1 | user2 | user3 | bookId |
      | "alice"    | "bob"    | "charlie" | 1 |

   Scenario Outline: borrowing_limit_and_hold_interactions
    Given user <user1> borrowing book three books and <user2> holding book <bookId>
    When user <user1> try to borrow a fourth book <bookHold>
    Then that book cannot be borrowed
    When user <user1> place a hold on the book <bookHold>
    Then that available book <bookHold> is held from user <user1>
    When user <user1> return the book <bookId>
    Then that user <user1> can borrow another book <bookId2>
    When user <user2> will be back in the session
    Then the first user in the fifo queue <user2> is alerted of the book <bookId>

     Examples:
       | user1 | user2 | bookHold | bookId | bookId2 |
       | "alice"    | "bob"    | 4 | 1 | 5           |

  Scenario Outline: no_books_borrowed_scenario
    Given the user <user> with no books
    When the user <user> does not have books
    Then the system display a no books prompt
    When user <user> borrow the book <bookId>
    Then the system do not display a no books prompt
    When user <user> return the book <bookId>
    Then the system display all books as available

    Examples:
      | user | bookId |
      | "alice"    | 1    |

