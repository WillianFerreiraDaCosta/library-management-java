public class Book {
    private String isbn;
    private String title;
    private String author;

    private boolean available;  // exemplar status
    private Student holder;     // who has the book (if any)

    public Book(String isbn, String title, String author){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
        this.holder = null;
    }

    // Getters / Setters
    public String getIsbn()   { return isbn; }
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }

    public void setTitle(String title)  { this.title = title; }
    public void setAuthor(String author){ this.author = author; }

    public boolean isAvailable() { return available; }
    public Student getHolder()   { return holder; }

    // Loan/return rules
    void markAsLoanedTo(Student s){
        this.available = false;
        this.holder = s;
    }

    public boolean markAsReturned(){
        if (this.available) return false;
        this.available = true;
        this.holder = null;
        return true;
    }

    // New copy (clone-like)
    public Book createCopy(){
        return new Book(this.isbn, this.title, this.author);
    }

    @Override
    public String toString(){
        String st = available ? "Available" :
                "Loaned to " + (holder != null ? holder.getName() : "—");
        return "[" + isbn + "] " + title + " — " + author + " (" + st + ")";
    }
}