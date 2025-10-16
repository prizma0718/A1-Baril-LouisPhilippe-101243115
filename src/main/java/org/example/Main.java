package org.example;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Library Function
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("COMP 4004 - Library Management System");

        //String input = "user01\npass02\n";

        Scanner input = new Scanner(System.in);
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
    public void Start(Scanner input, PrintWriter output) {
        output.println("Logged in as " + currentUser.getUsername() + ".");
    }
}
