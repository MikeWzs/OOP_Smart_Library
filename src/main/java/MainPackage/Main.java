package MainPackage;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Shelf<Book> bookShelf = new Shelf<>();
        bookShelf.addItem(new Book("B01", "Java Programming", "Rack A"));
        bookShelf.addItem(new Book("B02", "OOP Design Patterns", "Rack B"));
        bookShelf.addItem(new Book("B03", "Clean Code", "Rack A"));

        Shelf<Multimedia> mediaShelf = new Shelf<>();
        mediaShelf.addItem(new Multimedia("M01", "Tutorial Java (DVD)", "Rack C"));
        mediaShelf.addItem(new Multimedia("M02", "National Geographic (CD)", "Rack D"));
        
        Shelf<Journal> journalShelf = new Shelf<>();
        journalShelf.addItem(new Journal("J01", "IEEE Computer Science", "Rack E"));

        Member member = new Member("Mahasiswa Binus");

                                                                                                                                                                                                                                            
                                                                                                                                                                                                                               
System.out.println("""
  ______                               _     _____      _   __                                       
.' ____ \\                             / |_  |_   _|    (_) [  |                                      
| (___ \\_| _ .--..--.   ,--.   _ .--.`| |-'   | |      __   | |.--.   _ .--.  ,--.   _ .--.  _   __  
 _.____`. [ `.-. .-. | `'_\\ : [ `/'`\\]| |     | |   _ [  |  | '/'`\\ \\[ `/'`\\]`'_\\ : [ `/'`\\][ \\ [  ] 
| \\____) | | | | | | | // | |, | |    | |,   _| |__/ | | |  |  \\__/ | | |    // | |, | |     \\ '/ /  
 \\______.'[___||__||__]\\'-;__/[___]   \\__/  |________|[___][__;.__.' [___]   \\'-;__/[___]  [\\_:  /   
                                                                                            \\__.'    
""");

                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                


        int choice;
        do {
            System.out.println("\n+===================================+");
            System.out.println("|        SMART LIBRARY MENU         |");
            System.out.println("+===================================+");
            System.out.println("| 1. Lihat Koleksi (Buku/Media)     |");
            System.out.println("| 2. Pinjam Barang                  |");
            System.out.println("| 3. Kembalikan Barang              |");
            System.out.println("| 4. Lihat Barang Pinjaman          |");
            System.out.println("| 5. Hitung Denda (Calculate Fine)  |");
            System.out.println("| 0. Keluar                         |");
            System.out.println("+===================================+");
            System.out.print("Pilih menu: ");
            
            while (!sc.hasNextInt()) {
                System.out.print("Input harus angka. Pilih menu: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n=== KATALOG PERPUSTAKAAN ===");
                    printTableHeader();
                    printShelfItems(bookShelf);
                    printShelfItems(mediaShelf);
                    printShelfItems(journalShelf);
                    printTableFooter();
                    break;

                case 2:
                    System.out.print("Masukkan ID Barang yang ingin dipinjam (Contoh: B01): ");
                    String borrowId = sc.nextLine();
                    
                    LibraryResource itemToBorrow = bookShelf.findItem(borrowId);
                    if (itemToBorrow == null) itemToBorrow = mediaShelf.findItem(borrowId);
                    if (itemToBorrow == null) itemToBorrow = journalShelf.findItem(borrowId);

                    if (itemToBorrow != null) {
                        member.borrowItem(itemToBorrow);
                    } else {
                        System.out.println("Error: ID '" + borrowId + "' tidak ditemukan di katalog.");
                    }
                    break;

                case 3:
                    member.showBorrowedItems();
                    System.out.print("Masukkan ID Barang yang ingin dikembalikan: ");
                    String returnId = sc.nextLine();
                    member.returnItem(returnId);
                    break;

                case 4:
                    member.showBorrowedItems();
                    break;

                case 5:
                    System.out.print("Masukkan ID Barang yang terlambat: ");
                    String fineId = sc.nextLine();
                    LibraryResource fineItem = member.getBorrowedItem(fineId);
                    
                    if (fineItem != null) {
                        System.out.print("Berapa hari terlambat? ");
                        int days = sc.nextInt();
                        double fine = fineItem.calculateFine(days);
                        System.out.printf("Total Denda untuk '%s': Rp %.2f\n", fineItem.getTitle(), fine);
                    } else {
                        System.out.println("Error: Anda tidak sedang meminjam barang dengan ID tersebut.");
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan SmartLibrary.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (choice != 0);
        
        sc.close();
    }

    private static void printTableHeader() {
        System.out.println("+------+------------------------------+--------------+");
        System.out.println("| ID   | Judul                        | Lokasi       |");
        System.out.println("+------+------------------------------+--------------+");
    }

    private static void printTableFooter() {
        System.out.println("+------+------------------------------+--------------+");
    }

    private static void printShelfItems(Shelf<? extends LibraryResource> shelf) {
        for (LibraryResource item : shelf.getItems()) {
            System.out.printf("| %-4s | %-28s | %-12s |\n", 
                    item.getResourceID(), 
                    truncate(item.getTitle(), 28), 
                    item.getLocation());
        }
    }
    
    private static String truncate(String str, int width) {
        if (str.length() > width) {
            return str.substring(0, width - 3) + "...";
        }
        return str;
    }
}