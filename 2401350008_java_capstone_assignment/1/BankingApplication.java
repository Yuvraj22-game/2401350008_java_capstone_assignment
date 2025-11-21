import java.util.Scanner;

public class BankingApplication {

    private Account[] accounts = new Account[100];
    private int accountCount = 0;
    private Scanner sc = new Scanner(System.in);
    private int nextAccountNumber = 1001;

    public void createAccount() {
        System.out.print("Enter account holder name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter email address: ");
        String email = sc.next();

        System.out.print("Enter phone number: ");
        String phone = sc.next();

        Account acc = new Account(nextAccountNumber, name, amount, email, phone);
        accounts[accountCount++] = acc;

        System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
        nextAccountNumber++;
    }

    public Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();

        Account acc = findAccount(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        acc.deposit(amount);
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();

        Account acc = findAccount(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        acc.withdraw(amount);
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();

        Account acc = findAccount(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        acc.displayAccountDetails();
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();

        Account acc = findAccount(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter new email: ");
        String email = sc.next();

        System.out.print("Enter new phone number: ");
        String phone = sc.next();

        acc.updateContactDetails(email, phone);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankingApplication app = new BankingApplication();
        app.mainMenu();
    }
}
