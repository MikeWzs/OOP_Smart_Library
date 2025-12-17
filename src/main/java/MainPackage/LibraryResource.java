package MainPackage;

public abstract class LibraryResource {
    protected String resourceID;
    protected String title;
    protected String location;

    public LibraryResource(String resourceID, String title, String location) {
        this.resourceID = resourceID;
        this.title = title;
        this.location = location;
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

    public String displayInfo() {
        return resourceID + " | " + title + " | " + location;
    }
}