package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainATest {

    // TODO: ATEST-01 Multi-User Borrow and Return with Availability Validated
    @Test
    @DisplayName("A-TEST-01")
    void A_TEST_01() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user01\npass01\n1\n18\n3\nuser02\npass02\n1\n0\n3\nuser01\npass01\n2\n18\n3\nuser02\npass02\n1\n"; // Logout
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = true;

        // All books must be available as expected
        for(int i = 0; i < program.GetCatalogueSize(); i++){
            if(program.GetBook(i).getBorrowId() != 999){
                assertion = false;
            }
        }

        assertTrue(assertion);
    }

    // UC-01 Complete Flow
    /*
    // TODO: ATEST-02 Initialization and Authentication with Error Handling
    @Test
    @DisplayName("A-TEST-02")
    void A_TEST_02() {
        Main program = new Main();
        program.InitializeLibrary();

        String input = "user03\npass03\n3\nuser99\npass99\n"; // Logout
        StringWriter output = new StringWriter();
        program.Authentication(new Scanner(input), new PrintWriter(output));


        // The Menu should show at the beginning of the code
        program.Start(new Scanner(input), new PrintWriter(output)); // Start with the Session above

        boolean assertion = false;

        // Check of conditions
        if(program.GetCatalogueSize() == 20 && program.GetUsersSize() == 3 && output.toString().contains("Welcome, user03!") && output.toString().contains("Login invalid, please retry.") && program.getCurrentUserId() == 999){
            assertion = true;
        }

        assertTrue(assertion);
    }
     */
}