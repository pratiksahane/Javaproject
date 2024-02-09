import java.util.ArrayList;
import java.util.Scanner;
class Usercred{
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
    public boolean validation(int code){
        int temp=passwords.get(code);
        if (usernames.get(code).equals(name) && temp==(password1)){

            return true;
        }
        else{
            return false;
        }

    }
}
class Manager{
    public int managpass=1234;
    public String user="employee3";
    public boolean validate(String a,int b){
        if (a.equals(user) && b==managpass){
            return true;
        
        }else{
            return false;
        }

    }

}
public class Login {
    
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter employee if you are and enter manager if you are:");
        String s=sc.nextLine();
        Usercred myobj=new Usercred();
        
        if (s.equals("employee")){
    
        System.out.println("Enter the employee code:(0,1)");
        int code=sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter the user name:");
        myobj.name=sc.nextLine();
        System.out.println("Enter the password:");
        myobj.password1=sc.nextInt();
     if (myobj.validation(code)){
        System.out.println("Success full logged in");
        Menupage myobj3=new Menupage();
        myobj3.printMenu();

     }   
     else{
        System.out.println("Invalid credentials");
    }
        }
    

    else if (s.equals("manager")){
            Manager myobj1=new Manager();
            System.out.println("Enter username for manager:");
            String a=sc.nextLine();
            System.out.println("Enter password for manager:");
            int b =sc.nextInt();
            
            if (myobj1.validate(a,b)){
                System.out.println("Success full logged in");
                String c = "No"; // Ensure proper initialization
                Menupage myobj7 = new Menupage();
                while (c.equals("No")) { // Loop until the user wants to quit
                    System.out.println("1: Add employee");
                    System.out.println("2: Remove employee");
                    System.out.println("3: Switch to employee side");
                    System.out.println("4: Check Totalsales:");
                    System.out.println("What task do you want to perform?");
                
                    int task = sc.nextInt();
                    sc.nextLine();  
                
                    switch(task) {
                        case 1:
                            System.out.println("Enter new employee username:");
                            String namee = sc.nextLine();
                                        
                            System.out.println("Enter new employee login password:");
                            int id1 = sc.nextInt();
                            Usercred myobj5 = new Usercred(namee, id1);
                            break;
                            
                        case 2:
                            int i = 0;
                            System.out.println("Enter employee username to remove:");
                            String nameee = sc.nextLine();
                                        
                            System.out.println("Enter employee password to remove:");
                            int id2 = sc.nextInt();
                            sc.nextLine(); // Consume newline
                            Usercred myobj6 = new Usercred(nameee, id2, i);
                            break;
                            
                        case 3:
                            myobj7.printMenu();
                            break;

                        case 4:
                            myobj7.displayTotalsales();
                            break;
                    }
                
                    System.out.println("Do you want to quit? (Yes/No):");
                    c = sc.nextLine();
                    sc.nextLine();
                }
                

            }
            else{
                System.out.println("Invalid credentials");
            }

        }
    else{
        System.out.println("Invalid Choice");
        
    }
    sc.close();
    }
    
}
