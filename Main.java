import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<MenuItem> menu = new ArrayList<>();
        ArrayList<Receipt> orderHistory = new ArrayList<>();
        //ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Integer> employeeCodes = new ArrayList<>();
        ArrayList<Date> clockInTimes = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        //adding initial items to the menu
        MenuItem item1=new MenuItem("Burrito",10);
        MenuItem item2=new MenuItem("Rice Bowl",12);
        MenuItem item3=new MenuItem("Tacos",8);
        MenuItem item4=new MenuItem("Nachos",12);
        MenuItem item5=new MenuItem("Quesadilla",10);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.add(item5);
        int option;
        do{
                System.out.println("****POS SYSTEM****");
                System.out.println("1. New Order");
                System.out.println("2. View Order History");
                System.out.println("3. Clock In");
                System.out.println("4. Clock Out");
                System.out.println("5. Employee Management");
                System.out.println("6. Menu management");
                System.out.println("7. Sale Report");
                System.out.println("8. Exit");
                do {
                    try {
                        System.out.print("Enter the Option:");
                        option = scanner.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scanner.nextLine();
                    }
                } while (true);

            switch (option) {
                    case 1: // Add to Order & Checkout
    
                        break;
                    case 2: // Order History
                        
                        break;

                    case 3: // clock in
                        
                        break;
                    case 4: // clock out
                        
                        break;
                    case 5: // Employee Management
                        
                        break;

                    case 6: // Menu Management
                        
                        break;
                    case 7: // Sale Report
                        
                        break;

                    case 8:
                        System.out.println("Thanks for using the app.");
                        break;
                    default:
                        System.out.println("Please enter a valid option!!!");

                }
                
        }while (option!=8);

    }
}
