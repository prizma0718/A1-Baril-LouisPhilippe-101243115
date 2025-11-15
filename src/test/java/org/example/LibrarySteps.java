package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySteps {
    private int result;
    private boolean success;
    private Main program;

    // 1. A1_Scenario
    @Given("two users {string} and {string}")
    public void a1_scenario_init(String user1, String user2) {
        program = new Main();
        program.InitializeLibrary();
        program.InitializeUsers();

        program.SetCurrentUser(user1);
    }

    @When("user {string} borrow the book {int}")
    public void user_borrow_the_book(String username, int bookId) {
        success = program.bookBorrow(bookId, username);
    }

    @Then("that book {int} becomes unavailable to {string}")
    public void that_book_becomes_unavailable_to_user(int bookId, String username) {
        assertTrue(success); // If borrow function successful
        assertTrue(program.GetBook(bookId).isCheckedOut()); // If book borrowed on the system
        assertFalse(program.bookBorrow(bookId, username)); // If another user can borrow the book
    }

    @When("user {string} return the book {int}")
    public void user_return_a_book(String username, int bookId) {
        success = program.bookReturn(bookId, username);
    }

    @Then("that book {int} becomes available to {string}")
    public void that_book_becomes_available_to_user(int bookId, String username) {
        assertTrue(success); // If return function successful
        assertFalse(program.GetBook(bookId).isCheckedOut()); // Make sure the book is available
        assertTrue(program.bookBorrow(bookId, username)); // Make sure Bob can borrow the book
    }

    // 2. Multiple Holds Queue Processing
    @Given("user {string} borrowing book {int}")
    public void init_multiple_holds_queue_processing(String username, int bookId) {
        program = new Main();
        program.InitializeLibrary();
        program.InitializeUsers();

        program.SetCurrentUser(username);
        program.bookBorrow(bookId, username); // User is borrowing the first book
    }

    @When("user {string} place a hold on the book {int}")
    public void book_hold(String username, int bookId) {
        success = program.bookHold(bookId,username);
    }

    @Then("that book {int} is held from user {string}")
    public void book_hold_success(int bookId, String username) {
        assertTrue(success); // Return of the command
        assertTrue(program.GetBookHold(bookId, username)); // Check if Alice is in the holdqueue
    }

    @When("another user {string} try to place a hold on that book {int}")
    public void book_another_hold(String username, int bookId) {
        success = program.bookHold(bookId,username);
    }

    @Then("that user {string} is placed on a fifo queue of the book {int}")
    public void book_hold_queue_success(String username, int bookId) {
        assertTrue(success); // Return of the command
        assertEquals(program.getHoldQueue(bookId).get(2), username); // Check if the holder is in the appropriate spot in the list
    }

    @When("the book {int} becomes available from user {string}")
    public void book_available_queue(int bookId, String username) {
        success = program.bookReturn(bookId, username);
    }

    @Then("the first user in the fifo queue {string} is alerted of the book {int}")
    public void book_fifo_alert(String username, int bookId) {
        assertTrue(success); // Return of the command
        assertFalse(program.GetBook(bookId).isCheckedOut()); // The book is not checked out anymore

        // Match the expected display prompt
        assertEquals(("NOTICE: The book " + program.GetBook(bookId).getTitle() + " is now available.\n"), program.hasNotification(username));
    }
    @Then("only the user {string} can borrow that book {int}")
    public void book_fifo_borrow(String username, int bookId) {
        ArrayList<String> queue = program.getHoldQueue(bookId); // HoldQueue
        assertFalse(program.bookBorrow(bookId, queue.get(1))); // Bob cannot borrow it
        assertEquals(username, queue.getFirst()); // Make sure Alice is the user on the top of the hold list
    }

    @When("that user {string} borrow that book {int}")
    public void book_fifo_user_borrow(String username, int bookId) {
        success = program.bookBorrow(bookId, username);
    }

    @Then("that book {int} is held from the next user {string} in fifo queue")
    public void book_fifo_user_hold(int bookId, String username) {
        assertTrue(success); // Return of the command
        assertEquals(username, program.getHoldQueue(bookId).get(1));
    }

    // 3. Borrowing Limit and Hold Interactions
    @Given("user {string} borrowing book three books and {string} holding book {int}")
    public void init_borrowing_limit_and_hold_interactions(String user1, String user2, int bookId) {
        program = new Main();
        program.InitializeLibrary();
        program.InitializeUsers();

        program.SetCurrentUser(user1);

        // 3 Books are borrowed from the same user and 1 is held from another user
        program.bookBorrow(1, user1);
        program.bookBorrow(2, user1);
        program.bookBorrow(3, user1);
        program.bookHold(bookId, user2);
    }

    @When("user {string} try to borrow a fourth book {int}")
    public void borrow_above_limit(String username, int bookId) {
        success = program.bookBorrow(bookId, username);
    }

    @Then("that book cannot be borrowed")
    public void book_borrow_failure() {
        assertEquals(3, program.getBorrowedBooksCount());
        assertTrue(program.isAtBorrowingLimit());
        assertFalse(success);
    }

    @Then("that user {string} can borrow another book {int}")
    public void book_borrow_success(String username, int bookId) {
        assertTrue(success);
        assertFalse(program.isAtBorrowingLimit());
        assertTrue(program.bookBorrow(bookId, username));
    }

    @When("user {string} will be back in the session")
    public void book_borrow_success(String username) {
        program.SetCurrentUser(username);
    }

    // 4. No Books Borrowed Scenario
    @Given("the user {string} with no books")
    public void init_no_books_borrowed_scenario(String username) {
        program = new Main();
        program.InitializeLibrary();
        program.InitializeUsers();

        program.SetCurrentUser(username);
    }

    @When("the user {string} does not have books")
    public void user_no_books(String username) {
        result = program.getBorrowedBooksCount();
    }

    @Then("the system display a no books prompt")
    public void prompt_no_books() {
        assertEquals(0, result);
        assertEquals("no books currently borrowed.", program.noAvailableBooksNotification());
    }

    @Then("the system do not display a no books prompt")
    public void prompt_books() {
        assertTrue(success);
        assertEquals("", program.noAvailableBooksNotification());
    }

    @Then("the system display all books as available")
    public void book_available_all() {
        assertTrue(success);
        assertEquals(20,program.getAvailableBooksCount());
    }
}
