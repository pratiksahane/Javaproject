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
public class Login {
    
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        Usercred myobj=new Usercred();
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
}
