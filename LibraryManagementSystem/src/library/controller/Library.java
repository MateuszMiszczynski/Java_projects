package library.controller;
import library.model.*;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Library implements Serializable {
    private Catalog catalog;
    private List<User> users;
    private List<Employee> employees;

    public Library() {
        this.catalog = new Catalog();
        this.users = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void registerNewUser(User user) {
        users.add(user);
    }
    public void setCatalog (Catalog catalog){
        this.catalog=catalog;
    }
    public void removeUser(User user) {
        users.remove(user);
    }
    public int numOfUsers(){
        return users.size();
    }
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void registerEmployee(Employee employee) {
        employees.add(employee);
    }

    public void displayUsers(){
        for (int i=0; i<users.size(); i++){
            System.out.println(users.get(i).getUserId()+" "+users.get(i).getName());
        }
    }
    public void displayEmployees(){
        for (int i=0; i<employees.size(); i++){
            System.out.println(employees.get(i).getEmployeeId()+" "+employees.get(i).getName());
        }
    }
    public User findUserById(int userId) {
        return users.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public Employee findEmployeeById(int employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId() == employeeId)
                .findFirst()
                .orElse(null);
    }

    public void displayLibrarySummary() {
        System.out.println("=== Podsumowanie biblioteki ===");
        System.out.println("Liczba książek w katalogu: " + catalog.getBooks().size());
        System.out.println("Liczba użytkowników: " + users.size());
        System.out.println("Liczba pracowników: " + employees.size());

        long activeLoans = users.stream()
                .mapToLong(user -> user.getActiveLoans().size())
                .sum();
        System.out.println("Liczba aktywnych wypożyczeń: " + activeLoans);

        long overdueLoans = users.stream()
                .filter(User::hasOverdueLoans)
                .count();
        System.out.println("Liczba przeterminowanych wypożyczeń: " + overdueLoans);
    }
}