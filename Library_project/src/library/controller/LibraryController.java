package library.controller;

import library.model.Book;
import library.model.Catalog;
import library.model.User;
import library.view.LibraryView;

import java.util.List;

public class LibraryController {
    private final Catalog catalog;
    private final LibraryView view;

    public LibraryController(Catalog catalog, LibraryView view) {
        this.catalog = catalog;
        this.view = view;
    }

    public void start() {
        while (true) {
            int choice = view.displayMenu();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> catalog.displayAllBooks();
                case 6 -> {
                    view.displayMessage("Exiting...");
                    return;
                }
                default -> view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void addBook() {
        String title = view.getStringInput("Enter book title: ");
        Book book = new Book(catalog.generateBookId(), title);
        catalog.addBook(book);
        view.displayMessage("Book added successfully.");
    }

    private void searchBook() {
        String query = view.getStringInput("Enter book title or keyword: ");
        List<Book> results = catalog.searchBooks(query);
        if (results.isEmpty()) {
            view.displayMessage("No books found.");
        } else {
            results.forEach(Book::displayBookInfo);
        }
    }

    private void borrowBook() {
        int bookId = view.getIntInput("Enter book ID to borrow: ");
        Book book = catalog.searchById(bookId);
        if (book == null || !book.isAvailable()) {
            view.displayMessage("Book is not available.");
            return;
        }
        book.borrowBook();
        view.displayMessage("Book borrowed successfully.");
    }

    private void returnBook() {
        int bookId = view.getIntInput("Enter book ID to return: ");
        Book book = catalog.searchById(bookId);
        if (book == null || book.isAvailable()) {
            view.displayMessage("Book is not borrowed.");
            return;
        }
        book.returnBook();
        view.displayMessage("Book returned successfully.");
    }
}
