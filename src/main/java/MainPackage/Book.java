package MainPackage;

public class Book extends LibraryResource implements Loanable {

    public Book(String id, String title, String location) {
        super(id, title, location);
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 2000;
    }

    @Override
    public void borrowItem() {
        System.out.println("Book borrowed successfully.");
    }

    @Override
    public void returnItem() {
        System.out.println("Book returned successfully.");
    }
}