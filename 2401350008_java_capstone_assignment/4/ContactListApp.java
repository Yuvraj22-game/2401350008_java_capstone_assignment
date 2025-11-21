import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ContactListApp {

    // ---------------- CONTACT CLASS ----------------
    static class Contact {
        private String name;
        private String phoneNumber;
        private String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Name: " + name +
                    ", Phone: " + phoneNumber +
                    ", Email: " + email;
        }
    }

    // ---------------- CONTACT MANAGER ----------------
    static class ContactManager {

        private HashMap<String, Contact> contacts = new HashMap<>();

        public void addContact(Contact c) {
            contacts.put(c.getName(), c);
            System.out.println("Contact added successfully.\n");
        }

        public void removeContact(String name) {
            if (contacts.remove(name) != null) {
                System.out.println("Contact removed successfully.\n");
            } else {
                System.out.println("Contact not found.\n");
            }
        }

        public Contact searchContact(String name) {
            return contacts.get(name);
        }

        public void displayAllContacts() {
            if (contacts.isEmpty()) {
                System.out.println("No contacts to display.\n");
                return;
            }

            System.out.println("\n--- Contact List ---");
            for (Contact c : contacts.values()) {
                System.out.println(c);
            }
            System.out.println();
        }

        public void saveContactsToFile(String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (Contact c : contacts.values()) {
                    writer.write(c.getName() + "," + c.getPhoneNumber() + "," + c.getEmail());
                    writer.newLine();
                }
                System.out.println("Contacts saved to file.\n");
            } catch (IOException e) {
                System.out.println("Error saving contacts: " + e.getMessage());
            }
        }

        public void loadContactsFromFile(String filename) {
            File file = new File(filename);

            if (!file.exists()) {
                System.out.println("File does not exist. Cannot load.\n");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                contacts.clear();
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 3) {
                        Contact c = new Contact(data[0], data[1], data[2]);
                        contacts.put(c.getName(), c);
                    }
                }

                System.out.println("Contacts loaded successfully.\n");
            } catch (IOException e) {
                System.out.println("Error loading contacts: " + e.getMessage());
            }
        }
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();

        while (true) {
            System.out.println("Welcome to the Contact List Application!");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter contact name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    manager.addContact(new Contact(name, phone, email));
                    break;

                case 2:
                    System.out.print("Enter contact name to remove: ");
                    String removeName = sc.nextLine();
                    manager.removeContact(removeName);
                    break;

                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine();
                    Contact result = manager.searchContact(searchName);

                    if (result != null) {
                        System.out.println(result + "\n");
                    } else {
                        System.out.println("Contact not found.\n");
                    }
                    break;

                case 4:
                    manager.displayAllContacts();
                    break;

                case 5:
                    System.out.print("Enter filename to save: ");
                    String saveFile = sc.nextLine();
                    manager.saveContactsToFile(saveFile);
                    break;

                case 6:
                    System.out.print("Enter filename to load: ");
                    String loadFile = sc.nextLine();
                    manager.loadContactsFromFile(loadFile);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }
}
