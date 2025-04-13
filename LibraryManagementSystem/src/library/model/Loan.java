package library.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Loan {
    private final User user;
    private final Book book;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private boolean isReturned;

    public Loan(User user, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }

    public void returnBook() {
        isReturned = true;
        book.returnBook();
    }

    public boolean isOverdue() {
        return !isReturned && LocalDate.now().isAfter(dueDate);
    }

    public long calculateFine() {
        if (!isOverdue()) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now()) * 2; // 2 zł za każdy dzień zwłoki
    }

    public String getLoanInfo() {
        return "User: " + user.getName() +
                ", Book: " + book.getTitle() +
                ", Due Date: " + dueDate +
                ", Returned: " + (isReturned ? "Yes" : "No");
    }

    public boolean isReturned() {
        return isReturned;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }
}
