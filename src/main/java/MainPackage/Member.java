package MainPackage;

public class Member {
    private String name;
    private LibraryResource[] borrowedItems = new LibraryResource[3];
    private int count = 0;

    public Member(String name) {
        this.name = name;
    }

    public void borrowItem(LibraryResource item) {
        if (count >= 3) {
            System.out.println("Borrow limit reached (max 3 items).");
            return;
        }
        borrowedItems[count++] = item;
        System.out.println(name + " borrowed: " + item.getTitle());
    }

    public void showBorrowedItems() {
        System.out.println("Borrowed Items:");
        for (LibraryResource item : borrowedItems) {
            if (item != null) {
                System.out.println("- " + item.getTitle());
            }
        }
    }
}
