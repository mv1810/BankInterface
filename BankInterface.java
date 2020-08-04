import java.util.Scanner; 
import java.util.Random;
class Customer{
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static String Name = "";
    static int Accno = rand.nextInt(10000);
    static String IFSCcode = "ABC2304095";
    static String branch = "Vijayawada";
    static String place = "";
    static String phonenumber = "";
    static String email = "";
    static double BankBalance = 0;
    static int pin = 0;
    static boolean IsPinGenerated = false;
    static boolean IsAccountIsProcessed = false;
    static double deposit(double amount){
        BankBalance = BankBalance + amount;
        return BankBalance;
    }
    static double withdraw(double amount){
        BankBalance = BankBalance - amount;
        return BankBalance;
    }
    static double balanceenquiry(){
        return BankBalance;
    }
    static void generatepin(){
        if(IsPinGenerated){
            System.out.println("Pin is already generated");
        }
        else{
            pin = sc.nextInt();
            if(!((pin>1000)&&(pin<9999))){
                System.out.println("Please type a four digit pin!!!");
                generatepin();
                IsPinGenerated = true;
            }
        }
    }
    static void changepin(){
        int newpin = 0;
        int confirmpin = 0;
        if(!IsPinGenerated){
            System.out.println("Please generate the pin");
            generatepin();
        }
        else{
            System.out.println("Please enter your new pin");
            newpin = sc.nextInt();
            System.out.println("Please confirm the pin");
            confirmpin = sc.nextInt();
            if(!((newpin>1000)&&(newpin<9999)&&(confirmpin>1000)&&(confirmpin<9999))){
                System.out.println("Please type a four digit pin!!!");
                changepin();
            }
            else{
                if(newpin!=confirmpin){
                    System.out.println("The pins you have entered didn't match please check and retype them.");
                    changepin();
                }
                else{
                    pin = newpin;
                }
            }
        }
    }
    static void display(){
        int option = 0;
        double amount = 0;
        System.out.println("###################################################################################");
        System.out.println("Choose one option below : ");
        if(IsPinGenerated){
            System.out.println("1. Change pin");
            System.out.println("2. Balance Enquiry");
            System.out.println("3. Cash withdrawl");
            System.out.println("4. Cash Deposit");
            System.out.println("5. Display account details");
            System.out.println("6. Exit");
        }
        else{
            System.out.println("1. Generate pin");
            System.out.println("2. Exit");
        }
        
        System.out.println("###################################################################################");
        option = sc.nextInt();
        if(IsPinGenerated){
            switch(option){
            case 1:
                if(IsPinGenerated){
                    changepin();
                }
                else{
                    generatepin();
                }
                display();
                break;
            case 2 :
                System.out.println(balanceenquiry());
                display();
                break;
            case 3:
                System.out.println("Enter the amount to be withdrawn");
                amount = sc.nextInt();
                System.out.print("Amount withdrawn successfully. Your bank balance is : Rs.");
                System.out.println(withdraw(amount));
                display();
                break;
            case 4:
                System.out.println("Enter the amount to be deposited");
                amount = sc.nextInt();
                System.out.print("Amount deposited successfully. Your bank balance is : Rs.");
                System.out.println(deposit(amount));
                display();
                break;
            case 5:
                System.out.println("###################################################################################");
                System.out.println("Here are your account details : ");
                System.out.println("Name           : "+Name);
                System.out.println("IFSC code      : "+IFSCcode);
                System.out.println("Account number : "+Accno);
                System.out.println("Branch         : "+branch);
                System.out.println("Phone Number   : "+phonenumber);
                System.out.println("Email ID       : "+email);
                System.out.println("###################################################################################");
                display();
                break;
            case 6:
                break;
        }
        }
        else{
            switch(option){
            case 1:
                if(IsPinGenerated){
                    changepin();
                }
                else{
                    generatepin();
                }
                display();
                break;
            case 2 :
                break;
            
        }
        }
        
    }
}
public class BankInterface {
   static Scanner sc = new Scanner(System.in);
   public static void main(String[] args){
       Customer c1 = new Customer();
       if(!c1.IsAccountIsProcessed){
           System.out.println("Please process your account first");
           System.out.println("Please Enter your name : ");
           c1.Name = sc.next();
           System.out.println("Please Enter your place of birth : ");
           c1.place = sc.next();
           System.out.println("Please Enter your phone number : ");
           c1.phonenumber = sc.next();
           System.out.println("Please Enter your Email : ");
           c1.email = sc.next();
           c1.IsAccountIsProcessed = true;
           c1.display();
       }
       else{
           c1.display();
       }
   }
}