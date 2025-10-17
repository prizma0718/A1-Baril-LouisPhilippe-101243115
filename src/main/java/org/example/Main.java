package org.example;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Library Function
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("COMP 4004 - Library Management System");

        String input = "user01\npass01\n";

        //Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        Main program = new Main();
        program.InitializeLibrary();

        //program.Authentication(new Scanner(input), output);
        //program.Authentication(input, output);

    }

    public class Book{
        String title;
        String author;
        boolean borrowed;
        int borrowId;
        boolean hold;
        int holdId;
        LocalDate dueDate;

        public String getTitle(){
            return this.title;
        }

        public String getAuthor(){
            return this.author;
        }

        public Boolean getBorrowed(){
            return this.borrowed;
        }

        public void setBorrowed(boolean value){
            this.borrowed = value;
        }

        public void setBorrowedId(int userId){
            this.borrowId = userId;
        }

        public int getBorrowId(){
            return this.borrowId;
        }

        public int getHoldId(){
            return this.holdId;
        }

        public void setHoldId(int userId){
            this.holdId = userId;
        }

        public LocalDate getDueDate(){
            return this.dueDate;
        }

        public void setDueDate(LocalDate date){
            this.dueDate = date;
        }

    }

    public class User{
        String username;
        String password;
        boolean borrowing;

        public String getUsername(){
            return this.username;
        }

        public String getPassword(){
            return this.password;
        }

        public boolean getBorrowing(){
            return this.borrowing;
        }


    }

    ArrayList<Book> catalogue = new ArrayList<Book>();
    ArrayList<User> users = new ArrayList<User>();

    User currentUser;

    // Catalogue Class Creation
    // InitializeLibrary Class Creation

    public void InitializeLibrary(){
        catalogue = new ArrayList<Book>();

        // Add the 20 Books into the Catalogue
        this.AddBook("Whispers in the Fog", "Eleanor Vance", false);
        this.AddBook("The Clockwork Garden", "Marcus Halloway", false);
        this.AddBook("Shadows of the Forgotten", "Lila Brenner", false);
        this.AddBook("A Lantern for Tomorrow", "Thomas Evers", false);
        this.AddBook("The Silent Symphony", "Clara Whitmore", false);
        this.AddBook("Beneath the Crimson Sky", "Julian Rook", false);
        this.AddBook("Threads of Infinity", "Isabelle Marlowe", false);
        this.AddBook("The Last Lighthouse Keeper", "Adrian Kells", false);
        this.AddBook("Echoes of Amber", "Sophie Delacroix", false);
        this.AddBook("When Stars Align", "Nathaniel Grey", false);
        this.AddBook("The Paper Kingdom", "Victoria Ames", false);
        this.AddBook("A Door in the Mountains", "Daniel Forsyth", false);
        this.AddBook("The Forgotten Map", "Helena Carrick", false);
        this.AddBook("Tides of Glass", "Oliver Bain", false);
        this.AddBook("Voices of the Deep", "Madeline Frost", false);
        this.AddBook("The Painter’s Secret", "Gabriel Thorne", false);
        this.AddBook("Winds of Yesterday", "Emilia Hart", false);
        this.AddBook("Beneath Neon Skies", " Jasper Linwood", false);
        this.AddBook("The Alchemist’s Shadow", "Fiona Delaney", false);
        this.AddBook("Letters from the Void", "Samuel Quill", false);

        users = new ArrayList<User>();

        this.AddUser("user01", "pass01", false);
        this.AddUser("user02", "pass02", false);
        this.AddUser("user03", "pass03", false);

    }

    public void AddBook(String title, String author, boolean borrowed){
        Book b = new Book();
        b.title = title;
        b.author = author;
        b.borrowed = borrowed;
        b.borrowId = 999;
        b.holdId = 999;
        b.hold = false;
        b.dueDate = LocalDate.now();
        catalogue.add(b);
    }

    public Book GetBook(int i){
        return catalogue.get(i);
    }

    public int GetCatalogueSize(){
        return this.catalogue.size();
    }

    public void OverwriteBook(int index, String title, String author, boolean borrowed){
        Book b = new Book();
        b.title = title;
        b.author = author;
        b.borrowed = borrowed;
        catalogue.set(index, b);
    }

    public int GetUsersSize(){
        return users.size();
    }

    public User GetUser(int i){
        return users.get(i);
    }

    public void AddUser(String username, String password, boolean borrowing){
        User u = new User();
        u.username = username;
        u.password = password;
        u.borrowing = borrowing;
        users.add(u);
    }

    public User GetCurrentUser(){
        return currentUser;
    }

    public void setBookHold(int bookId, int userId){
        this.GetBook(bookId).setHoldId(userId);
    }

    public void Authentication(Scanner input, PrintWriter output){

        boolean valid = false;
        String username = "";
        String password = "";

        // Main Header
        output.println("Welcome to the Library Management System.");
        output.println("Please log in.");
        output.flush();

        while(valid == false){

            // Username Prompt
            output.println("Username:");
            output.flush();

            try {
                username = input.nextLine();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Username missing. Exiting safely.");
                break;
            }

            // Password Prompt
            output.println("Password:");
            output.flush();
            try {
                password = input.nextLine();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Password missing. Exiting safely.");
                break;
            }

            // Validation Process
            for(int i = 0; i < this.GetUsersSize(); i++){
                if(this.users.get(i).getUsername().equals(username) && this.users.get(i).getPassword().equals(password)){
                    valid = true;
                }
            }

            if(!valid){
                output.println("Login invalid, please retry.");

            }
            else{
                output.println("Welcome, " + username + "!");
            }
            output.flush();
        }

        // Set the User to the Appropriate One
        for(int i = 0; i < this.GetUsersSize(); i++){
            if(this.users.get(i).getUsername().equals(username) && this.users.get(i).getPassword().equals(password)){
                this.currentUser = this.users.get(i);
            }
        }

    }


    // Get the Current User ID
    public int getCurrentUserId(){
        for(int i = 0; i < this.GetUsersSize(); i++){
            if(this.GetUser(i).getUsername().equals(currentUser.getUsername())){
                return i;
            }
        }

        return 999; // If invalid
    }

    public void Start(Scanner input, PrintWriter output) {
        output.println("Logged in as " + currentUser.getUsername() + ".");
        output.flush();

        // Get the UserID of the Current User
        int userId = getCurrentUserId();

        // Check if Book on hold is available
        // Find the User Book that is currently on hold
        output.flush();
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            if(this.GetBook(i).getHoldId() == userId && !this.GetBook(i).getBorrowed()){
                output.println("The book " + this.GetBook(i).getTitle() + " is now available.");
                output.flush();
                //break;
            }
        }

        while(true){


            String option = "";

            // Menu Display
            output.println("----- MAIN MENU -----");
            output.println("1. Borrow a book");
            output.println("2. Return a book");
            output.println("3. Logout");
            output.println("Please enter your selection:");
            output.flush();

            try {
                option = input.nextLine();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Value missing. Exiting safely.");
                break;
            }


            if(option.equals("1")){

                borrowProcess(input, output);
            }
            else if(option.equals("2")){
                returnProcess(input, output);
            }
            else if(option.equals("3")){
                output.println("Logout currently in progress...");
            }
            else{
                output.println("Invalid Choice. Please Retry.");
            }

            output.flush();
        }

    }

    public void borrowProcess(Scanner input, PrintWriter output){

        output.println("----- BORROWING -----"); // Display Prompt

        // Get Current User ID
        int userId = getCurrentUserId();

        // Find how many books the user is borrowing
        int numBorrow = 0;
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            if(this.GetBook(i).getBorrowId() == userId){
                numBorrow += 1;
            }
        }

        // Display the Number of the Borrowed Books
        if(numBorrow <= 3 && numBorrow >= 0){
            output.println("Number of borrowed books: " + numBorrow);
            output.flush();

            output.println("Display of the collection:");
            output.flush();
            for(int i = 0; i < this.GetCatalogueSize(); i++){
                if(this.GetBook(i).getBorrowId() != 999){
                    String date = this.GetBook(i).getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | Checked Out, Due: " + date);
                }
                else if(this.GetBook(i).getHoldId() != 999){
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | On Hold");
                }
                else{
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | Available");
                }
            }
            output.flush();


            while (true) {
                int inputValue = 0;
                output.println("Enter the # of the Book you want to borrow.");
                output.flush();
                try {
                    inputValue = Integer.parseInt(input.nextLine()); // Read input as string and parse
                } catch (NumberFormatException e) {
                    output.println("Invalid input! Please enter a valid integer.");
                    output.flush();
                } catch (java.util.NoSuchElementException e) {
                    output.println("Value missing. Exiting safely.");
                    output.flush();
                    break;
                }


                if(inputValue >= 1 && inputValue <= 20){
                    if(this.GetBook(inputValue-1).getBorrowId() != 999 || this.GetBook(inputValue-1).getHoldId() != 999){

                        // If we are borrowing or holding this book
                        if(this.GetBook(inputValue-1).getBorrowId() == userId){
                            output.println("Book borrowing unsuccessful. Book already borrowed.");
                            output.flush();
                            break;
                        }
                        else if(this.GetBook(inputValue-1).getHoldId() == userId){
                            output.println("Book borrowing unsuccessful. Book already held.");
                            output.flush();
                            break;
                        }

                        output.println("Book borrowing unsuccessful. Someone is borrowing this book.");
                        output.flush();

                        int bookIndex = inputValue - 1;

                        output.println("Do you want to hold this book for the future?");
                        output.println("1. Yes");
                        output.println("2. No");
                        output.flush();
                        try {
                            inputValue = Integer.parseInt(input.nextLine()); // Read input as string and parse
                        } catch (NumberFormatException e) {
                            output.println("Invalid input! Please enter a valid integer.");
                            output.flush();
                        } catch (java.util.NoSuchElementException e) {
                            output.println("Value missing. Exiting safely.");
                            output.flush();
                            break;
                        }

                        if(inputValue == 1){
                            this.GetBook(bookIndex).setHoldId(userId);
                            output.println("Book onhold successful.");
                            output.flush();
                        }
                        else{
                            output.println("Book onhold unsuccessful.");
                            output.flush();
                        }

                        break;
                    }
                    else if(numBorrow == 3){
                        output.println("Book borrowing unsuccessful. Maximum number of borrowing books.");
                        output.flush();

                        int bookIndex = inputValue - 1;

                        output.println("Do you want to hold this book for the future?");
                        output.println("1. Yes");
                        output.println("2. No");
                        output.flush();
                        try {
                            inputValue = Integer.parseInt(input.nextLine()); // Read input as string and parse
                        } catch (NumberFormatException e) {
                            output.println("Invalid input! Please enter a valid integer.");
                            output.flush();
                        } catch (java.util.NoSuchElementException e) {
                            output.println("Value missing. Exiting safely.");
                            output.flush();
                            break;
                        }

                        if(inputValue == 1){
                            this.GetBook(bookIndex).setHoldId(userId);
                            output.println("Book onhold successful.");
                            output.flush();
                        }
                        else{
                            output.println("Book onhold unsuccessful.");
                            output.flush();
                        }

                        break;
                    }
                    else{
                        this.GetBook(inputValue-1).setBorrowedId(userId);
                        output.println(this.GetBook(inputValue-1).getTitle() + " Book successfully borrowed.");

                        // Set the Due date for 2 weeks later
                        LocalDate futureDate = LocalDate.now().plusDays(14);
                        this.GetBook(inputValue-1).setDueDate(futureDate);

                        output.flush();
                        break;
                    }
                }
            }

        }
        else{
            output.println("Too many borrowing books. Please return books.");
            output.flush();
        }




    }

    public void returnProcess(Scanner input, PrintWriter output){
        output.println("----- RETURNING -----");

        // Get Current User ID
        int userId = getCurrentUserId();

        output.println("Currently borrowed books:");
        output.flush();

        // Find how many books the user is borrowing
        int numBorrow = 0;
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            if(this.GetBook(i).getBorrowId() == userId){
                numBorrow += 1;
                output.println(this.GetBook(i).getTitle());
            }
        }

        if(numBorrow == 0){
            output.println("No borrowed books.");
        }

    }
}
