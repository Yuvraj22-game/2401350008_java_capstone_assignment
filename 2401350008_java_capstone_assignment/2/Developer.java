public class Developer extends Employee {
    private String programmingLanguage;

    public Developer(int employeeId, String name, double salary, String programmingLanguage) {
        super(employeeId, name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.08; // Developers get 8% bonus
    }

    @Override
    public void displayDetails() {
        System.out.println("----- Developer Details -----");
        super.displayDetails();
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("------------------------------");
    }
}
