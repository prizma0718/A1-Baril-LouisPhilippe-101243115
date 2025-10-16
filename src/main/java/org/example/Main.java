package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Library Function
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("COMP 4004 - Library Management System");

        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        Main program = new Main();
    }

    class Book{
        String title;
        String author;
        boolean borrowed;

        public String getTitle(){
            return "";
        }

        public String getAuthor(){
            return "";
        }

        public Boolean getBorrowed(){
            return true;
        }


    }

    ArrayList<Book> catalogue = new ArrayList<Book>();

    // Catalogue Class Creation
    // InitializeLibrary Class Creation

    public void InitializeLibrary(){

    }

    public Book GetBook(int i){
        Book b = new Book();
        b.title = "Title";
        b.author = "Author";
        b.borrowed = true;
        return b;
    }



    public int GetCatalogueSize(){
        return 0;
    }

    public void OverwriteBook(int index, String title, String author, Boolean borrowed){

    }

}
