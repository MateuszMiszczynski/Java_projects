package library.model;

public class Employee {
    private final int employeeId;
    private final String name;

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public void addBook(Catalog catalog, Book book) {
        catalog.addBook(book);
    }

    public void removeBook(Catalog catalog, Book book) {
        catalog.removeBook(book);
    }

    public void updateBookInfo(Book book, String newTitle) {
        book.setTitle(newTitle);
    }

    public void displayLoanStatus(Catalog catalog) {
        catalog.getBooks().forEach(book -> {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
        });
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}

