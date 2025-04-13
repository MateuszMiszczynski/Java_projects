package library;

import library.controller.Library;
import library.model.*;
import library.utils.FileManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        // Inicjalizacja katalogu i biblioteki
        Catalog catalog = Catalog.getInstance();
        String adminPasswd = "admin"; // Hasło administratora
        Library library = new Library();
        // Inicjalizacja pracownika
        Employee employee = new Employee(1, "Jan Kowalski");
        library.registerEmployee(employee);
        // Inicjalizacja użytkownika
        User user = new User(1, "Anna Nowak");
        library.registerNewUser(user);

        mainmenu:
        while (true) {
            System.out.println("\n=== Witamy! ===");
            System.out.println("1. Użytkownik");
            System.out.println("2. Pracownik");
            System.out.println("3. Administrator");
            System.out.println("4. Zakończ");
            int selectedMode = scanner.nextInt();
            scanner.nextLine();

            switch (selectedMode) {
                case 1:
                    library.displayUsers();
                    int selectedId;
                    while (true) {
                        System.out.println("Wybierz id użytkownika:");
                        selectedId = scanner.nextInt();
                        scanner.nextLine();
                        if (library.findUserById(selectedId) != null) {
                            break;
                        } else {
                            System.out.println("Nie znaleziono.");
                        }
                    }
                    user = library.findUserById(selectedId);

                    us:
                    while (true) {
                        System.out.println("\n=== Menu Użytkownika ===");
                        System.out.println("1. Wypożycz książkę");
                        System.out.println("2. Zwrot książki");
                        System.out.println("3. Wyświetl raport biblioteki");
                        System.out.println("4. Zakończ");

                        System.out.print("Wybierz opcję: ");
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                System.out.println("Dostępne książki:");
                                catalog.displayAvailableBooks();

                                System.out.print("Podaj ID książki do wypożyczenia: ");
                                int bookId = scanner.nextInt();
                                scanner.nextLine();

                                Book bookToBorrow = catalog.searchById(bookId);
                                if (bookToBorrow != null && bookToBorrow.isAvailable()) {
                                    Loan loan = new Loan(user, bookToBorrow, LocalDate.now(), LocalDate.now().plusDays(14));
                                    user.addLoan(loan);
                                    bookToBorrow.borrowBook();
                                    System.out.println("Wypożyczono książkę: " + bookToBorrow.getTitle());
                                } else {
                                    System.out.println("Książka o ID " + bookId + " nie jest dostępna.");
                                }
                                break;

                            case 2:
                                System.out.println("Aktywne wypożyczenia użytkownika:");
                                user.getActiveLoans().forEach(loan -> {
                                    System.out.println("ID książki: " + loan.getBook().getBookId() + " - Tytuł: " + loan.getBook().getTitle());
                                });

                                System.out.print("Podaj ID książki do zwrotu: ");
                                int returnBookId = scanner.nextInt();
                                scanner.nextLine();

                                Loan loanToReturn = user.getActiveLoans().stream()
                                        .filter(loan -> loan.getBook().getBookId() == returnBookId)
                                        .findFirst()
                                        .orElse(null);

                                if (loanToReturn != null) {
                                    loanToReturn.returnBook();
                                    user.removeLoan(loanToReturn);
                                    loanToReturn.getBook().returnBook();
                                    System.out.println("Zwrócono książkę: " + loanToReturn.getBook().getTitle());
                                } else {
                                    System.out.println("Nie znaleziono wypożyczenia o ID książki " + returnBookId);
                                }
                                break;

                            case 3:
                                library.displayLibrarySummary();
                                break;

                            case 4:
                                break us;

                            default:
                                System.out.println("Niepoprawna opcja, spróbuj ponownie.");
                        }
                    }
                    break;

                case 2:
                    library.displayEmployees();
                    int selectedEId;
                    while (true) {
                        System.out.println("Wybierz id pracownika:");
                        selectedEId = scanner.nextInt();
                        if (library.findEmployeeById(selectedEId) != null) {
                            break;
                        } else {
                            System.out.println("Nie znaleziono.");
                        }
                    }
                    scanner.nextLine();
                    employee = library.findEmployeeById(selectedEId);

                    worker:
                    while (true) {
                        System.out.println("\n=== Menu Pracownika ===");
                        System.out.println("1. Dodaj książkę do katalogu");
                        System.out.println("2. Wyświetl raport biblioteki");
                        System.out.println("3. Zakończ");
                        System.out.println("4. Dodaj użytkownika");
                        System.out.println("5. Usuń użytkownika");

                        System.out.print("Wybierz opcję: ");
                        int employeeOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (employeeOption) {
                            case 1:
                                System.out.print("Podaj tytuł książki: ");
                                String title = scanner.nextLine();
                                Book newBook = new Book(catalog.generateBookId(), title);
                                employee.addBook(catalog, newBook);
                                System.out.println("Dodano książkę: " + title);
                                library.setCatalog(catalog);
                                break;

                            case 2:
                                library.displayLibrarySummary();
                                break;

                            case 3:
                                break worker;

                            case 4:
                                System.out.println("Podaj nazwę użytkownika:");
                                String username = scanner.nextLine();
                                scanner.nextLine();
                                int tempId = 1;
                                while (true) {
                                    if (library.findUserById(tempId) == null)
                                        break;
                                    else tempId++;
                                }
                                User newUser = new User((tempId), username);
                                library.registerNewUser(newUser);
                                break;

                            case 5:
                                library.displayUsers();
                                System.out.println("Wybierz id użytkownika:");
                                int idToDelete = scanner.nextInt();
                                scanner.nextLine();
                                if (library.findUserById(idToDelete) != null) {
                                    library.removeUser(library.findUserById(idToDelete));
                                    System.out.println("Usunięto wskazanego użytkownika.");
                                } else {
                                    System.out.println("Nie znaleziono użytkownika.");
                                }
                                break;

                            default:
                                System.out.println("Niepoprawna opcja, spróbuj ponownie.");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Podaj hasło administratora: ");
                    String passwd = scanner.nextLine();

                    if (!passwd.equals(adminPasswd)) {
                        System.out.println("Niepoprawne hasło! Powrót do menu głównego.");
                        break;
                    }

                    admin:
                    while (true) {
                        System.out.println("\n=== Menu Administratora ===");
                        System.out.println("1. Dodaj pracownika");
                        System.out.println("2. Usuń pracownika");
                        System.out.println("3. Zakończ");
                        int adminOption = scanner.nextInt();
                        scanner.nextLine();
                        switch (adminOption) {
                            case 1:
                                System.out.println("Podaj nazwę użytkownika");
                                String EmployeeUsername = scanner.nextLine();
                                int tempEmpId = 1;
                                while (true) {
                                    if (library.findEmployeeById(tempEmpId) == null) {
                                        break;
                                    } else {
                                        tempEmpId++;
                                    }
                                }
                                Employee newEmployee = new Employee((tempEmpId), EmployeeUsername);
                                library.registerEmployee(newEmployee);
                                break;

                            case 2:
                                library.displayEmployees();
                                System.out.println("Wybierz ID");
                                int EmIdToDetele = scanner.nextInt();
                                scanner.nextLine();
                                if (library.findEmployeeById(EmIdToDetele) != null) {
                                    library.removeEmployee(library.findEmployeeById(EmIdToDetele));
                                } else {
                                    System.out.println("Nie znaleziono pracownika");
                                }
                                break;

                            case 3:
                                break admin;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Zakończono działanie programu.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Niepoprawna opcja, spróbuj ponownie.");
            }
        }
    }
}
