import java.util.Scanner;

public class ManagementSystem {

    private Employee[] employees = new Employee[100];
    private int count = 0;
    private Scanner sc = new Scanner(System.in);

    public void addManager() {
        System.out.print("Enter Employee ID: ");
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
        System.out.println("Manager added successfully.\n");
    }

    public void addDeveloper() {
        System.out.print("Enter Employee ID: ");
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
        System.out.println("Developer added successfully.\n");
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void displayEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();

        Employee emp = searchEmployee(id);

        if (emp == null) {
            System.out.println("Employee not found.\n");
        } else {
            emp.displayDetails();
        }
    }

    public void displayAllEmployees() {
        if (count == 0) {
            System.out.println("No employees added yet.");
            return;
        }
        for (int i = 0; i < count; i++) {
            employees[i].displayDetails();
            System.out.println();
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.println("Welcome to the Employee Management System!");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Developer");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addManager();
                    break;
                case 2:
                    addDeveloper();
                    break;
                case 3:
                    displayEmployee();
                    break;
                case 4:
                    displayAllEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void main(String[] args) {
        ManagementSystem ms = new ManagementSystem();
        ms.mainMenu();
    }
}
