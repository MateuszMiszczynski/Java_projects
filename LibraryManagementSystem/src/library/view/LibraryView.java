package library.view;

import java.util.Scanner;

public class LibraryView {
    private final Scanner scanner;

    public LibraryView() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("Library System Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Display All Books");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
