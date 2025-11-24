import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void borrow() { isAvailable = false; }
    public void returned() { isAvailable = true; }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Status: " + (isAvailable ? "Available" : "Borrowed");
    }
}

public class LibraryApp {
    private static Book[] books = new Book[100];
    private static int count = 0;
    private static Scanner sc = new Scanner(System.in);

    private static void addBook() {
        System.out.print("Enter book title: ");
        sc.nextLine();
        String title = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();
        books[count++] = new Book(title, author);
        System.out.println("Book added.");
    }

    // Overloaded search by title
    private static Book searchBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) return books[i];
        }
        return null;
    }

    // Overloaded search by author (returns first match)
    private static Book[] searchBookByAuthor(String author) {
        Book[] result = new Book[100];
        int rc = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) result[rc++] = books[i];
        }
        Book[] trimmed = new Book[rc];
        System.arraycopy(result, 0, trimmed, 0, rc);
        return trimmed;
    }

    private static void borrowBook() {
        System.out.print("Enter title to borrow: ");
        sc.nextLine();
        String title = sc.nextLine();
        Book b = searchBook(title);
        if (b == null) System.out.println("Book not found.");
        else if (!b.isAvailable()) System.out.println("Book already borrowed.");
        else {
            b.borrow();
            System.out.println("Book borrowed.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter title to return: ");
        sc.nextLine();
        String title = sc.nextLine();
        Book b = searchBook(title);
        if (b == null) System.out.println("Book not found.");
        else {
            b.returned();
            System.out.println("Book returned.");
        }
    }

    private static void displayAllBooks() {
        if (count == 0) {
            System.out.println("No books in library.");
            return;
        }
        for (int i = 0; i < count; i++) System.out.println(books[i]);
    }

    private static void searchByTitle() {
        System.out.print("Enter title: ");
        sc.nextLine();
        String title = sc.nextLine();
        Book b = searchBook(title);
        if (b == null) System.out.println("Book not found.");
        else System.out.println("Book found: " + b);
    }

    private static void searchByAuthor() {
        System.out.print("Enter author: ");
        sc.nextLine();
        String author = sc.nextLine();
        Book[] found = searchBookByAuthor(author);
        if (found.length == 0) System.out.println("No books found by author.");
        else {
            System.out.println("Books by " + author + ":");
            for (Book b : found) System.out.println(b);
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add a new book");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Search for books by author");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Display all books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchByTitle();
                case 3 -> searchByAuthor();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> displayAllBooks();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }
}
