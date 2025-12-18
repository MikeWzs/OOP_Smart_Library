package MainPackage;

public abstract class LibraryResource {
    protected String resourceID;
    protected String title;
    protected String location;
    protected int stock;

    public LibraryResource(String resourceID, String title, String location, int stock) {
        this.resourceID = resourceID;
        this.title = title;
        this.location = location;
        this.stock = stock;
    }

    public abstract double calculateFine(int daysLate);

    public String getResourceID() {
        return resourceID;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }
    
    public int getStock() {
        return stock;
    }

    public void decreaseStock() {
        if (stock > 0) stock--;
    }

    public void increaseStock() {
        stock++;
    }

    public String displayInfo() {
        return String.format("%-5s | %-30s | %-10s | Stock: %d", resourceID, title, location, stock);
    }
}