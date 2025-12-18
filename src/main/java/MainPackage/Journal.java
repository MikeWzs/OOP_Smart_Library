package MainPackage;

public class Journal extends LibraryResource {

    public Journal(String id, String title, String location, int stock) {
        super(id, title, location, stock);
    }

    @Override
    public double calculateFine(int daysLate) {
        if (daysLate <= 3) {
            return daysLate * 2000;
        } else {
            return (3 * 2000) + ((daysLate - 3) * 5000);
        }
    }
}
