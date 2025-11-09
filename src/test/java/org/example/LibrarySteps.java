package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySteps {
    private int result;
    private Main program;

    private Scanner input = new Scanner(System.in);
    private PrintWriter output = new PrintWriter(System.out);

    // 1. A1_Scenario
    @Given("2 users and 1 book")
    public void init_a1_scenario() {
        program = new Main();
        program.InitializeLibrary();
        program.Authentication(input, output);
        program.Start(input, output); // Start with the Session above
    }

    @When("1 user borrow a book")
    public void book_borrow(int num1, int num2) {

    }

    @Then("that book becomes unavailable to everyone")
    public void book_unavailable(int expected) {

    }

    @When("1 user return a book")
    public void book_return(int num1, int num2) {

    }

    @Then("that book becomes available to everyone")
    public void book_available(int expected) {

    }

    // 2. Multiple Holds Queue Processing
    @Given("3 users and 1 book")
    public void init_multiple_holds_queue_processing() {
        program = new Main();
        program.InitializeLibrary();
        program.Authentication(input, output);
        program.Start(input, output); // Start with the Session above
    }

    @When("1 user place a hold on a book")
    public void book_hold(int num1, int num2) {

    }

    @Then("that book can be held")
    public void book_hold_success(int expected) {

    }

    @When("another user try to place a hold on that book")
    public void book_another_hold(int num1, int num2) {

    }

    @Then("that user is placed on a fifo queue")
    public void book_hold_queue_success(int expected) {

    }

    @When("the book becomes available")
    public void book_available_queue(int num1, int num2) {

    }

    @Then("the first user in the fifo queue is alerted")
    public void book_fifo_alert(int expected) {

    }
    @Then("only that user can borrow that book")
    public void book_fifo_borrow(int expected) {

    }

    @When("that user borrow that book")
    public void book_fifo_user_borrow(int num1, int num2) {

    }

    @Then("that book is held from the next user in fifo queue")
    public void book_fifo_user_hold(int expected) {

    }


    // 3. Borrowing Limit and Hold Interactions
    @Given("1 user with 3 books")
    public void init_borrowing_limit_and_hold_interactions() {
        program = new Main();
        program.InitializeLibrary();
        program.Authentication(input, output);
        program.Start(input, output); // Start with the Session above
    }

    @When("1 user try to borrow a 4th book")
    public void borrow_below_limit(int num1, int num2) {

    }

    @Then("that book cannot be borrowed")
    public void book_borrow_failure(int expected) {

    }

    @Then("that user can borrow another book")
    public void book_borrow_success(int expected) {

    }

    // 4. No Books Borrowed Scenario
    @Given("3 users with no books")
    public void init_no_books_borrowed_scenario() {
        program = new Main();
        program.InitializeLibrary();
        program.Authentication(input, output);
        program.Start(input, output); // Start with the Session above
    }

    @When("the user does not have books")
    public void user_no_books(int num1, int num2) {

    }

    @Then("the system display a prompt")
    public void prompt_no_books(int expected) {

    }

    @Then("the system display all books as available")
    public void book_available_all(int expected) {

    }
}
