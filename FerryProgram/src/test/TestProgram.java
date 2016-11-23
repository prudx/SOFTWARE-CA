package test;

import dbService.JPAService;
import java.util.*;

public class TestProgram {

    static JPAService jpas = new JPAService();

    public static void main(String[] args) {

        displayMenu1();
    }

    public static void displayMenu1() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Please press 1 to sign in");
            System.out.println("Please press 2 to create an account");
            System.out.println("Please press 3 to quit");
            System.out.println("-----------------------------------------------------------");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Please enter your username");
                    String usernameEntered = in.nextLine();
                    if (!jpas.findUser(usernameEntered)) {
                        System.out.println("Username not found");
                    } else {
                        displayMenu2(usernameEntered);
                    }
                    break;
                case 2:
                    System.out.println("please enter a new username:");
                    String usernameAdd = in.nextLine();
                    System.out.println("please enter a password:");
                    String passwordAdd = in.nextLine();
                    System.out.println("please enter in your name");
                    String nameAdd = in.nextLine();
                    System.out.println("please enter your date of birth");
                    String dobAdd = in.nextLine();
                    jpas.createUser(usernameAdd, passwordAdd, nameAdd, dobAdd);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Option entered\n");
                    break;
            }
        }
    }
    public static void displayMenu2(String usernameEntered) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please press 1 to buy a new ticket");
            System.out.println("Please press 2 to view the tickets you've bought");
            System.out.println("Please press 3 to if you want change ticket type");
            System.out.println("Please press 4 to cancel a ticket");
            System.out.println("Please press 5 to go back to main menu");
            System.out.println("Press 6 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Please enter the departure date:");
                    String depDate = in.nextLine();
                    System.out.println("Please enter the return date:");
                    String returnDate = in.nextLine();
                    System.out.println("please enter the route you wish to travel");
                    String routeIn = in.nextLine();
                    System.out.println("please enter the number of the contact you wish to add");
                    String numAdd = in.nextLine();
                    break;
                case 2:
                    System.out.println("Here are your tickets:");
                    jpas.printAllTickets(usernameEntered);
                    System.out.println("");
                    break;
                     
                case 3:
                    System.out.println("Please enter the id of the ticket you wish to delete");
                    int idDelete = in.nextInt();
                    in.nextLine();
                    if (!jpas.findTicket(idDelete, usernameEntered)) {
                        System.out.println("Entity not found");
                    } else {
                        jpas.deleteTicket(idDelete);
                        System.out.println("Entity removed");
                    }
                    break;
                case 4:
                    System.out.println("please enter the name of the contact you wish to add");
                    String nameAdd = in.nextLine();
                    System.out.println("please enter the address of the contact you wish to add");
                    String addAdd = in.nextLine();
                    System.out.println("please enter the email of the contact you wish to add");
                    String emailAdd = in.nextLine();
                    System.out.println("please enter the number of the contact you wish to add");
//                    String numAdd = in.nextLine();
//                    jpas.createContact(nameAdd, addAdd, emailAdd, numAdd, owner);
                    break;
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
