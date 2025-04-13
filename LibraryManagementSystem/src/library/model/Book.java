package library.model;


public class Book {
    private final int bookId;
    private String title;
    private boolean isAvailable;

    public Book(int bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.isAvailable = true;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
