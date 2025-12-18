package MainPackage;

public class Member {
    private String name;
    private LibraryResource[] borrowedItems = new LibraryResource[3];
    private int count = 0;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void borrowItem(LibraryResource item) {
        if (count >= 3) {
            System.out.println("Error: Batas peminjaman tercapai (Maksimal 3 item).");
            return;
        }
        
        borrowedItems[count++] = item;
        
        //  Memanggil method interface jika item adalah Loanable
        if (item instanceof Loanable) {
            ((Loanable) item).borrowItem();
        } else {
            System.out.println(item.getTitle() + " berhasil dipinjam.");
        }
    }

    public void returnItem(String resourceID) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (borrowedItems[i].getResourceID().equalsIgnoreCase(resourceID)) {
                LibraryResource item = borrowedItems[i];
                
                // Geser array untuk mengisi kekosongan
                for (int j = i; j < count - 1; j++) {
                    borrowedItems[j] = borrowedItems[j + 1];
                }
                borrowedItems[count - 1] = null;
                count--;
                found = true;

                //  Panggil method interface returnItem
                if (item instanceof Loanable) {
                    ((Loanable) item).returnItem();
                } else {
                    System.out.println(item.getTitle() + " berhasil dikembalikan.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Error: Item dengan ID " + resourceID + " tidak ditemukan dalam daftar peminjaman.");
        }
    }

    public void showBorrowedItems() {
        System.out.println("\n--- Barang yang Dipinjam oleh " + name + " ---");
        if (count == 0) {
            System.out.println("(Tidak ada barang yang dipinjam)");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + borrowedItems[i].displayInfo());
            }
        }
    }
    
    public LibraryResource getBorrowedItem(String id) {
        for (int i = 0; i < count; i++) {
            if (borrowedItems[i].getResourceID().equalsIgnoreCase(id)) {
                return borrowedItems[i];
            }
        }
        return null;
    }
}