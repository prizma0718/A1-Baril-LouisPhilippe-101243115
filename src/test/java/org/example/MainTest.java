package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // TODO: RESP-01 System Book Collection Initialization
    // TODO: RESP_01_01 Book Collection Count
    // Check if the collection contains 20 books
    @Test
    @DisplayName("Book Collection Count Check")
    void RESP_01_test_01(){
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
    void RESP_01_test_02(){
        Main program = new Main();
        program.InitializeLibrary();

        int catalogueSize = program.GetCatalogueSize(); // Get the Value

        program.OverwriteBook(0, "Whispers in the Fog","Eleanor Vance", false);
        program.OverwriteBook(9, "When Stars Align", "Nathaniel Grey", false);
        program.OverwriteBook(19, "Letters from the Void", "Samuel Quill", false);

        // Verify the Entry
        boolean noMismatch = false;

        if (program.GetBook(0).getTitle().equals("Whispers in the Fog") && program.GetBook(0).getAuthor().equals("Eleanor Vance")){
            if (program.GetBook(9).getTitle().equals("When Stars Align") && program.GetBook(9).getAuthor().equals("Nathaniel Grey")){
                if (program.GetBook(19).getTitle().equals("Letters from the Void") && program.GetBook(19).getAuthor().equals("Samuel Quill")){
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
    void RESP_01_test_03(){
        Main program = new Main();
        program.InitializeLibrary();

        int catalogueSize = program.GetCatalogueSize();

        // test 2 - should be no duplicate values in deck, Computing
        boolean noMismatch = true;
        for (int i = 0; i < catalogueSize; i++){
            for (int j = 0; j < program.GetCatalogueSize(); j++){
                if (program.GetBook(i).getBorrowed()){
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
    void RESP_02_test_01(){
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
    void RESP_02_test_02(){
        Main program = new Main();
        program.InitializeLibrary();

        // Verify the Entry
        boolean noMismatch = false;

        if (program.GetUser(0).getUsername().equals("user01") && program.GetUser(0).getPassword().equals("pass01")){
            if (program.GetUser(1).getUsername().equals("user02") && program.GetUser(1).getPassword().equals("pass02")){
                if (program.GetUser(2).getUsername().equals("user03") && program.GetUser(2).getPassword().equals("pass03")){
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
    void RESP_02_test_03(){
        Main program = new Main();
        program.InitializeLibrary();

        int usersSize = program.GetUsersSize();

        // test 2 - should be no duplicate values in deck, Computing
        boolean noMismatch = true;
        for (int i = 0; i < usersSize; i++){
            if (program.GetUser(i).getBorrowing()){
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
    void RESP_03_test_01(){
        Main program = new Main();
        program.InitializeLibrary();

        String input = "\n"; // No input Yet
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Username:")){ // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_03_02 Password Authentication Prompt
    // Check if password authentication prompt is shown
    @Test
    @DisplayName("Password Authentication Prompt")
    void RESP_03_test_02(){
        Main program = new Main();
        program.InitializeLibrary();

        String input = "\n"; // No input Yet
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Password:")){ // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_03_03 User Authentication Success
    // Check if user authentication succeeds
    @Test
    @DisplayName("User Authentication Success")
    void RESP_03_test_03(){
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01"; // No input Yet
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Welcome, user01!")){ // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP_03_04 User Authentication Failure
    // Check if user authentication fails and retry
    @Test
    @DisplayName("User Authentication Failure")
    void RESP_03_test_04(){
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user99\npass99"; // Invalid credentials
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));

        boolean assertion = false;
        if (output.toString().contains("Login invalid, please retry.")){ // Prompt Check
            assertion = true;
        }
        assertTrue(assertion);
    }

    // TODO: RESP-04 Session Establishment
    // TODO: RESP_04_01 User Session Establishment
    // Check if user session is set up after authentication

    // TODO: RESP-05 Book Availability Real Time Tracking
    // TODO: RESP_05_01 Registered Book Available
    // Check if the registered book is now available at beginning

    // TODO: RESP_05_02 Registered Book Not Available
    // Check if the registered book is not available yet

    // TODO: RESP-06 Main Menu Displaying
    // TODO: RESP_06_01 Main Menu Prompt
    // Check if main menu displays correctly

    // TODO: RESP-07 Main Menu Navigation
    // Main menu Options Navigation Process

    // TODO: RESP-08 Book Borrowing Initial Display
    // TODO: RESP_08_01 Current Book Count Prompt
    // Check if borrowing books display properly

    // TODO: RESP_08_02 Collection Prompt
    // Check if borrowing collection display properly

    // TODO: RESP_08_03 Books Borrowed Prompt
    // Check if borrowed book information display properly

    // TODO: RESP-09 Book Borrowing Validation
    // TODO: RESP_09_01 Book Borrowing Valid
    // In case the book can be borrowed

    // TODO: RESP_09_02 Book Borrowing Invalid
    // In case the book cannot be borrowed

    // TODO: RESP_09_03 Book Borrowing Maximum
    // In case more than 3 books are borrowed

    // TODO: RESP-10 Book Borrowing Process
    // TODO: RESP_10_01 Setting up the 14-day period
    // So that book is borrowed for next 14 days

    // TODO: RESP_10_02 Setting up the borrowed status
    // So that book is now marked as borrowed

    // TODO: RESP-11 Book Holding Display
    // TODO: RESP_11_01 In case there are holding book(s)
    // Display holding books

    // TODO: RESP_11_02 In case there are no borrowed book(s)
    // Display no holding books

    // TODO: RESP-12 Book Holding Validation
    // TODO: RESP_12_01 Book Holding Valid
    // In case the book can be hold

    // TODO: RESP_12_02 Book Holding Invalid
    // In case the book cannot be hold

    // TODO: RESP-13 Book Holding Process
    // TODO: RESP_13_01 Setting up the holding period
    // So that book show specific details

    // TODO: RESP_13_02 Setting up the hold status
    // So that book is now marked as hold

    // TODO: RESP-14 Book Holding Update
    // TODO: RESP_14_01 Book Holding Updated after return

    // TODO: RESP_14_02 Book Must be shown as available

    // TODO: RESP-15 Book Borrowed Display
    // TODO: RESP_15_01 In case there are borrowed book(s)
    // Display borrowed books

    // TODO: RESP_15_02 In case there are no borrowed book(s)
    // Display no borrowed books

    // TODO: RESP-16 Book Returning Validation
    // TODO: RESP_16_01 Book Returning Valid
    // In case the book can be returned

    // TODO: RESP_16_02 Book Returning Invalid
    // In case the book cannot be returned

    // TODO: RESP-17 Book Returning Process
    // TODO: RESP_17_01 Setting up the return period
    // So that book show specific details

    // TODO: RESP_17_02 Setting up the returned status
    // So that book is now marked as returned

    // TODO: RESP-18 Logout Process
    // TODO: RESP_18_01 Logout Successful
    // So that the user can now log out

    // TODO: RESP_18_02 Return to Authentication
    // So that it returns to the login screen

    // TODO: REST_18_03 Initiate a new session without errors
}
