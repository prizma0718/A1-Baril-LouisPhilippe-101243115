package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // TODO: RESP-01 System Book Collection Initialization
    // TODO: RESP_01_01 Book Collection Count
    // Check if the collection contains 20 books
    @Test
    @DisplayName("Book Collection Count Check")
    void RESP_01_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        // The Catalogue should not be empty, with 20 books
        int catalogueSize = program.GetCatalogueSize(); // Get the Deck Size
        assertEquals(20, catalogueSize); // Check for the test
    }

    // TODO: RESP_01_02 Specific Book Information Matching
    // Check if 1th, 10th and 20th book if they are correct
    @Test
    @DisplayName("Book Collection Information Check")
    void RESP_01_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        int catalogueSize = program.GetCatalogueSize(); // Get the Value

        program.OverwriteBook(0, "Whispers in the Fog", "Eleanor Vance", false);
        program.OverwriteBook(9, "When Stars Align", "Nathaniel Grey", false);
        program.OverwriteBook(19, "Letters from the Void", "Samuel Quill", false);

        // Verify the Entry
        boolean noMismatch = false;

        if (program.GetBook(0).getTitle().equals("Whispers in the Fog") && program.GetBook(0).getAuthor().equals("Eleanor Vance")) {
            if (program.GetBook(9).getTitle().equals("When Stars Align") && program.GetBook(9).getAuthor().equals("Nathaniel Grey")) {
                if (program.GetBook(19).getTitle().equals("Letters from the Void") && program.GetBook(19).getAuthor().equals("Samuel Quill")) {
                    noMismatch = true;
                }
            }
        }


        assertTrue(noMismatch && (program.GetCatalogueSize() != 0));
    }

    // TODO: RESP_01_03 All Books Availability
    // Check if all books are listed as available at initialization
    @Test
    @DisplayName("Book Collection Availability Check")
    void RESP_01_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        int catalogueSize = program.GetCatalogueSize();

        // test 2 - should be no duplicate values in deck, Computing
        boolean noMismatch = true;
        for (int i = 0; i < catalogueSize; i++) {
            for (int j = 0; j < program.GetCatalogueSize(); j++) {
                if (program.GetBook(i).getBorrowed()) {
                    noMismatch = false;
                }
            }
        }

        // The Catalogue should return true if all books are marked as available
        assertTrue(noMismatch && (program.GetCatalogueSize() != 0));
    }

    // TODO: RESP-02 System user accounts initialization
    // TODO: RESP_02_01 User Collection Count
    // Check if there are 3 users initialized
    @Test
    @DisplayName("User Collection Count")
    void RESP_02_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        // The Catalogue should not be empty, with 3 users
        int usersSize = program.GetUsersSize(); // Get the Deck Size
        assertEquals(3, usersSize); // Check for the test
    }

    // TODO: RESP_02_02 User Collection Usernames and Passwords
    // Check if their username and passwords are intended
    @Test
    @DisplayName("User Collection Usernames and Passwords")
    void RESP_02_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        // Verify the Entry
        boolean noMismatch = false;

        if (program.GetUser(0).getUsername().equals("user01") && program.GetUser(0).getPassword().equals("pass01")) {
            if (program.GetUser(1).getUsername().equals("user02") && program.GetUser(1).getPassword().equals("pass02")) {
                if (program.GetUser(2).getUsername().equals("user03") && program.GetUser(2).getPassword().equals("pass03")) {
                    noMismatch = true;
                }
            }
        }

        assertTrue(noMismatch && (program.GetUsersSize() != 0));
    }

    // TODO: RESP_02_03 User Collection no borrowing
    // Check if all user are initialized with no borrow
    @Test
    @DisplayName(" User Collection no borrowing")
    void RESP_02_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        int usersSize = program.GetUsersSize();

        // test 2 - should be no duplicate values in deck, Computing
        boolean noMismatch = true;
        for (int i = 0; i < usersSize; i++) {
            if (program.GetUser(i).getBorrowing()) {
                noMismatch = false;
            }
        }

        // The Catalogue should return true if all books are marked as available
        assertTrue(noMismatch && (program.GetUsersSize() != 0));
    }

    // TODO: RESP-03 User Authentication and credentials validation
    // TODO: RESP_03_01 User Authentication Prompt
    // Check if user authentication prompt is shown
    @Test
    @DisplayName("User Authentication Prompt")
    void RESP_03_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "\n"; // No input Yet
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Username:")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_03_02 Password Authentication Prompt
    // Check if password authentication prompt is shown
    @Test
    @DisplayName("Password Authentication Prompt")
    void RESP_03_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "\n"; // No input Yet
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Password:")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_03_03 User Authentication Success
    // Check if user authentication succeeds
    @Test
    @DisplayName("User Authentication Success")
    void RESP_03_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n"; // No input Yet
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Welcome, user01!")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_03_04 User Authentication Failure
    // Check if user authentication fails and retry
    @Test
    @DisplayName("User Authentication Failure")
    void RESP_03_test_04() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user99\npass99\n"; // Invalid credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Login invalid, please retry.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-04 User Session Establishment
    // TODO: RESP_04_01 User Session Establishment
    // Check if user session is set up after authentication
    @Test
    @DisplayName("User Session Establishment")
    void RESP_04_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n"; // Valid Credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (program.GetCurrentUser().getUsername().equals("user01")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // Make More Checks on each Responsibility

    // TODO: RESP-05 Book Availability Real Time Tracking
    // TODO: RESP_05_01 Registered Book Available
    // Check if the registered book is now available at beginning
    @Test
    @DisplayName("Registered Book Available")
    void RESP_05_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n"; // Valid Credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Variable for the hold book check, on the book
        program.setBookHold(6, 0);
        program.GetBook(6).setBorrowed(false); // The book is not borrowed by anyone else

        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above


        boolean assertion = false;
        if (output.toString().contains("Threads of Infinity")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-06 Main Menu Displaying
    // TODO: RESP_06_01 Main Menu Prompt
    // Check if main menu displays correctly
    @Test
    @DisplayName("Main Menu Prompt")
    void RESP_06_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n"; // Valid Credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("----- MAIN MENU -----")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-07 Main Menu Navigation
    // TODO: RESP_07_01 Borrowing Prompt
    // Main menu Options Navigation Process
    @Test
    @DisplayName("Borrowing Book Navigation")
    void RESP_07_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n"; // Valid Credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("----- BORROWING -----")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_07_02 Returning Prompt
    @Test
    @DisplayName("Returning Book Navigation")
    void RESP_07_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n2\n"; // Valid Credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("----- RETURNING -----")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_07_03 Logout Prompt
    @Test
    @DisplayName("Logout Navigation")
    void RESP_07_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n3\n"; // Valid Credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Logout currently in progress...")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_07_04 Error Prompt
    @Test
    @DisplayName("Error Navigation")
    void RESP_07_test_04() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n4\n"; // Invalid Output
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Invalid Choice. Please Retry.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-08 Book Borrowing Initial Display
    // TODO: RESP_08_01 Current Book Count Valid Prompt
    // Check if borrowing books display properly
    @Test
    @DisplayName("Current Book Count Valid Prompt")
    void RESP_08_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // 3 Borrowed Books, should be valid
        program.GetBook(1).setBorrowedId(0);
        program.GetBook(2).setBorrowedId(0);
        program.GetBook(3).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Number of borrowed books: 3")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_08_02 Current Book Count Invalid Prompt
    // Check if borrowing books display properly
    @Test
    @DisplayName("Current Book Count Invalid Prompt")
    void RESP_08_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // 4 Borrowed Books, should be invalid.
        program.GetBook(1).setBorrowedId(0);
        program.GetBook(2).setBorrowedId(0);
        program.GetBook(3).setBorrowedId(0);
        program.GetBook(4).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Too many borrowing books.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_08_03 Book Collection Checked Prompt
    // Check if borrowing collection display properly
    @Test
    @DisplayName("Book Collection Prompt")
    void RESP_08_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Check a book
        program.GetBook(1).setBorrowedId(0);


        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Checked Out")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_08_04 Book Collection Onhold Prompt
    @Test
    @DisplayName("Book Collection Onhold Prompt")
    void RESP_08_test_04() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Onhold a book
        program.GetBook(1).setHoldId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("On Hold")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP-08_05 Book Borrowing Listing Author Prompt
    @Test
    @DisplayName("Book Borrowing Listing Author Prompt")
    void RESP_08_test_05() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Isabelle Marlowe")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP-08_06 Book Borrowing Confirmation Prompt
    @Test
    @DisplayName("Book Borrowing Confirmation Prompt")
    void RESP_08_test_06() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Please confirm the operation.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-08_07 Book Checked by ourself displayed
    @Test
    @DisplayName("Book Checked by ourself displayed")
    void RESP_08_test_07() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.GetBook(7).setBorrowedId(0);

        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("*Checked Out")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-08_08 Book Held by ourself displayed
    @Test
    @DisplayName("Book Held by ourself displayed")
    void RESP_08_test_08() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.GetBook(7).setHoldId(0);

        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("*On Hold")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }


    // TODO: RESP-09 Book Borrowing Validation
    // TODO: RESP_09_01 Book Borrowing Valid
    // In case the book can be borrowed
    @Test
    @DisplayName("Book Borrowing Valid")
    void RESP_09_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Threads of Infinity Book successfully borrowed.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP_09_02 Book Borrowing Invalid if Borrowed
    // In case the book cannot be borrowed
    @Test
    @DisplayName("Book Borrowing Invalid if Borrowed")
    void RESP_09_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Book borrowed by someone else
        program.GetBook(6).setBorrowedId(1);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book borrowing unsuccessful. Someone is borrowing this book.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_09_03 Book Borrowing Invalid if Onhold from others
    @Test
    @DisplayName("Book Borrowing Invalid if Onhold from others")
    void RESP_09_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Book borrowed by someone else
        program.GetBook(6).setHoldId(1);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book borrowing unsuccessful. Someone is onholding this book.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_09_04 Book Borrowing Invalid if Onhold from myself
    // You already have a hold on this book
    @Test
    @DisplayName("Book Borrowing Invalid if Onhold from myself")
    void RESP_09_test_04() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Book borrowed by someone else
        program.GetBook(6).setHoldId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book borrowing unsuccessful. You already have a hold on this book.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_09_05 Book Borrowing Invalid if Checked from myself
    @Test
    @DisplayName("Book Borrowing Invalid if Checked from myself")
    void RESP_09_test_05() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Book borrowed by someone else
        program.GetBook(6).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book borrowing unsuccessful. You already have this book checked out")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_09_06 Book Borrowing Invalid if 3 books or more
    @Test
    @DisplayName("Book Borrowing Invalid if 3 books or more")
    void RESP_09_test_06() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n12\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // Book borrowed by someone else
        program.GetBook(6).setBorrowedId(0);
        program.GetBook(7).setBorrowedId(0);
        program.GetBook(8).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Maximum borrowing limit reached.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_09_07 Book Borrowing Invalid if number outside range
    // Return to the main menu with appropriate message
    @Test
    @DisplayName("Book Borrowing Invalid if number outside range")
    void RESP_09_test_07() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n25\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Please enter a number in the range of 0-20.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }


    // TODO: RESP-10 Book Borrowing Process
    // TODO: RESP_10_01 Setting up the 14-day period
    // So that book is borrowed for next 14 days
    @Test
    @DisplayName("Setting up the 14-day period")
    void RESP_10_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        LocalDate futureDate = LocalDate.now().plusDays(14);
        int year = futureDate.getYear();
        int month = futureDate.getMonthValue();
        int day = futureDate.getDayOfMonth();

        if (program.GetBook(6).getDueDate().getYear() == year) {
            if (program.GetBook(6).getDueDate().getMonthValue() == month) {
                if (program.GetBook(6).getDueDate().getDayOfMonth() == day) {
                    assertion = true;
                }
            }
        }
        assertTrue(assertion);
    }

    // TODO: RESP-11 Book Holding Display
    // TODO: RESP_11_01 Book Holding System on One Book
    // Display holding books when the book is unavailable
    @Test
    @DisplayName("Book Holding System on One Book")
    void RESP_11_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // Book borrowed by someone else
        program.GetBook(6).setBorrowedId(1);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book onhold successful.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }


    // TODO: RESP-12 Book Holding Validation
    // TODO: RESP_12_01 Book Holding System on Available Book
    // In case the book can be hold
    // Only on a held or borrowed book
    @Test
    @DisplayName("Book Holding System on Available Book")
    void RESP_12_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n12\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The user has 3 borrowed books
        program.GetBook(6).setBorrowedId(0);
        program.GetBook(7).setBorrowedId(0);
        program.GetBook(8).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book onhold successful.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_12_02 Book Holding Invalid Borrowed
    // Also the hold cannot be placed on oneself borrowed book
    @Test
    @DisplayName("Book Holding Invalid Borrowed")
    void RESP_12_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // Book borrowed ourself
        program.GetBook(6).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("You already have this book checked out.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_12_03 Book Holding Invalid Hold
    // No holding onto an already holding book
    @Test
    @DisplayName("Book Holding Invalid Hold")
    void RESP_12_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // Book already held
        program.GetBook(6).setHoldId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("You already have a hold on this book.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_12_04 Book Holding System Invalid if more than one book
    // Make sure to check that the user who hold it is identified correctly, through User Object, not Book itself
    @Test
    @DisplayName("Book Holding System Invalid if more than one book")
    void RESP_12_test_04() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n";
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // Book already held
        program.GetBook(5).setHoldId(0);
        program.GetBook(6).setBorrowedId(1);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("You already have a hold on a book.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-13 Book Holding Process
    // TODO: RESP_13_01 Setting up the hold status
    // So that book is now marked as hold
    @Test
    @DisplayName("Setting up the hold status")
    void RESP_13_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n"; // Access the status of the page with the book holding
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.GetBook(6).setHoldId(1); // Book is held from another

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("On Hold")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-14 Returning Book Borrowed Display
    // TODO: RESP_14_01 In case there are borrowed book
    // Display borrowed books
    @Test
    @DisplayName("In case there are borrowed book")
    void RESP_14_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n2"; // Access the status of the page with the book holding
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The user have already 2 borrowed books.
        program.GetBook(6).setBorrowedId(0);
        program.GetBook(7).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Threads of Infinity") && output.toString().contains("The Last Lighthouse Keeper")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP_14_02 In case there are no borrowed book(s)
    // Display no borrowed books
    @Test
    @DisplayName("In case there are no borrowed book")
    void RESP_14_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n2"; // Access the status of the page with the book holding
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("No borrowed books.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP_14_03 Displaying the Due Date on the Borrowed Books

    // TODO: RESP-15 Book Returning Validation
    // TODO: RESP_15_01 Book Returning Valid
    // In case the book can be returned
    @Test
    @DisplayName("Book Returning Valid")
    void RESP_15_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n2\n7\n"; // Access the status of the page with the book holding
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The user have already 2 borrowed books.
        program.GetBook(6).setBorrowedId(0);
        program.GetBook(7).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book returned.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP_15_02 Book Returning Invalid
    // In case the book cannot be returned
    @Test
    @DisplayName("Book Returning Invalid")
    void RESP_15_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n2\n12"; // Invalid Value for the book
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The user have already 2 borrowed books.
        program.GetBook(6).setBorrowedId(0);
        program.GetBook(7).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Book not returned.")) { // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP_15_03 Book Returning Invalid if number outside range
    // Return to the main menu with appropriate message

    // TODO: RESP-16 Book Returning Process
    // TODO: RESP_16_01 Setting up the returned status
    // So that book is now marked as returned
    @Test
    @DisplayName("Setting up the returned status")
    void RESP_16_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n2\n7\n2\n"; // Invalid Value for the book
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        // The user have already 2 borrowed books.
        program.GetBook(6).setBorrowedId(0);
        program.GetBook(7).setBorrowedId(0);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (program.GetBook(6).getBorrowId() == 999) { // Confirm that the book is not borrowed anymore
            assertion = true;
        }
        assertTrue(assertion);

    }

    // TODO: RESP_16_02 Deal with the onhold status from another user

    // TODO: RESP-17 Book Holding Update
    // TODO: RESP_17_01 Book Must be shown as available
    @Test
    @DisplayName("Book Must be shown as available")
    void RESP_17_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user02\npass02\n2\n7\n1\n"; // Invalid Value for the book
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.GetBook(6).setBorrowedId(1);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Available")) { // Prompt Check
            assertion = true;
        }

        assertTrue(assertion);

    }

    // TODO: RESP_17_02 Book Must not be shown as available
    @Test
    @DisplayName("Book Must not be shown as available")
    void RESP_17_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user02\npass02\n1\n7\n1\n1\n"; // Invalid Value for the book
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("Checked Out")) { // Prompt Check
            assertion = true;
        }

        assertTrue(assertion);
    }

    // TODO: RESP_17_03 Book Holding Update Success on Login
    // Also the book must be shown as available from the user perspective
    /*
    @Test
    @DisplayName("Book Holding Update Success on Login")
    void RESP_17_test_03() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n7\n1\n"; // Checjk
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        program.GetBook(6).setBorrowedId(1);

        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (output.toString().contains("7 | Threads of Infinity | Checked Out")) { // Prompt Check
            assertion = true;
        }

        assertTrue(assertion);
    }
     */

    // TODO: RESP-18 Logout Process
    // TODO: RESP_18_01 Logout Successful
    // So that the user can now log out
    @Test
    @DisplayName("Logout Successful")
    void RESP_18_test_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n3\n"; // Logout
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;
        if (program.GetCurrentUser() == null) { // Prompt Check
            assertion = true;
        }

        assertTrue(assertion);
    }

    // TODO: RESP_18_02 Initiate a new session without errors
    // So that it returns to the login screen
    @Test
    @DisplayName("Initiate a new session without errors")
    void RESP_18_test_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n3\nuser02\npass02"; // Logout
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above



        boolean assertion = false;
        if (program.GetCurrentUser().getUsername().equals("user02")) { // Prompt Check
            assertion = true;
        }

        assertTrue(assertion);
    }
}
