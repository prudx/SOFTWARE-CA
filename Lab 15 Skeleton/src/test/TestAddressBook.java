package test;

import dbService.ContactOperations;
import dbService.JPAService;
import java.util.Scanner;

public class TestAddressBook {

    static JPAService jpas = new JPAService();

    public static void main(String[] args) {
//        ContactOperations co = new ContactOperations();
//        co.openDB();
//        co.dropContactSequence();
//        co.dropAddressBookOwnerSequence();
//
//        co.dropContactsAddressBookTable();
//        co.dropAddressBookOwnersTable();
//        co.dropContactsTable();
//
//        co.createContactSequence();
//        co.createAddressBookOwnersSequence();
//
//        co.CreateContactsTable();
//        co.createAddressBookOwnerstable();
//        co.createContactsAddressBook();
//
        displayMenu1();
    }

    public static void displayMenu1() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("**************************************************");
            System.out.println("Please press 1 to enter the name of the address book owner");
            System.out.println("Please press 2 to quit");
            System.out.println("**************************************************");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("**************************************************");
                    System.out.println("Please enter the name of the address book owner whose contacts you wish to view");
                    String owner = in.nextLine();
                    if (!jpas.findOwner(owner)) {
                        System.out.println("Address Book owner not found");
                    } else {
                        displayMenu2(owner);
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
    public static void displayMenu2(String owner) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please press 1 to view the tickets you've bought");
            System.out.println("Please press 2 if you want to update a phone number");
            System.out.println("Please press 3 to delete a contact");
            System.out.println("Please press 4 to add a new contact");
            System.out.println("Please press 5 to go back to main menu");
            System.out.println("Press 6 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    jpas.printAllTickets(owner);
                    break;
//                case 2:
//                    System.out.println("please enter the id of the contact you wish to update");
//                    int cid = in.nextInt();
//                    in.nextLine();
//                    if (!jpas.findContact(cid, owner)) {
//                        System.out.println("Entity not found");
//                    } else {
//                        System.out.println("Please enter the new phone number for " + cid);
//                        String newNum = in.nextLine();
//                        jpas.updateContact(cid, newNum);
//                    }
//                    break;
//                case 3:
//                    System.out.println("please enter the id of the contact you wish to delete");
//                    int idDelete = in.nextInt();
//                    in.nextLine();
//                    if (!jpas.findContact(idDelete, owner)) {
//                        System.out.println("Entity not found");
//                    } else {
//                        jpas.removeContact(idDelete);
//                        System.out.println("Entity removed");
//                    }
//                    break;
//                case 4:
//                    System.out.println("please enter the name of the contact you wish to add");
//                    String nameAdd = in.nextLine();
//                    System.out.println("please enter the address of the contact you wish to add");
//                    String addAdd = in.nextLine();
//                    System.out.println("please enter the email of the contact you wish to add");
//                    String emailAdd = in.nextLine();
//                    System.out.println("please enter the number of the contact you wish to add");
//                    String numAdd = in.nextLine();
////                    jpas.createContact(nameAdd, addAdd, emailAdd, numAdd, owner);
//                    break;
                case 5:
                    displayMenu1();
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}
