package library.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int userId;
    private final String name;
    private final List<Loan> loans;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    public List<Loan> getActiveLoans() {
        List<Loan> activeLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }


    public boolean hasOverdueLoans() {
        return loans.stream().anyMatch(Loan::isOverdue);
    }

    public void displayInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Loans:");
        for (Loan loan : loans) {
            System.out.println(" - " + loan.getLoanInfo());
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

