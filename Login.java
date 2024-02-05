import java.util.Scanner;

interface Authenticatable {
    boolean validateCredentials(String username, int password);
}

class Employee implements Authenticatable {
    private String[] usernames = {"employee1", "employee2"};
    private int[] passwords = {1234, 5678};

    @Override
    public boolean validateCredentials(String username, int password) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username) && passwords[i] == password) {
                return true;
            }
        }
        return false;
    }
}

class Manager implements Authenticatable {
    private int managerPassword = 1234;
    private String managerUsername = "employee3";

    @Override
    public boolean validateCredentials(String username, int password) {
        return username.equals(managerUsername) && password == managerPassword;
    }
}

public class Login {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 'employee' if you are an employee and 'manager' if you are a manager:");
        String userType = sc.nextLine();

        Authenticatable authenticator;

        if (userType.equals("employee")) {
            authenticator = new Employee();
        } else if (userType.equals("manager")) {
            authenticator = new Manager();
        } else {
            System.out.println("Invalid user type.");
            return;
        }

        System.out.println("Enter the username:");
        String username = sc.nextLine();
        System.out.println("Enter the password:");
        int password = sc.nextInt();

        if (authenticator.validateCredentials(username, password)) {
            System.out.println("Successfully logged in.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
