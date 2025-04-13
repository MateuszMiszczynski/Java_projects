package library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
    private static Catalog instance;
    private final List<Book> books;
    private int bookIdCounter;

    public Catalog() {
        books = new ArrayList<>();
        bookIdCounter = 1;
    }

    public static synchronized Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }


    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Book searchById(int bookId) {
        return books.stream()
                .filter(book -> book.getBookId() == bookId)
                .findFirst()
                .orElse(null);
    }

    public void displayAllBooks() {
        for (Book book : books) {
            book.displayBookInfo();
        }
    }

    public void displayAvailableBooks() {
        books.stream()
                .filter(Book::isAvailable)
                .forEach(Book::displayBookInfo);
    }

    public int generateBookId() {
        return bookIdCounter++;
    }
}

