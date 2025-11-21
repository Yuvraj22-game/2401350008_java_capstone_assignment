import java.util.InputMismatchException;
import java.util.Scanner;

public class ResultManager {

    // ---------------- CUSTOM EXCEPTION ----------------
    static class InvalidMarksException extends Exception {
        public InvalidMarksException(String message) {
            super(message);
        }
    }

    // ---------------- STUDENT CLASS ----------------
    static class Student {
        private int rollNumber;
        private String studentName;
        private int[] marks = new int[3];

        public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
            this.rollNumber = rollNumber;
            this.studentName = studentName;
            this.marks = marks;
            validateMarks();
        }

        public void validateMarks() throws InvalidMarksException {
            for (int i = 0; i < 3; i++) {
                if (marks[i] < 0 || marks[i] > 100) {
                    throw new InvalidMarksException("Invalid marks for subject " + (i + 1) + ": " + marks[i]);
                }
            }
        }

        public double calculateAverage() {
            int sum = 0;
            for (int m : marks) {
                sum += m;
            }
            return sum / 3.0;
        }

        public void displayResult() {
            System.out.println("\n----- Student Details -----");
            System.out.println("Roll Number: " + rollNumber);
            System.out.println("Name: " + studentName);
            System.out.print("Marks: ");

            for (int m : marks) {
                System.out.print(m + " ");
            }

            double avg = calculateAverage();
            System.out.println("\nAverage: " + avg);

            String result = (avg >= 40) ? "Pass" : "Fail";
            System.out.println("Result: " + result);
            System.out.println("---------------------------\n");
        }

        public int getRollNumber() {
            return rollNumber;
        }
    }

    // ---------------- MAIN APPLICATION ----------------

    private Student[] students = new Student[100];
    private int count = 0;
    private Scanner sc = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Student s = new Student(roll, name, marks);
            students[count++] = s;
            System.out.println("Student added successfully!\n");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage() + ". Returning to main menu...\n");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input! Marks must be numbers.\n");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public Student findStudent(int roll) {
        for (int i = 0; i < count; i++) {
            if (students[i].getRollNumber() == roll) {
                return students[i];
            }
        }
        return null;
    }

    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();

            Student s = findStudent(roll);
            if (s == null) {
                System.out.println("Student not found.\n");
            } else {
                s.displayResult();
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Enter a valid roll number.\n");
            sc.nextLine();
        }
    }

    public void mainMenu() {
        try {
            while (true) {
                System.out.println("===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;

                    case 2:
                        showStudentDetails();
                        break;

                    case 3:
                        System.out.println("Exiting system...");
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.\n");
                }
            }
        } finally {
            System.out.println("Program closed. Resources released.");
            sc.close();
        }
    }

    public static void main(String[] args) {
        ResultManager rm = new ResultManager();
        rm.mainMenu();
    }
}
