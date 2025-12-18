package MainPackage;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Member> memberList = new ArrayList<>();
    private static Member currentMember = null;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Shelf<Book> bookShelf = new Shelf<>();
        bookShelf.addItem(new Book("B01", "Java Programming", "Rack A", 2));
        bookShelf.addItem(new Book("B02", "OOP Design Patterns", "Rack B", 1));
        bookShelf.addItem(new Book("B03", "Clean Code", "Rack A", 3));

        Shelf<Multimedia> mediaShelf = new Shelf<>();
        mediaShelf.addItem(new Multimedia("M01", "Tutorial Java (DVD)", "Rack C", 1));
        mediaShelf.addItem(new Multimedia("M02", "National Geographic (CD)", "Rack D", 2));
        
        Shelf<Journal> journalShelf = new Shelf<>();
        journalShelf.addItem(new Journal("J01", "IEEE Computer Science", "Rack E", 5));

        memberList.add(new Member("Adi (Mhs)"));
        memberList.add(new Member("Calvin (Mhs)"));
        memberList.add(new Member("Christian (Mhs)"));
        memberList.add(new Member("Ko Sinji (Dosen)"));
        currentMember = memberList.get(0);

                                                                                                                                                                                                                                            
                                                                                                                                                                                                                               
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
            System.out.printf("| User Aktif: %-21s |\n", truncate(currentMember.getName(), 21));
            System.out.println("+===================================+");
            System.out.println("| 1. Lihat Koleksi (Buku/Media)     |");
            System.out.println("| 2. Pinjam Barang                  |");
            System.out.println("| 4. Info Member (Cek Pinjaman)     |");
            System.out.println("| 5. Hitung Denda (Jurnal Progresif)|");
            System.out.println("| 6. GANTI MEMBER / DAFTAR BARU     |");
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
                    System.out.print("Masukkan ID Barang (Contoh: B01): ");
                    String borrowId = sc.nextLine();
                    
                    LibraryResource itemToBorrow = bookShelf.findItem(borrowId);
                    if (itemToBorrow == null) itemToBorrow = mediaShelf.findItem(borrowId);
                    if (itemToBorrow == null) itemToBorrow = journalShelf.findItem(borrowId);

                    if (itemToBorrow != null) {
                        currentMember.borrowItem(itemToBorrow);
                    } else {
                        System.out.println("Error: ID '" + borrowId + "' tidak ditemukan di katalog.");
                    }
                    break;

                case 3:
                    currentMember.showBorrowedItems();
                    System.out.print("Masukkan ID Barang yang ingin dikembalikan: ");
                    String returnId = sc.nextLine();
                    currentMember.returnItem(returnId);
                    break;

                case 4:
                    currentMember.showBorrowedItems();
                    break;

                case 5:
                    System.out.print("ID Barang yang terlambat: ");
                    String fineId = sc.nextLine();
                    LibraryResource fineItem = currentMember.getBorrowedItem(fineId);
                    
                    if (fineItem != null) {
                        System.out.print("Hari terlambat: ");
                        int days = sc.nextInt();
                        double fine = fineItem.calculateFine(days);
                        System.out.println("=== Rincian Denda ===");
                        System.out.println("Item: " + fineItem.getTitle());
                        System.out.println("Tipe: " + fineItem.getClass().getSimpleName());
                        System.out.printf("Total: Rp %.2f\n", fine);
                    } else {
                        System.out.println("Barang tidak ada di list pinjaman Anda.");
                    }
                    break;
                
                case 6:
                    System.out.println("\n=== PILIH MEMBER ===");
                    for(int i=0; i<memberList.size(); i++) {
                        System.out.println((i+1) + ". " + memberList.get(i).getName());
                    }
                    System.out.println((memberList.size()+1) + ". + Daftar Member Baru");
                    System.out.print("Pilih: ");
                    int mChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if(mChoice > 0 && mChoice <= memberList.size()) {
                        currentMember = memberList.get(mChoice - 1);
                        System.out.println("Login sebagai: " + currentMember.getName());
                    } else if (mChoice == memberList.size() + 1) {
                        System.out.print("Masukkan Nama Member Baru: ");
                        String newName = sc.nextLine();
                        Member newMember = new Member(newName);
                        memberList.add(newMember);
                        currentMember = newMember;
                        System.out.println("Member baru dibuat & login.");
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
        System.out.println("+------+------------------------------+----------+-------+");
        System.out.println("| ID   | Judul                        | Lokasi   | Stok  |");
        System.out.println("+------+------------------------------+----------+-------+");
    }

    private static void printTableFooter() {
        System.out.println("+------+------------------------------+----------+-------+");
    }

    private static void printShelfItems(Shelf<? extends LibraryResource> shelf) {
        for (LibraryResource item : shelf.getItems()) {
            System.out.printf("| %-4s | %-28s | %-8s | %-5d |\n",
                    item.getResourceID(), 
                    truncate(item.getTitle(), 28), 
                    item.getLocation(),
                    item.getStock());
        }
    }
    
    private static String truncate(String str, int width) {
        if (str.length() > width) {
            return str.substring(0, width - 3) + "...";
        }
        return str;
    }
}