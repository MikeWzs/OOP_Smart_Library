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
    
   public int getBorrowedCount() {
        return count;
    }

    public void borrowItem(LibraryResource item) {
        if (count >= 3) {
            System.out.println("Gagal: " + name + " sudah meminjam 3 barang (Batas Maksimal).");
            return;
        }

        if (item.getStock() <= 0) {
            System.out.println("Gagal: Stok untuk '" + item.getTitle() + "' Habis!");
            return;
        }
        
        item.decreaseStock();
        borrowedItems[count++] = item;
        
        System.out.println("Sukses: " + name + " meminjam '" + item.getTitle() + "'");
        
        if (item instanceof Loanable) {
            ((Loanable) item).borrowItem();
        }
    }

    public void returnItem(String resourceID) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (borrowedItems[i].getResourceID().equalsIgnoreCase(resourceID)) {
                LibraryResource item = borrowedItems[i];
                
                item.increaseStock();
                
                for (int j = i; j < count - 1; j++) {
                    borrowedItems[j] = borrowedItems[j + 1];
                }
                borrowedItems[count - 1] = null;
                count--;
                found = true;

                System.out.println("Sukses: " + name + " mengembalikan '" + item.getTitle() + "'");
                
                if (item instanceof Loanable) {
                    ((Loanable) item).returnItem();
                } else {
                    System.out.println(item.getTitle() + " berhasil dikembalikan.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Error: Anda tidak meminjam barang dengan ID " + resourceID);
        }
    }

    public void showBorrowedItems() {
        System.out.println("\n--- Barang yang Dipinjam oleh " + name + " ---");
        if (count == 0) {
            System.out.println("(Tidak ada barang yang dipinjam)");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println("- " + borrowedItems[i].getResourceID() + ": " + borrowedItems[i].getTitle());
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