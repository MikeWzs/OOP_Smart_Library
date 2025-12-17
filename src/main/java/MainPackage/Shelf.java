package MainPackage;

import java.util.ArrayList;

public class Shelf<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void displayItems() {
        for (T item : items) {
            System.out.println(item);
        }
    }
}
