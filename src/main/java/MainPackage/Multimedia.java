package MainPackage;

public class Multimedia extends LibraryResource implements Loanable {

    public Multimedia(String id, String title, String location, int stock) {
        super(id, title, location, stock);
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 10000;
    }

    @Override
    public void borrowItem() {
        System.out.println("Multimedia borrowed successfully.");
    }

    @Override
    public void returnItem() {
        System.out.println("Multimedia returned successfully.");
    }
}
