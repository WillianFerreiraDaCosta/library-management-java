import java.util.ArrayList;
import java.util.List;

public class LibrarySingleton {

    private static LibrarySingleton instance;

    private final List<Book> catalog;
    private final List<Student> students;

    private LibrarySingleton(){
        this.catalog = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public static LibrarySingleton getInstance(){
        if (instance == null) instance = new LibrarySingleton();
        return instance;
    }

    // ---- Create / Register ----
    public boolean addNewBook(String isbn, String title, String author){
        return catalog.add(new Book(isbn, title, author));
    }
    public boolean addNewBook(Book b){ return catalog.add(b); }

    public boolean addNewStudent(String name, String registration){
        return students.add(new Student(name, registration));
    }
    public boolean addNewStudent(Student s){ return students.add(s); }

    // ---- Finders ----
    public Book findBook(String isbn){
        for (Book b : catalog){
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    public Student findStudent(String registration){
        for (Student s : students){
            if (s.getRegistration().equals(registration)) return s;
        }
        return null;
    }

    // ---- Actions ----
    public boolean startLoan(String registration, String isbn){
        Book b = findBook(isbn);
        Student s = findStudent(registration);
        if (b == null || s == null) return false;
        if (!b.isAvailable() || !s.canLoan()) return false;
        return s.loan(b); // marks both sides
    }

    public boolean startReturn(String registration, String isbn){
        Book b = findBook(isbn);
        Student s = findStudent(registration);
        if (b == null || s == null) return false;
        if (s.getCurrentLoan() != b) return false;
        return s.returnBook(); // marks both sides
    }

    // ---- Reports ----
    public void listCatalog(){
        if (catalog.isEmpty()){
            System.out.println("Catalog is empty.");
            return;
        }
        for (Book b : catalog) System.out.println(b);
    }

    public void listStudents(){
        if (students.isEmpty()){
            System.out.println("No students registered.");
            return;
        }
        for (Student s : students) System.out.println(s);
    }
}