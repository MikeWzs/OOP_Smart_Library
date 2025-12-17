package MainPackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Shelf<Book> bookShelf = new Shelf<>();
        bookShelf.addItem(new Book("B01", "Java Programming", "Rack A"));
        bookShelf.addItem(new Book("B02", "OOP Design", "Rack B"));

        Shelf<Multimedia> mediaShelf = new Shelf<>();
        mediaShelf.addItem(new Multimedia("M01", "Learning Java DVD", "Rack C"));

        Member member = new Member("BINUS Student");

        int choice;
        do {
            System.out.println("\n===== SMART LIBRARY MENU =====");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. View Borrowed Items");
            System.out.println("4. Calculate Fine");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Book b : bookShelf.getItems()) {
                        System.out.println(b.displayInfo());
                    }
                    break;

                case 2:
                    member.borrowItem(bookShelf.getItems().get(0));
                    break;

                case 3:
                    member.showBorrowedItems();
                    break;

                case 4:
                    System.out.print("Days late: ");
                    int days = sc.nextInt();
                    System.out.println("Fine: Rp " +
                        bookShelf.getItems().get(0).calculateFine(days));
                    break;

                case 0:
                    System.out.println("Thank you for using SmartLibrary.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
        
        sc.close();
    }
}
