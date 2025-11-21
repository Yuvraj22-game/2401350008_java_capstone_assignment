public class Manager extends Employee {
    private String department;

    public Manager(int employeeId, String name, double salary, String department) {
        super(employeeId, name, salary);
        this.department = department;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10; // Managers get 10% bonus
    }

    @Override
    public void displayDetails() {
        System.out.println("----- Manager Details -----");
        super.displayDetails();
        System.out.println("Department: " + department);
        System.out.println("---------------------------");
    }
}
