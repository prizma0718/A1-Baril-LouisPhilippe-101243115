package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Library Function
public class Main {
    public static void main(String[] args) {
        
        System.out.println("COMP 4004 - Library Management System");
        
        // Input and Output Initialization
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);
        
        // Program Library Initialization
        Main program = new Main();
        program.InitializeLibrary();
        program.InitializeUsers();

        // Program Authentication Process
        program.Authentication(input, output);

        // Start with the Session above
        program.Start(input, output); 

    }

    public static class Book{
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

        public int getBorrowId(){
            return this.borrowId;
        }

        public void setBorrowedId(int userId){
            this.borrowId = userId;
        }

        public String getDueDate(){
            return this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        public void setDueDate(LocalDate date){
            this.dueDate = date;
        }
        
        // Get the Next User who is borrowing/holding the book
        public String getNextUserHoldList(){
            if(this.holdList.isEmpty()){
                return "";
            }
            
            return this.holdList.getFirst();
        }

        // Add the User from the Hold List
        public void addUserHoldList(String userId){
            this.holdList.add(userId);
        }

        // Remove the User from the Hold List
        public void removeUserHoldList(){
            if(!this.holdList.isEmpty()){
                this.holdList.removeFirst();
            }
        }

        public boolean isCheckedOut(){
            return this.getBorrowId() != 999;
        }
    }

    public static class User{
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

    public void InitializeLibrary(){
        catalogue = new ArrayList<Book>();

        // Add the 20 Books into the Catalogue
        this.AddBook("The Great Gatsby", "F. Scott Fitzgerald");
        this.AddBook("To Kill a Mockingbird", "Harper Lee");
        this.AddBook("1984", "George Orwell");
        this.AddBook("Pride and Prejudice", "Jane Austen");
        this.AddBook("The Hobbit", "J.R.R. Tolkien");
        this.AddBook("Harry Potter", "J.K. Rowling");
        this.AddBook("The Catcher in the Rye", "J.D. Salinger");
        this.AddBook("Animal Farm", "George Orwell");
        this.AddBook("Lord of the Flies", "William Golding");
        this.AddBook("Jane Eyre", "Charlotte Bronte");
        this.AddBook("Wuthering Heights", "Emily Bronte");
        this.AddBook("Moby Dick", "Herman Melville");
        this.AddBook("The Odyssey", "Homer");
        this.AddBook("Hamlet", "William Shakespeare");
        this.AddBook("War and Peace", "Leo Tolstoy");
        this.AddBook("The Divine Comedy", "Dante Alighieri");
        this.AddBook("Crime and Punishment", "Fyodor Dostoevsky");
        this.AddBook("Don Quixote", "Miguel de Cervantes");
        this.AddBook("The Iliad", "Homer");
        this.AddBook("Ulysses", "James Joyce");

        users = new ArrayList<User>();



    }

    public void InitializeUsers(){
        this.AddUser("alice", "pass123");
        this.AddUser("bob", "pass456");
        this.AddUser("charlie", "pass789");
    }

    public void AddBook(String title, String author){
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

    public User GetUserById(int i){
        return users.get(i);
    }

    public User GetUserByUsername(String username){
        for (int i = 0; i < this.GetUsersSize(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }

        return null;
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

    // Get the Current User ID
    public int GetUserId(String username) {

        for (int i = 0; i < this.GetUsersSize(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return i;
            }
        }

        return 999; // If invalid;
    }

    public void SetCurrentUser(String username){
        for(int i = 0; i < this.GetUsersSize(); i++){
            if(this.users.get(i).getUsername().equals(username)){
                this.currentUser = this.users.get(i);
            }
        }
    }

    // Check if the user has a hold on the book
    // DO NOT CHECK THE First Value, since it is the borrower
    public boolean GetBookHold(int bookId, String userId){
        for(int i = 1; i < this.GetBook(bookId).holdList.size(); i++){
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

    public User getCurrentBookBorrower(Book book){

        if(book.getBorrowId() == 999){
            return null;
        }

        return this.GetUserById(book.borrowId);
    }

    public String noAvailableBooksNotification(){
        if(this.getBorrowedBooksCount() == 0){
            return "no books currently borrowed.";
        }

        return "";
    }

    public int getBorrowedBooksCount(){
        int numBorrowed = 0;
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            if(this.GetBook(i).getBorrowId() == GetUserId(currentUser.getUsername())){
                numBorrowed += 1;
            }
        }

        return numBorrowed;


    }

    public int getAvailableBooksCount(){
        int numAvailable = 0;
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            if(this.GetBook(i).getBorrowId() == 999){
                numAvailable += 1;
            }
        }

        return numAvailable;

    }


    public void displayBorrowedBooks(int userId, PrintWriter output){
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            if(this.GetBook(i).getBorrowId() == userId){
                output.println(i+1 + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | Checked Out, Due: " + this.GetBook(i).getDueDate());
            }
        }
    }

    public boolean isAtBorrowingLimit(){
        return getBorrowedBooksCount() == 3;
    }

    public ArrayList<String> getHoldQueue(int bookId){
        return GetBook(bookId).holdList;
    }

    public String hasNotification(String username){
        StringBuilder notify = new StringBuilder();
        for(int i = 0; i < this.GetCatalogueSize(); i++){

            if(this.GetBook(i).getNextUserHoldList().equals(username) && this.GetBook(i).getBorrowId() != GetUserId(currentUser.getUsername())){
                notify.append("NOTICE: The book ").append(this.GetBook(i).getTitle()).append(" is now available.\n");
            }
        }

        return notify.toString();
    }

    public boolean bookBorrow(int bookId, String username){

        if(this.GetBook(bookId).getBorrowId() == 999 && getBorrowedBooksCount() < 3) {

            if(this.getHoldQueue(bookId).isEmpty() || this.getHoldQueue(bookId).getFirst().equals(username)){
                this.GetBook(bookId).setBorrowedId(this.GetUserId(username));

                // Set the Due date for 2 weeks later
                LocalDate futureDate = LocalDate.now().plusDays(14);
                this.GetBook(bookId).setDueDate(futureDate);


                // Set the hold list parameters in case the user is currently holding that book
                /*
                if (this.GetBook(bookId).getNextUserHoldList().equals(username)) {
                    this.GetBook(bookId).removeUserHoldList();
                }
                */

                // Set the user in the holdlist
                this.GetBook(bookId).addUserHoldList(username);

                return true;
            }
        }

        return false;
    }

    public boolean bookReturn(int bookId, String username){

        if(this.GetBook(bookId).getBorrowId() == GetUserId(username)){
            this.GetBook(bookId).setBorrowedId(999);
            this.GetBook(bookId).removeUserHoldList();
            return true;
        }

        return false;

    }

    public boolean bookHold(int bookId, String username){

        if(!GetUserHold(username)){
            this.GetBook(bookId).addUserHoldList(username);
            return true;
        }

        return false;
    }

    public void displayCollection(int userId, PrintWriter output){
        for(int i = 0; i < this.GetCatalogueSize(); i++){
            int entry = i+1;
            String date = this.GetBook(i).getDueDate();
            if(this.GetBook(i).getBorrowId() == userId){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | *Checked Out, Due: " + date);
            }
            else if(this.GetBook(i).getBorrowId() != 999){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | Checked Out, Due: " + date);
            }
            else if(this.GetBook(i).getNextUserHoldList().equals(currentUser.username)){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | *On Hold (Available)");
            }
            else if(this.GetBookHold(i, currentUser.getUsername())){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | *On Hold");
            }
            else if(this.GetUserId(currentUser.getUsername()) != 0 && this.GetBookHold(i, this.GetUserById(0).getUsername())){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | On Hold");
            }
            else if(this.GetUserId(currentUser.getUsername()) != 1 && this.GetBookHold(i, this.GetUserById(1).getUsername())){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | On Hold");
            }
            else if(this.GetUserId(currentUser.getUsername()) != 2 && this.GetBookHold(i, this.GetUserById(2).getUsername())){
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | On Hold");
            }
            else{
                output.println(entry + " | " + this.GetBook(i).getTitle() + " | " + this.GetBook(i).getAuthor() + " | Available");
            }
        }
        output.flush();
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

            // Reset the Output
            output.flush();
        }

        // Set the CurrentUser
        for(int i = 0; i < this.GetUsersSize(); i++){
            if(this.users.get(i).getUsername().equals(username) && this.users.get(i).getPassword().equals(password)){
                this.SetCurrentUser(username);
            }
        }

        // Check if Book on hold is available
        // Find the User Book that is currently on hold
        String notify = hasNotification(currentUser.getUsername());
        output.println(notify);
        output.flush();
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

            // Option Selection
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
        int userId = GetUserId(currentUser.getUsername());

        // Find how many books the user is borrowing
        int numBorrow = getBorrowedBooksCount();

        // Display the Number of the Borrowed Books
        if(numBorrow <= 3 && numBorrow >= 0){
            output.println("Number of borrowed books: " + numBorrow);
            output.flush();

            output.println("Display of the collection:");
            output.flush();
            displayCollection(userId, output);

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
                    
                    int bookIndex = inputValue-1;

                    if(this.GetBook(bookIndex).getBorrowId() != 999){

                        // If we are borrowing or holding this book
                        if(this.GetBook(bookIndex).getBorrowId() == userId){
                            output.println("Book borrowing unsuccessful. You already have this book checked out.");
                            output.flush();
                            break;
                        }
                        else if(this.GetBookHold(bookIndex, currentUser.getUsername())){
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
                            this.bookHold(bookIndex, this.currentUser.getUsername());
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
                            this.bookHold(bookIndex, this.currentUser.getUsername());
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
                        if(this.getHoldQueue(bookIndex).isEmpty() || this.getHoldQueue(bookIndex).getFirst().equals(currentUser.getUsername())){
                            int confirm = 0;
                            LocalDate expectedDate = LocalDate.now().plusDays(14);
                            String date = expectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            output.println("BOOK INFORMATION:");
                            output.println(inputValue + " | " + this.GetBook(bookIndex).getTitle() + " | " + this.GetBook(bookIndex).getAuthor());
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
                                if(this.bookBorrow(bookIndex, this.currentUser.getUsername())){
                                    output.println(this.GetBook(bookIndex).getTitle() + " Book successfully borrowed.");
                                    output.println("Book borrowing successful.");
                                    output.flush();
                                    break;
                                }
                                else{
                                    output.println("Book borrowing function unsuccessful.");
                                    output.flush();
                                }

                            }
                            else{
                                output.println("Returning to book listing.");
                                output.flush();
                            }
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
        int userId = GetUserId(currentUser.getUsername());

        output.println("Currently borrowed books:");
        output.flush();

        // Display borrowed books
        displayBorrowedBooks(userId, output);

        // Find how many books the user is borrowing
        int numBorrow = getBorrowedBooksCount();


        if(numBorrow == 0){
            noAvailableBooksNotification();
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

                int bookIndex = inputValue-1;

                if(inputValue >= 1 && inputValue <= 20){
                    if(this.GetBook(bookIndex).getBorrowId() == userId){

                        int confirm = 0;
                        output.println("BOOK INFORMATION:");
                        output.println(inputValue + " | " + this.GetBook(bookIndex).getTitle() + " | " + this.GetBook(bookIndex).getAuthor());
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
                            if(this.bookReturn(bookIndex, this.currentUser.getUsername())){
                                output.println("Book returned.");
                                output.flush();
                                break;
                            }
                            else{
                                output.println("Book return function unsuccessful.");
                                output.flush();
                            }

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
