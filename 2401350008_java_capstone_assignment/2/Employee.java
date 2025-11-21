public class Employee {
    protected int employeeId;
    protected String name;
    protected double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    // Overloaded constructor for flexibility
    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = 0.0;
    }

    public double calculateBonus() {
        return salary * 0.05; // 5% default bonus
    }

    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
