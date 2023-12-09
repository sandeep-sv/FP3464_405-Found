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
                        ArrayList<OrderItem> presentOrder = new ArrayList<>();
                        while (true) {
                            System.out.println("Menu:");
                            for (int i = 0; i < menu.size(); i++) {
                                MenuItem item = menu.get(i);
                                System.out.println(i + 1 + ". " + item.getItemName() + " - $" + item.getPrice());
                            }
                            int itemChoice;
                            do {
                                try {
                                    System.out.print("Enter the item number to add to the order (0 to finish, -1 to cancel, -2 to remove): ");
                                    itemChoice = scanner.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter an integer.");
                                    scanner.nextLine();
                                }
                            } while (true);

                            if (itemChoice == 0) {
                                // Finish and checkout
                                if (!presentOrder.isEmpty()) {
                                    double total = 0.0;

                                    for (OrderItem orderItem : presentOrder) {
                                        total += orderItem.getItemTotal();
                                    }

                                    System.out.println(" Your order");
                                    System.out.println("**************");
                                    for (OrderItem item : presentOrder) {
                                        System.out.println(item.getQuantity() + " " + item.getMenuItem().getItemName() + " - $" + item.getItemTotal());
                                    }
                                    System.out.println("**************");
                                    System.out.println("Total: $" + total);
                                    System.out.print("Customer amount: $");
                                    double customerAmount = scanner.nextDouble();
                                    double change = customerAmount - total;
                                    System.out.println("Change: $" + change);
                                    Receipt receipt = new Receipt(new ArrayList<>(presentOrder), total, customerAmount, change);
                                    orderHistory.add(receipt);
                                    System.out.println("Thank you for your order!");
                                    presentOrder.clear();
                                } else {
                                    System.out.println("Your order is empty.");
                                }
                                break;
                            } else if (itemChoice == -1) {
                                presentOrder.clear();
                                System.out.println("Current order canceled.");
                                break;
                            } else if (itemChoice == -2) {
                                if (!presentOrder.isEmpty()) {
                                    System.out.println("Current Order:");
                                    for (int i = 0; i < presentOrder.size(); i++) {
                                        OrderItem orderItem = presentOrder.get(i);
                                        System.out.println((i + 1) + ". " + orderItem.getQuantity() + " " + orderItem.getMenuItem().getItemName());
                                    }
                                    System.out.print("Enter the item number to remove (0 to cancel): ");
                                    int removeChoice = scanner.nextInt();
                                    if (removeChoice == 0) {
                                        System.out.println("Removal canceled.");
                                    } else if (removeChoice > 0 && removeChoice <= presentOrder.size()) {
                                        presentOrder.remove(removeChoice - 1);
                                        System.out.println("Item removed from the order.");
                                    } else {
                                        System.out.println("Invalid item number.");
                                    }
                                } else {
                                    System.out.println("Your order is empty.");
                                }
                            } else if (itemChoice > 0 && itemChoice <= menu.size()) {
                                MenuItem selectedMenuItem = menu.get(itemChoice - 1);
                                System.out.print("Enter the quantity: ");
                                int quantity = scanner.nextInt();
                                OrderItem orderItem = new OrderItem(selectedMenuItem, quantity);
                                presentOrder.add(orderItem);
                                System.out.println(orderItem.getQuantity() + " " + orderItem.getMenuItem().getItemName() + " added to the order.");
                            } else {
                                System.out.println("Invalid item number.");
                            }
                        }
                        break;
                    case 2: // Order History
                        if (orderHistory.isEmpty()) {
                            System.out.println("Order history is empty.");
                        } else {
                            System.out.println("order history:" + orderHistory.size());
                            for (int i = 0; i < orderHistory.size(); i++) {
                                Receipt receipt1 = orderHistory.get(i);
                                System.out.println("Receipt " + (i + 1) + ":");
                                System.out.println("************************");
                                for (OrderItem item : receipt1.getOrderItems()) {
                                    System.out.println(item.getQuantity() + " - " + item.getMenuItem().getItemName() + " - $" + item.getItemTotal());
                                }
                                System.out.println(receipt1.getTotal());
                                System.out.println(receipt1.getCustomerAmount());
                                System.out.println(receipt1.getChange());
                                
                            }
                        }
                        
                        break;

                    case 3: // clock in
                        
                        break;
                    case 4: // clock out
                        
                        break;
                    case 5: // Employee Management
                    int choice;
                    do {
                        System.out.println("Employee Management Menu:");
                        System.out.println("1. Add Manager");
                        System.out.println("2. Add Crew Member");
                        System.out.println("3. View Employees");
                        System.out.println("4. View Salary by Employee ID");
                        System.out.println("5. Back to Main Menu");
                        do {
                            try {
                                System.out.print("Enter your choice: ");
                                choice = scanner.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please Enter An Integer");
                                scanner.nextLine();
                            }
                        } while (true);
                        switch (choice) {
                            case 1:
                                System.out.print("Enter Manager's name: ");
                                String name = scanner.next();
                                System.out.print("Enter Manager's employee ID: ");
                                int employeeId = scanner.nextInt();
                                System.out.print("Enter Manager's fixed salary: ");
                                double fixedSalary = scanner.nextDouble();
                                Manager manager = new Manager(name, employeeId, fixedSalary);
                                employees.add(manager);
                                System.out.println("Manager added successfully.");
                                break;
                            case 2:
                                System.out.print("Enter Crew Member's name: ");
                                String name1 = scanner.next();
                                System.out.print("Enter Crew Member's employee ID: ");
                                int employeeId1 = scanner.nextInt();
                                System.out.print("Enter Crew Member's hourly pay: ");
                                double hourlyPay = scanner.nextDouble();
                                CrewMember crewMember = new CrewMember(name1, employeeId1, hourlyPay);
                                employees.add(crewMember);
                                System.out.println("Crew Member added successfully.");
                                break;
                            case 3:
                                System.out.println("List of Employees:");
                                    for (Employee employee : employees) {
                                        System.out.println("Name: " + employee.getName());
                                        System.out.println("Employee ID: " + employee.getEmployeeId());
                                        System.out.println("Employee ID: " + employee.getEmployeeId());
                                        System.out.println("Role: " + employee.getClass().getSimpleName());
                                    }
                                break;
                            case 4:
                                System.out.print("Enter Employee ID: ");
                                int employeeId2 = scanner.nextInt();

                                for (Employee employee : employees) {
                                    if (employee.getEmployeeId() == employeeId2) {
                                        System.out.println("Salary for Employee ID " + employeeId2 + ": " + employee.calculateSalary());
                                    }
                                }
                                break;
                            case 5:
                                System.out.println("Returning to the main menu.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (choice != 5);
                    break;

                    case 6: // Menu Management
                        
                        break;
                    case 7: // Sale Report
                    // Samrat
                        
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
