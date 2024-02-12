import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*; 


class Usercred {
    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<Integer> passwords = new ArrayList<>();

    private void initializeDefaultEmployees() {
        // Add default employees if the list is empty
        if (usernames.isEmpty()) {
            usernames.add("employee1");
            usernames.add("employee2");
            passwords.add(1234);
            passwords.add(5678);
        }
    }

    public Usercred() {
        initializeDefaultEmployees();
    }

    public Usercred(String n, int n1) {
        initializeDefaultEmployees();
        usernames.add(n); // Add the username directly, not the index
        passwords.add(n1); // Add the password directly, not the index
        System.out.println("Employees are:");
        for (String item : usernames) {
            System.out.println(item);
        }
    }

    public Usercred(String n, int n1, int e) {
        initializeDefaultEmployees();
        int indexToRemove = usernames.indexOf(n);
        if (indexToRemove != -1 && passwords.get(indexToRemove) == n1) {
            usernames.remove(indexToRemove); // Remove the username at the found index
            passwords.remove(indexToRemove); // Remove the corresponding password
            System.out.println("Employee removed successfully");
        } else {
            System.out.println("Employee removed");
        }

        // Print the updated list of employees after the removal attempt
        System.out.println("Employees are:");
        for (String item : usernames) {
            System.out.println(item);
        }
    }

    String name;
    int password1;

    public boolean validation(int code) {
        int temp = passwords.get(code);
        return usernames.get(code).equals(name) && temp == (password1);
    }
}

class Manager {
    public int managpass = 1234;
    public String user = "employee3";

    public boolean validate(String a, int b) {
        return a.equals(user) && b == managpass;
    }
}

public class Login {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Java Restaurant management System");
        System.out.println("Enter employee if you are and enter manager if you are:");
        String s = sc.nextLine();
        Usercred myobj = new Usercred();
        Menupage myobj7 = new Menupage();

        if (s.equals("employee")) {

            System.out.println("Enter the employee code:(0,1)");
            int code = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.println("Enter the user name:");
            myobj.name = sc.nextLine();
            System.out.println("Enter the password:");
            myobj.password1 = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (myobj.validation(code)) {
                System.out.println("Success full logged in");
                myobj7.printMenu();

            } else {
                System.out.println("Invalid credentials");
            }
        } else if (s.equals("manager")) {
            Manager myobj1 = new Manager();
            System.out.println("Enter username for manager:");
            String a = sc.nextLine();
            System.out.println("Enter password for manager:");
            int b = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (myobj1.validate(a, b)) {
                System.out.println("Success full logged in");
                int quitChoice;
                do {
                    System.out.println("1: Add employee");
                    System.out.println("2: Remove employee");
                    System.out.println("3: Switch to employee side");
                    System.out.println("4: Check Totalsales:");
                    System.out.println("What task do you want to perform?");

                    int task = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    switch (task) {
                        case 1:
                            System.out.println("Enter new employee username:");
                            String namee = sc.nextLine();

                            System.out.println("Enter new employee login password:");
                            int id1 = sc.nextInt();
                            sc.nextLine(); // Consume newline
                            Usercred myobj5 = new Usercred(namee, id1);
                            break;

                        case 2:
                            System.out.println("Enter employee username to remove:");
                            String nameee = sc.nextLine();

                            System.out.println("Enter employee password to remove:");
                            int id2 = sc.nextInt();
                            sc.nextLine(); // Consume newline
                            Usercred myobj6 = new Usercred(nameee, id2, 0);
                            break;

                        case 3:
                            myobj7.printMenu();
                            break;

                        case 4:
                        File file = new File("total_sales.txt");
                        try (Reader reader = new FileReader(file);
                             BufferedReader bufferedReader = new BufferedReader(reader)) {
                            String line = bufferedReader.readLine();
                            if (line != null) {
                                System.out.println("The total sales are: " + line);
                            } else {
                                System.out.println("No total sales found in file.");
                            }
                        } catch (FileNotFoundException e) {
                            System.err.println("Error: File not found - " + e.getMessage());
                        } catch (IOException e) {
                            System.err.println("Error reading from file - " + e.getMessage());
                        }
                        break;
                    
                        
                    }

                    do {
                        System.out.println("Do you want to quit? (1(no)/0(yes)):");
                       try {
                            quitChoice = sc.nextInt();
                            sc.nextLine(); // Consume newline
                           if (quitChoice != 0 && quitChoice != 1) {
                              System.out.println("Invalid input. Please enter 0 (yes) or 1 (no).");
                           }
                        } catch (NoSuchElementException e) {
                           System.err.println("Error reading input: " + e.getMessage());
                            quitChoice = 0; // Quit to avoid infinite loop
                        }
                    } while (quitChoice != 0 && quitChoice != 1);

                } while (quitChoice == 1);

                System.out.println("Exiting the program. Thank you!");
            } else {
                System.out.println("Invalid credentials");
            }

        } else {
           System.out.println("Invalid Choice");
        }
        sc.close();
    }
}

