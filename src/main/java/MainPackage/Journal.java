package MainPackage;

public class Journal extends LibraryResource {

    public Journal(String id, String title, String location) {
        super(id, title, location);
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 5000;
    }
}
