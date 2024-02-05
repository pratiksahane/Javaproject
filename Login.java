import java.util.Scanner;
class Usercred{
    private String[] usernames = {"employee1", "employee2"};
    private int[] password={1234,5678};
    String name;
    int password1;
    public boolean validation(int code){
        int temp=password[code];
        if (usernames[code].equals(name) && temp==(password1)){

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
            }
            else{
                System.out.println("Invalid credentials");
            }





        }
    else{
        System.out.println("Invalid Choice");
        
    }

       




    }
}
