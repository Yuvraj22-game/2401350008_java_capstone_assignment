import java.util.Scanner;

public class LibraryApp {

    // -------------------- BOOK CLASS --------------------
    static class Book {
        private String title;
        private String author;
        private boolean isAvailable;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true; // default state
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean status) {
            this.isAvailable = status;
        }

        @Override
        public String toString() {
            return title + " by " + author + " | " +
                    (isAvailable ? "Available" : "Borrowed");
        }
    }

    // -------------------- LIBRARY CLASS --------------------
    static class Library {

        private Book[] books = new Book[100];
        private int count = 0;

        public void addBook(Book b) {
            if (count < books.length) {
                books[count++] = b;
                System.out.println("Book added successfully.\n");
            } else {
                System.out.println("Library is full! Cannot add more books.\n");
            }
        }

        // Overloaded search method by title
        public Book searchBook(String title) {
            for (int i = 0; i < count; i++) {
                if (books[i].getTitle().equalsIgnoreCase(title)) {
                    return books[i];
                }
            }
            return null;
        }

        // Overloaded search method by author
        public void searchBookByAuthor(String author) {
            boolean found = false;

            for (int i = 0; i < count; i++) {
                if (books[i].getAuthor().equalsIgnoreCase(author)) {
                    System.out.println(books[i]);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No books found for this author.\n");
            }
        }

        public void borrowBook(String title) {
            Book b = searchBook(title);

            if (b == null) {
                System.out.println("Book not found.\n");
                return;
            }

            if (!b.isAvailable()) {
                System.out.println("Book is already borrowed.\n");
                return;
            }

            b.setAvailable(false);
            System.out.println("You have borrowed the book.\n");
        }

        public void returnBook(String title) {
            Book b = searchBook(title);

            if (b == null) {
                System.out.println("Book not found.\n");
                return;
            }

            if (b.isAvailable()) {
                System.out.println("Book is not borrowed.\n");
                return;
            }

            b.setAvailable(true);
            System.out.println("Book returned successfully.\n");
        }

        public void displayAllBooks() {
            if (count == 0) {
                System.out.println("No books in the library.\n");
                return;
            }

            System.out.println("\n--- Library Books ---");
            for (int i = 0; i < count; i++) {
                System.out.println(books[i]);
            }
            System.out.println();
        }
    }

    // -------------------- MAIN APPLICATION --------------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("Welcome to the Library Management System!");
            System.out.println("1. Add a new book");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Search for books by author");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Display all books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter book author: ");
                    String author = sc.nextLine();

                    library.addBook(new Book(title, author));
                    break;

                case 2:
                    System.out.print("Enter book title: ");
                    String searchTitle = sc.nextLine();

                    Book found = library.searchBook(searchTitle);

                    if (found != null) {
                        System.out.println("\nBook found: " + found + "\n");
                    } else {
                        System.out.println("Book not found.\n");
                    }
                    break;

                case 3:
                    System.out.print("Enter author name: ");
                    String searchAuthor = sc.nextLine();

                    library.searchBookByAuthor(searchAuthor);
                    break;

                case 4:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = sc.nextLine();

                    library.borrowBook(borrowTitle);
                    break;

                case 5:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();

                    library.returnBook(returnTitle);
                    break;

                case 6:
                    library.displayAllBooks();
                    break;

                case 7:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }
}
