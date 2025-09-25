import utilitarios.Teclado;

public class App {

    static LibrarySingleton library;

    private static void init(){
        library = LibrarySingleton.getInstance();

        // Seed books
        Book b1 = new Book("111111", "OOP Handbook", "Valdemar Lorenzon Junior");
        Book b2 = new Book("112112", "OOP Exercises", "José Toniazzo");
        Book b3 = b1.createCopy();

        library.addNewBook(b1);
        library.addNewBook(b2);
        library.addNewBook(b3);

        // Seed students
        library.addNewStudent("Willian", "123hhjhjh");
        library.addNewStudent("John Doe", "66666hhjhjh");
        library.addNewStudent("Jane Roe", "55555hhjhjh");
    }

    private static void showMenu(){
        System.out.println("\n=== LIBRARY MENU ===");
        System.out.println("1 - List catalog");
        System.out.println("2 - Loan a book");
        System.out.println("3 - Return a book");
        System.out.println("4 - Find book");
        System.out.println("5 - Find student");
        System.out.println("6 - Add book");
        System.out.println("7 - Add student");
        System.out.println("0 - Exit");
    }

    public static void main(String[] args) {
        init();
        int opt;

        do {
            showMenu();
            opt = Teclado.readInt("Choose an option: ");

            switch (opt) {
                case 1:
                    library.listCatalog();
                    break;

                case 2: { // loan
                    String reg = Teclado.readString("Student registration: ");
                    String isbn = Teclado.readString("Book ISBN: ");
                    boolean ok = library.startLoan(reg, isbn);
                    System.out.println(ok ? "Loan completed!" : "Loan failed.");
                    break;
                }

                case 3: { // return
                    String reg = Teclado.readString("Student registration: ");
                    String isbn = Teclado.readString("Book ISBN: ");
                    boolean ok = library.startReturn(reg, isbn);
                    System.out.println(ok ? "Return completed!" : "Return failed.");
                    break;
                }

                case 4: {
                    String isbn = Teclado.readString("Enter ISBN: ");
                    Book b = library.findBook(isbn);
                    System.out.println(b == null ? "Book not found." : b.toString());
                    break;
                }

                case 5: {
                    String reg = Teclado.readString("Enter registration: ");
                    Student s = library.findStudent(reg);
                    System.out.println(s == null ? "Student not found." : s.toString());
                    break;
                }

                case 6: {
                    String isbn = Teclado.readString("ISBN: ");
                    String title = Teclado.readString("Title: ");
                    String author = Teclado.readString("Author: ");
                    library.addNewBook(isbn, title, author);
                    System.out.println("Book added!");
                    break;
                }

                case 7: {
                    String name = Teclado.readString("Name: ");
                    String reg = Teclado.readString("Registration: ");
                    library.addNewStudent(name, reg);
                    System.out.println("Student added!");
                    break;
                }

                case 0:
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        } while (opt != 0);
    }
}