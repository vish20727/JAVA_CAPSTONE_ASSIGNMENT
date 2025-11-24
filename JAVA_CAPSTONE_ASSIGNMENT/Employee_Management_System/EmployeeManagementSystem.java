import java.util.Scanner;

abstract class Employee {
    protected int employeeId;
    protected String name;
    protected double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    public abstract double calculateBonus();

    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId + ", Name: " + name + ", Salary: " + salary + ", Bonus: " + calculateBonus());
    }

    public int getEmployeeId() {
        return employeeId;
    }
}

class Manager extends Employee {
    private String department;

    public Manager(int id, String name, double salary, String department) {
        super(id, name, salary);
        this.department = department;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.15; // higher bonus for managers
    }

    @Override
    public void displayDetails() {
        System.out.println("Manager ID: " + employeeId + ", Name: " + name + ", Department: " + department + ", Salary: " + salary + ", Bonus: " + calculateBonus());
    }
}

class Developer extends Employee {
    private String programmingLanguage;

    public Developer(int id, String name, double salary, String programmingLanguage) {
        super(id, name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10; // different bonus structure
    }

    @Override
    public void displayDetails() {
        System.out.println("Developer ID: " + employeeId + ", Name: " + name + ", Language: " + programmingLanguage + ", Salary: " + salary + ", Bonus: " + calculateBonus());
    }
}

public class EmployeeManagementSystem {
    private static Employee[] employees = new Employee[100];
    private static int count = 0;
    private static Scanner sc = new Scanner(System.in);

    private static void addManager() {
        System.out.print("Enter Manager ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        employees[count++] = new Manager(id, name, salary, dept);
        System.out.println("Manager added successfully.");
    }

    private static void addDeveloper() {
        System.out.print("Enter Developer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Programming Language: ");
        String lang = sc.nextLine();
        employees[count++] = new Developer(id, name, salary, lang);
        System.out.println("Developer added successfully.");
    }

    private static void displayEmployeeDetails() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                employees[i].displayDetails();
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private static void displayAllEmployees() {
        if (count == 0) {
            System.out.println("No employees to display.");
            return;
        }
        for (int i = 0; i < count; i++) {
            employees[i].displayDetails();
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Developer");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addManager();
                case 2 -> addDeveloper();
                case 3 -> displayEmployeeDetails();
                case 4 -> displayAllEmployees();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
}
