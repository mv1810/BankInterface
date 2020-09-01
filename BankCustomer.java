package firstjavaproject;
import java.util.*;
class BankCustomer{
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    String Name ;
    Date DOB;
    int Accno ;
    Date dateofcreation = new Date();
    String IFSCcode;
    String branch;
    String place ;
    Long phonenumber ;
    String email;
    double BankBalance;
    int pin;
    boolean IsPinGenerated = false;
    boolean IsAccountIsProcessed = false;
    List<BankCustomer> customers = new ArrayList<BankCustomer>();
    int count = 0;
    boolean verification(){
        System.out.println("Please enter your 4 digit pin : ");
        int p = sc.nextInt();
        if(p == this.pin){
            return true;
        }
        else{
            System.out.println("You have entered a wrong pin.\n Try again!!");
            return verification();
        }
    }
    void dateofbirthcreation(){
        int year = sc.nextInt();
        int mon = sc.nextInt();
        int date = sc.nextInt();
        this.DOB = new Date(year, mon, date);
    }
    void deposit(double amount){
        this.BankBalance = this.BankBalance + amount;
        System.out.print("An amount of Rs. "+amount+" has been credited into the account of Mr."+this.Name+".\n Your acc no. is "+this.Accno+".\n And your remaining balance is : Rs. "+this.BankBalance);
        
    }
    void withdraw(double amount){
        if(verification()){
            this.BankBalance = this.BankBalance - amount;
            System.out.print("An amount of Rs. "+amount+" has been debited from the account of Mr."+this.Name+".\n Your acc no. is "+this.Accno+".\n And your remaining balance is : Rs. "+this.BankBalance);
            
        }
    }
    double balanceenquiry(){
        if(verification()){
            System.out.print("A request for balance enquiry has been initiated from your account number "+this.Accno+".\n Your account balance is : Rs. ");
            return this.BankBalance;
        }
        else{
            return -1;
        }
    }
    void generatepin(){
        if(this.IsPinGenerated){
            System.out.println("Pin is already generated");
        }
        else{
            System.out.println("Please enter your pin");
            this.pin = sc.nextInt();
            if(!((this.pin>1000)&&(this.pin<9999))){
                System.out.println("Please type a four digit pin!!!");
                generatepin();
            }
            else{
                this.IsPinGenerated = true;
                this.IsAccountIsProcessed = true;
            }
        }
    }
    void changepin(){
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
    void createAccount(){
        customers.add(new BankCustomer());
        BankCustomer c1 = customers.get(count);
        count++;
        c1.Accno = rand.nextInt(10000);
        System.out.println("Please Enter your Full name : ");
        c1.Name = sc.nextLine();
        System.out.println("Please enter your Date of birth in this format -> YYYY MM DD");
        dateofbirthcreation();
        c1.BankBalance = 0;
        c1.IFSCcode = "ABC234566";
        c1.IsPinGenerated = false;
        c1.IsAccountIsProcessed = false;
        c1.dateofcreation = new Date();
        System.out.println("Please enter the name of your branch : ");
        c1.branch = sc.nextLine();
        System.out.println("Please enter your emailID : ");
        c1.email = sc.nextLine();
        System.out.println("Please enter your phone number : ");
        c1.phonenumber = sc.nextLong();
        System.out.println("Please enter your place of birth : ");
        c1.place = sc.next();
        System.out.println("Starting your PIN generation process");
        c1.generatepin();
        System.out.println("Your Bank Account has been created successfully!!!");
        c1.DisplayAccountDetails();
    }
    void Transfer(double amount){
        for(int i=0;i<=count;i++){
            BankCustomer c1 = customers.get(i);
            System.out.print(i+1);
            System.out.println(". "+c1.Name+"-----"+c1.Accno);
        }
        System.out.println("Please choose an account to transfer the money from  : ");
        int opt1 = sc.nextInt();
        BankCustomer a1 = customers.get(opt1-1);
        for(int i=0;i<=count;i++){
            BankCustomer c1 = customers.get(i);
            System.out.print(i+1);
            System.out.println(". "+c1.Name+"-----"+c1.Accno);
        }
        System.out.println("Please choose an account to transfer the money : ");
        int opt2 = sc.nextInt();
        BankCustomer a2 = customers.get(opt2-1);
        a2.deposit(amount);
        a1.withdraw(amount);
    }
    void DisplayAccountDetails(){
        System.out.println("AccNO\tName\tD.O.B\t\tPhoneNumber\tEmailID\tIFSCcode\tBranch");
        System.out.println(this.Accno+"  "+this.Name+"  "+this.DOB+" "+this.phonenumber+"  "+this.email+"");
    }
    void DisplayAllAccounts(){
        System.out.println("AccNO\tName\tIFSCcode\tBranch\tPhoneNumber\tEmailID");
        for(int i = 0;i<count;i++){
            BankCustomer c1 = customers.get(i);
            System.out.println(c1.Accno+"  "+c1.Name+"  "+c1.DOB+" "+c1.phonenumber+"  "+c1.email+"");
        }
    }
    void home(){
        System.out.println("#################################");
        System.out.println("#\t1. Create Account \t#");
        if(count != 0){
            System.out.println("#\t2. Balance Enquiry \t#");
            System.out.println("#\t3. Withdrawl \t\t#");
            System.out.println("#\t4. Deposit \t\t#");
            System.out.println("#\t5. Online Transfer \t#");
            System.out.println("#\t6. Display Accounts\t#");
            System.out.println("#\t7. Change Pin \t\t#");
            System.out.println("#\t8. Exit \t\t#");
        }
        else{
            System.out.println("#\t8. Exit \t\t#");
        }
        System.out.println("#################################");
        System.out.println("Please choose an option : ");
        int opt = sc.nextInt();
        if(count !=0){
        if(opt == 1){
            createAccount();
        }
        else if(opt == 2){
            for(int i=0;i<=count;i++){
                BankCustomer c1 = customers.get(i);
                System.out.print(i+1);
                System.out.println(". "+c1.Name+"-----"+c1.Accno);
            }
            System.out.println("Please choose an account to enquire the balance  : ");
            int opt1 = sc.nextInt();
            BankCustomer a1 = customers.get(opt1-1);
            a1.balanceenquiry();
        }
        else if(opt == 3){
            for(int i=0;i<=count;i++){
                BankCustomer c1 = customers.get(i);
                System.out.print(i+1);
                System.out.println(". "+c1.Name+"-----"+c1.Accno);
            }
            System.out.println("Please choose an account to withdraw the money from  : ");
            int opt1 = sc.nextInt();
            BankCustomer a1 = customers.get(opt1-1);
            System.out.println("Please enter the amount");
            double amount = sc.nextDouble();
            a1.withdraw(amount);
        }
        else if(opt == 4){
            for(int i=0;i<=count;i++){
                BankCustomer c1 = customers.get(i);
                System.out.print(i+1);
                System.out.println(". "+c1.Name+"-----"+c1.Accno);
            }
            System.out.println("Please choose an account to deposit the money into  : ");
            int opt1 = sc.nextInt();
            BankCustomer a1 = customers.get(opt1-1);
            System.out.println("Please enter the amount");
            double amount = sc.nextDouble();
            a1.deposit(amount);
        }
        else if(opt == 5){
            System.out.println("Enter the amount : ");
            double amount = sc.nextDouble();
            Transfer(amount);
        }
        else if(opt == 6){
            DisplayAllAccounts();
        }
        else if(opt == 7){
            for(int i=0;i<=count;i++){
                BankCustomer c1 = customers.get(i);
                System.out.print(i+1);
                System.out.println(". "+c1.Name+"-----"+c1.Accno);
            }
            System.out.println("Please choose an account to change the pin of  : ");
            int opt1 = sc.nextInt();
            BankCustomer a1 = customers.get(opt1-1);
            a1.changepin();
        }
        else if(opt!=8){
            home();
        }
        System.out.println("Stopping");
        }
        else{
        if(opt == 1){
            createAccount();
        }
        else if(opt != 2){
            home();
        }
        System.out.println("Stopping");
        }
    }
    public static void main(String[] args){
        BankCustomer b1 = new BankCustomer();
        b1.home();
    }       
}
