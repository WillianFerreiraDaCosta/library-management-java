public class Student {
    private String name;
    private String registration;   // unique id
    private Book currentLoan;      // at most ONE book at a time

    public Student(String name, String registration){
        this.name = name;
        this.registration = registration;
        this.currentLoan = null;
    }

    // Encapsulation
    public String getName() { return name; }
    public String getRegistration() { return registration; }
    public Book getCurrentLoan() { return currentLoan; }
    private void setCurrentLoan(Book book){ this.currentLoan = book; }

    // Behaviors
    public boolean canLoan() {
        return this.currentLoan == null;
    }

    public boolean loan(Book book){
        if (book == null || !book.isAvailable() || !canLoan()) return false;
        this.setCurrentLoan(book);
        book.markAsLoanedTo(this);
        return true;
    }

    public boolean returnBook(){
        if (this.currentLoan == null) return false;
        this.currentLoan.markAsReturned();
        this.setCurrentLoan(null);
        return true;
    }

    @Override
    public String toString() {
        String status = (currentLoan == null) ? "no active loans" :
                "holding \"" + currentLoan.getTitle() + "\"";
        return name + " (reg: " + registration + ") - " + status;
    }
}