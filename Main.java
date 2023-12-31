import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<MenuItem> menu = new ArrayList<>();
        ArrayList<Receipt> orderHistory = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
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
                    case 1: // Add to Order & Checkout - by sandeep
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
                    case 2: // Order History - by samrat
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
                                System.out.println();
                                System.out.println("Total: $" + receipt1.getTotal());
                                System.out.println("Customer Amount: " + receipt1.getCustomerAmount());
                                System.out.println("Change Amount: " + receipt1.getChange()); 
                                System.out.println("************************");
                                System.out.println();
                            }
                        }
                        
                        break;

                    case 3: // clock in - by sandeep
                        System.out.println("clock in");
                        int clockinId;
                        boolean isvalidId = false;
                        do {
                            try {
                                System.out.println("Enter employee Id:");
                                clockinId = scanner.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please Enter 4 Digit Integer Code");
                                scanner.nextLine();
                            }
                        } while (true);
                        for (Employee employee : employees) {
                            if (employee.getEmployeeId() == clockinId) {
                                isvalidId = true;
                            }
                        }
                        if (isvalidId) {
                            if (employeeCodes.contains(clockinId)) {
                                System.out.println("Employee already clocked in..");
                            } else {
                                employeeCodes.add(clockinId);
                                clockInTimes.add(new Date());
                                System.out.println("Clocked in at " + dateFormat.format(new Date()));
                            }
                        } else {
                            System.out.println("No employee with code " + clockinId);
                        }
                        break;
                        
                    case 4: // clock out - by sandeep
                        System.out.println("clock out");
                        int clockoutId;
                        do {
                            try {
                                System.out.println("Enter employee Id:");
                                clockoutId = scanner.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please Enter 4 Digit Integer Code");
                                scanner.nextLine();
                            }
                        } while (true);
                        int index = employeeCodes.indexOf(clockoutId);
                        if (index >= 0) {
                            Date clockOutTime = new Date();
                            System.out.println("Clocked out at " + dateFormat.format(clockOutTime));
                            employeeCodes.remove(index);
                            Date clockInTime = clockInTimes.get(index);
                            double workTimeMillis = clockOutTime.getTime() - clockInTime.getTime();
                            double hours = workTimeMillis / 3600000;
                            System.out.println("Worked for " + hours + " hours");
                            for (Employee employee : employees) {
                                if (employee.getEmployeeId() == clockoutId) {
                                    if (employee.getClass().getSimpleName().equals("Manager")) {
                                        Manager m = (Manager) employee;
                                        m.setDaysWorked();
                                    } else {
                                        CrewMember c = (CrewMember) employee;
                                        c.setHoursWorked(hours);
                                    }
                                    break;
                                }
                            }
                        } else {
                            System.out.println("You are not clocked in..");
                        }
                        break;
                        
                    case 5: // Employee Management - by rithvik
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

                    case 6: // Menu Management - by pavan
                    //my commit check
                        int menuOption;
                        do{
                            System.out.println("Menu Management:");
                            System.out.println("1. View Menu");
                            System.out.println("2. Add Item");
                            System.out.println("3. Remove Item");
                            System.out.println("4. Update Price");
                            System.out.println("5. Back to Main Menu");
                            do {
                                try {
                                    System.out.print("Enter your choice: ");
                                    menuOption = scanner.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please Enter An Integer");
                                    scanner.nextLine();
                                }
                            } while (true);
                            switch (menuOption) 
                            {
                                case 1:
                                    System.out.println("**** Menu *****");
                                    for (int i = 0; i < menu.size(); i++) {
                                        MenuItem m = menu.get(i);
                                        System.out.println(i + 1 + "-" + m.getItemName() + " - " + m.getPrice());
                                    }
                                    break;
                                case 2:
                                    String item;
                                    double price;
                                    System.out.println("Enter Item Name:");
                                    item = scanner.next();
                                    System.out.println("Enter Item Price: ");
                                    price = scanner.nextDouble();
                                    MenuItem m1 = new MenuItem(item, price);
                                    menu.add(m1);
                                    System.out.println("New Item Added Successfully.");
                                    break;
                                case 3:
                                    int inputVariable;
                                    System.out.println("**** Menu *****");
                                    for (int i = 0; i < menu.size(); i++) {
                                        MenuItem m = menu.get(i);
                                        System.out.println(i + 1 + "-" + m.getItemName() + " - " + m.getPrice());
                                    }
                                    System.out.print("Enter Item Number You Want To Remove: ");
                                    inputVariable = scanner.nextInt();
                                    menu.remove(inputVariable - 1);
                                    System.out.println("Item Removed Successfully");
                                    break;
                                case 4:
                                    int in1;
                                    System.out.println("**** Menu *****");
                                    for (int i = 0; i < menu.size(); i++) {
                                        MenuItem m = menu.get(i);
                                        System.out.println(i + 1 + "-" + m.getItemName() + " - " + m.getPrice());
                                    }
                                    System.out.print("Enter Item Number You Want To Update Price: ");
                                    in1 = scanner.nextInt();
                                    MenuItem mi = menu.get(in1 - 1);
                                    double newPrice;
                                    System.out.println("Enter The New Price:");
                                    newPrice = scanner.nextDouble();
                                    mi.setPrice(newPrice);
                                    menu.set(in1 - 1, mi);
                                    System.out.println("Price Updated Successfully.");
                                    break;
                                default:
                                    System.out.println("Please Enter Valid Option!!");
                                    break;
                            }

                        }while (menuOption != 5);
                        
                        break;
                    case 7: // sale report - by samrat
                        if (orderHistory.isEmpty()) {
                        System.out.println("No sales have been made yet. ");
                        } else {
                            System.out.println("Sales Report:");
                            System.out.println(" ---------------------------- ");

                            // Total sales and number of orders
                            double totalSales = 0;
                            int totalOrders = orderHistory.size();

                            for (Receipt receipt : orderHistory) {
                                totalSales += receipt.getTotal();
                            }

                            System.out.println("Total number of orders: " + totalOrders);
                            System.out.println("Total Sales: " + totalSales);
                            System.out.println("Average sale per order: $" + (totalSales/totalOrders));

                            // Item-wise Sales

                            System.out.println("Item-wise Sales");
                            System.out.println("----------------------");

                            HashMap<String, Integer> itemSales = new HashMap<>();

                            for(Receipt receipt : orderHistory) {
                                for(OrderItem item : receipt.getOrderItems()) {
                                    String itemName = item.getMenuItem().getItemName();
                                    int quantity = item.getQuantity();

                                    // Update item-wise sales
                                    if (itemSales.containsKey(itemName)) {
                                        itemSales.put(itemName, itemSales.get(itemName) + quantity);
                                    } else {
                                        itemSales.put(itemName, quantity);
                                    }
                                }
                            }

                            // Display item-wise sales
                              for(Map.Entry<String, Integer> entry : itemSales.entrySet()) {
                                System.out.println(entry.getKey() + ": " + entry.getValue() + " items");
                            }

                              // Finding most selling and least selling item

                              int maxQuantity = Integer.MIN_VALUE;
                              int minQuantity = Integer.MAX_VALUE;
                              String mostSoldItem = "";
                              String leastSoldItem = "";
  
                              for (Map.Entry<String, Integer> entry : itemSales.entrySet()) {
                                  if (entry.getValue() > maxQuantity) {
                                      maxQuantity = entry.getValue();
                                      mostSoldItem = entry.getKey();
                                  }
                                  if (entry.getValue() < minQuantity) {
                                      minQuantity = entry.getValue();
                                      leastSoldItem = entry.getKey();
  
                                  }
  
                              }
  
                              System.out.println("Most selling item: " + mostSoldItem + " (" + maxQuantity + " items)");
                              System.out.println("Least selling item: " + leastSoldItem + " (" + minQuantity + " item)" );

                        }

                        
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
