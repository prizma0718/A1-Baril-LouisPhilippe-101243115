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

        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        Main program = new Main();
        program.InitializeLibrary();

        program.Authentication(input, output);

        // The Menu should show at the beginning of the code
        program.Start(input, output); // Start with the Session above

    }

    public class Book{
        String title;
        String author;
        int borrowId;
        LocalDate dueDate;
        ArrayList<String> holdList;

        public String getTitle(){
            return this.title;
        }

        public String getAuthor(){
            return this.author;
        }

        public void setBorrowedId(int userId){
            this.borrowId = userId;
        }

        public int getBorrowId(){
            return this.borrowId;
        }

        public LocalDate getDueDate(){
            return this.dueDate;
        }

        public void setDueDate(LocalDate date){
            this.dueDate = date;
        }

        public String getNextUserHoldList(){
            if(this.holdList.isEmpty()){
                return "";
            }

            return this.holdList.getFirst();
        }

        public void addUserHoldList(String userId){
            this.holdList.add(userId);
        }

        public void removeUserHoldList(){
            if(!this.holdList.isEmpty()){
                this.holdList.removeFirst();
            }
        }


    }

    public class User{
        String username;
        String password;

        public String getUsername(){
            return this.username;
        }

        public String getPassword(){
            return this.password;
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
        this.AddBook("The Great Gatsby", "F. Scott Fitzgerald", false);
        this.AddBook("To Kill a Mockingbird", "Harper Lee", false);
        this.AddBook("1984", "George Orwell", false);
        this.AddBook("Pride and Prejudice", "Jane Austen", false);
        this.AddBook("The Hobbit", "J.R.R. Tolkien", false);
        this.AddBook("Harry Potter", "J.K. Rowling", false);
        this.AddBook("The Catcher in the Rye", "J.D. Salinger", false);
        this.AddBook("Animal Farm", "George Orwell", false);
        this.AddBook("Lord of the Flies", "William Golding", false);
        this.AddBook("Jane Eyre", "Charlotte Bronte", false);
        this.AddBook("Wuthering Heights", "Emily Bronte", false);
        this.AddBook("Moby Dick", "Herman Melville", false);
        this.AddBook("The Odyssey", "Homer", false);
        this.AddBook("Hamlet", "William Shakespeare", false);
        this.AddBook("War and Peace", "Leo Tolstoy", false);
        this.AddBook("The Divine Comedy", "Dante Alighieri", false);
        this.AddBook("Crime and Punishment", "Fyodor Dostoevsky", false);
        this.AddBook("Don Quixote", "Miguel de Cervantes", false);
        this.AddBook("The Iliad", "Homer", false);
        this.AddBook("Ulysses", "James Joyce", false);

        users = new ArrayList<User>();

        this.AddUser("alice", "pass123");
        this.AddUser("bob", "pass456");
        this.AddUser("charlie", "pass789");

    }

    public void AddBook(String title, String author, boolean borrowed){
        Book b = new Book();
        b.title = title;
        b.author = author;
        b.borrowId = 999;
        b.dueDate = LocalDate.now();
        b.holdList = new ArrayList<>();
        catalogue.add(b);
    }

    public Book GetBook(int i){
        return catalogue.get(i);
    }

    public int GetCatalogueSize(){
        return this.catalogue.size();
    }

    public void OverwriteBook(int index, String title, String author){
        Book b = new Book();
        b.title = title;
        b.author = author;
        catalogue.set(index, b);
    }

    public int GetUsersSize(){
        return users.size();
    }

    public User GetUser(int i){
        return users.get(i);
    }

    public void AddUser(String username, String password){
        User u = new User();
        u.username = username;
        u.password = password;
        users.add(u);
    }

    public User GetCurrentUser(){
        return currentUser;
    }

    // Check if the user has a hold on the book
    public boolean GetBookHold(int bookId, String userId){
        for(int i = 0; i < this.GetBook(bookId).holdList.size(); i++){
            if(this.GetBook(bookId).holdList.get(i).equals(userId)){
                return true;
            }
        }
        return false;
    }

    // Check if the user has a hold on a book
    public boolean GetUserHold(String userId){
        for(int i = 0; i < 20; i++){
            if(this.GetBookHold(i, userId)){
                return true;
            }
        }
        return false;
    }

    // Get the Current User ID
    public int getCurrentUserId() {

        if (this.currentUser != null) {
            for (int i = 0; i < this.GetUsersSize(); i++) {
                if (this.GetUser(i).getUsername().equals(currentUser.getUsername())) {
                    return i;
                }
            }
        }

        return 999; // If invalid;
    }

    public void Authentication(Scanner input, PrintWriter output){

        boolean valid = false;
        String username = "";
        String password = "";

        // Main Header
        output.println("Welcome to the Library Management System.");
        output.println("Please log in.");
        output.flush();

        while(!valid){

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

        // Check if Book on hold is available
        // Find the User Book that is currently on hold
        output.flush();
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            //output.println(this.GetBook(i).holdList);
            //output.flush();
            if(this.GetBook(i).getNextUserHoldList().equals(username) && this.GetBook(i).getBorrowId() != getCurrentUserId()){
                output.println("NOTICE: The book " + this.GetBook(i).getTitle() + " is now available.");
                output.flush();
            }
        }

    }



    public void Start(Scanner input, PrintWriter output) {



        // Main Program Loop
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


            switch(option) {
                case "1":
                    borrowProcess(input, output);
                    break;
                case "2":
                    returnProcess(input, output);
                    break;
                case "3":
                    output.println("Logout currently in progress...");
                    currentUser = null;
                    Authentication(input, output);
                    break;
                default:
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
                if(this.GetBook(i).getBorrowId() == userId){
                    String date = this.GetBook(i).getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | *Checked Out, Due: " + date);
                }
                else if(this.GetBook(i).getBorrowId() != 999){
                    String date = this.GetBook(i).getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | Checked Out, Due: " + date);
                }
                else if(this.GetBook(i).getNextUserHoldList().equals(currentUser.username)){
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | *On Hold (Available)");
                }
                else if(this.GetBookHold(i, currentUser.getUsername())){
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | *On Hold");
                }
                else if(this.getCurrentUserId() != 0 && this.GetBookHold(i, this.GetUser(0).getUsername())){
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | On Hold");
                }
                else if(this.getCurrentUserId() != 1 && this.GetBookHold(i, this.GetUser(1).getUsername())){
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | On Hold");
                }
                else if(this.getCurrentUserId() != 2 && this.GetBookHold(i, this.GetUser(2).getUsername())){
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | On Hold");
                }
                else{
                    output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | Available");
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


                    if(this.GetBook(inputValue-1).getBorrowId() != 999){

                        // If we are borrowing or holding this book
                        if(this.GetBook(inputValue-1).getBorrowId() == userId){
                            output.println("Book borrowing unsuccessful. You already have this book checked out.");
                            output.flush();
                            break;
                        }
                        else if(this.GetBookHold(inputValue-1, currentUser.getUsername())){
                            output.println("Book borrowing unsuccessful. You already have a hold on this book.");
                            output.flush();
                            break;
                        }
                        else if(this.GetUserHold(currentUser.getUsername())){
                            output.println("Book borrowing unsuccessful. You already have a hold on another book.");
                            output.flush();
                            break;
                        }


                        // When someone is borrowing this book
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
                            this.GetBook(bookIndex).addUserHoldList(currentUser.getUsername());
                            output.println("Book onhold successful.");
                            output.flush();
                        }

                        else{
                            output.println("Book onhold unsuccessful.");
                            output.flush();
                        }

                        break;
                    }
                    else if(numBorrow >= 3){
                        output.println("Book borrowing unsuccessful. Maximum borrowing limit reached.");
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
                            this.GetBook(bookIndex).addUserHoldList(currentUser.getUsername());
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
                        int confirm = 0;
                        LocalDate expectedDate = LocalDate.now().plusDays(14);
                        String date = expectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        output.println("BOOK INFORMATION:");
                        output.println(inputValue + " | " + this.GetBook(inputValue-1).getTitle() + " | " + this.GetBook(inputValue-1).getAuthor());
                        output.println("Expected Return Date: " + date);
                        output.println("Please confirm the operation.");
                        output.println("1. Yes");
                        output.println("2. No");
                        output.flush();
                        try {
                            confirm = Integer.parseInt(input.nextLine()); // Read input as string and parse
                        } catch (NumberFormatException e) {
                            output.println("Invalid input! Please enter a valid integer.");
                            output.flush();
                        } catch (java.util.NoSuchElementException e) {
                            output.println("Value missing. Exiting safely.");
                            output.flush();
                            break;
                        }

                        if(confirm == 1){
                            this.GetBook(inputValue-1).setBorrowedId(userId);
                            output.println(this.GetBook(inputValue-1).getTitle() + " Book successfully borrowed.");

                            // Set the Due date for 2 weeks later
                            LocalDate futureDate = LocalDate.now().plusDays(14);
                            this.GetBook(inputValue-1).setDueDate(futureDate);

                            // Set the hold list parameters in case the user is currently holding that book
                            if(this.GetBook(inputValue-1).getNextUserHoldList().equals(currentUser.getUsername())){
                                this.GetBook(inputValue-1).removeUserHoldList();
                            }

                            // Set that book in the holdlist
                            this.GetBook(inputValue-1).addUserHoldList(currentUser.getUsername());

                            output.println("Book borrowing successful.");

                            output.flush();
                            break;
                        }
                        else{
                            output.println("Returning to book listing.");
                            output.flush();
                        }

                    }
                }
                else if(inputValue == 0){
                    output.println("Returning to main menu.");
                    output.flush();
                    break;
                }
                else{
                    output.println("Invalid Choice. Please enter a number in the range of 0-20.");
                    output.flush();
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
                output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | Checked Out, Due: " + this.GetBook(i).getDueDate());
            }
        }

        if(numBorrow == 0){
            output.println("No borrowed books.");
        }
        else {
            while (true) {
                int inputValue = 0;
                output.println("Enter the # of the Book you want to return.");
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
                    if(this.GetBook(inputValue-1).getBorrowId() == userId){

                        int confirm = 0;
                        output.println("BOOK INFORMATION:");
                        output.println(inputValue + " | " + this.GetBook(inputValue-1).getTitle() + " | " + this.GetBook(inputValue-1).getAuthor());
                        output.println("Please confirm the operation.");
                        output.println("1. Yes");
                        output.println("2. No");
                        output.flush();
                        try {
                            confirm = Integer.parseInt(input.nextLine()); // Read input as string and parse
                        } catch (NumberFormatException e) {
                            output.println("Invalid input! Please enter a valid integer.");
                            output.flush();
                        } catch (java.util.NoSuchElementException e) {
                            output.println("Value missing. Exiting safely.");
                            output.flush();
                            break;
                        }

                        if(confirm == 1){
                            this.GetBook(inputValue-1).setBorrowedId(999);
                            this.GetBook(inputValue-1).removeUserHoldList();
                            output.println("Book returned.");
                            output.flush();
                            break;
                        }
                        else{
                            output.println("Returning to book listing.");
                            output.flush();
                        }
                    }
                    else{
                        output.println("Book number invalid. Book not returned.");
                        output.flush();
                    }
                }
                else if (inputValue == 0){
                    output.println("Returning to main menu.");
                    output.flush();
                    break;
                }
            }
        }
    }
}
