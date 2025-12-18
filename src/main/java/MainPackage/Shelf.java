package MainPackage;

import java.util.ArrayList;

public class Shelf<T extends LibraryResource> {
    private ArrayList<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public T findItem(String id) {
        for (T item : items) {
            if (item.getResourceID().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
}