package e_wallet;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import e_wallet.Database;

public class Controller {
	
	static boolean loginStatus=false;
	static String name;
	static String bits_id;
	static String password;
	static String uname;
	
	static ApplicationContext context = 
            new ClassPathXmlApplicationContext("Beans.xml");
    static Database db = 
   	      (Database)context.getBean("db");
    
	
    static private void loginStudent(){
		Login l = new Login(bits_id,password,db);
		if(l.resultStudent()){
			name=l.getStudentName();
			loginStatus=true;
		}else
			loginStatus=false;
	}
    
    static private void loginVendor(){
		Login l = new Login(uname,password,db);
		if(l.resultVendor()){
			name=l.getVendorName();
			loginStatus=true;
		}else
			loginStatus=false;
	}
	
	static private void receivePayment(){
		PaymentTracker ptracker  = new PaymentTracker(db,uname);
		ptracker.initiateTransaction();
	}
    
	static private void recharge(){
		PaymentTracker ptracker  = new PaymentTracker(db,bits_id);
		ptracker.initiateRecharge();
	}
    
	static private void viewLogs(){
		System.out.println("Feature not available yet. Sorry for the inconvenience");
	}
	
	static private void admin(){
		
		Scanner ob = new Scanner(System.in);
		System.out.println("Enter Password");
		if(ob.nextLine().equals("group6")){
			loginStatus = true;
		}
			while(loginStatus){
				
			System.out.println("Enter choice\n1.Create Student\n2.Create Vendor\n3.Delete Student\n4.Delete Vendor\n5.Exit\nEnter 1,2,3,4 or 5");
			while(!ob.hasNextInt()) {
				System.out.println("Enter a valid integer input you doofus: ");
				ob.next();
			}
			Scanner in = new Scanner(System.in);
			switch(ob.nextInt()){
			case 1: System.out.println("Enter Name");
					
					String name = in.nextLine();
					
					System.out.println("Enter Bits ID");
					String id = in.nextLine();
					System.out.println("Enter Password");
					String pass = in.nextLine();
					System.out.println("Enter PIN(Valid length is 4 digits)");
					while(!in.hasNextInt()) {
						System.out.println("Enter a valid PIN: ");
						in.next();
					}
					int pin = in.nextInt();
					if(!db.verifyIDExists(1,id))
						db.create(name,id,pass,pin);
					else
						System.out.println("User already exists in the database");
					break;
			case 2: System.out.println("Enter Name");
				    name = in.nextLine();
					System.out.println("Enter Username");
					uname = in.nextLine();
					System.out.println("Enter Password");
					pass = in.nextLine();
					if(!db.verifyIDExists(0,uname))
						db.create(name,uname,pass);
					else
						System.out.println("User already exists in the database");
					break;	
			case 3: System.out.println("Enter ID Number");
					id=in.nextLine();
					db.delete(1, id);
					break;
			case 4: System.out.println("Enter User Name");
					id=in.nextLine();
					db.delete(0, id);
					break;
			case 5: loginStatus = false;		
					break;
			default: System.out.println("Sirf 5 option hai");
			}
		}
	}
	
	static private void student(){
		Scanner in = new Scanner(System.in);
		while(true){
		System.out.println("Hello Student. Enter your ID number");
		bits_id = in.nextLine();
		bits_id = bits_id.toLowerCase();
		System.out.println("Enter you password");
		password = in.nextLine();
		loginStudent();
		if(loginStatus)
			break;
		if(!loginStatus)
			System.out.println("No such Student exists. Please try again");
		}
		while(loginStatus){
			System.out.println("\n\nHello "+name+". What would you like to do today?\n1.Recharge E-Wallet\n2.Check Balance\n3.View Logs(Not available as of now. Wait for version 2.0 :D)\n4.Exit\n\nEnter 1,2,3 or 4.");
			
			while(!in.hasNextInt()) {
				System.out.println("Enter a valid integer input you doofus: ");
				in.next();
			}
			
			switch(in.nextInt()){
			case 1: recharge();
					loginStatus=true;
					break;
			case 2: System.out.println("You current E-Wallet Balance is Rs."+ db.checkBalance(bits_id));
					loginStatus=true;
					break;
			case 3: viewLogs();
					loginStatus=true;
					break;
			case 4: System.out.println("Thank you "+name+" for using BITS-Goa E-Wallet!");
					loginStatus = false;
					break;
			default: System.out.println("Invalid Input");
					 break;
			}
		}
		
		
		in.close();
	}
	
	static private void vendor(){
		Scanner in = new Scanner(System.in);
		
		System.out.println("Hello Vendor. Enter your Username");
		uname = in.nextLine();
		System.out.println("Enter you password");
		password = in.nextLine();
		loginVendor();
		if(!loginStatus)
			System.out.println("No such Vendor exists. Please try again");
		while(loginStatus){
			System.out.println("\n\nHello "+name+". What would you like to do today?\n1.Receive Payment\n2.View E-Wallet Balance\n3.View Logs(Not available as of now. Wait for version 2.0 :D)\n4.Exit\n\nEnter 1,2,3 or 4.");
			while(!in.hasNextInt()) {
				System.out.println("Enter a valid integer input you doofus: ");
				in.next();
			}
			
			switch(in.nextInt()){
			case 1: receivePayment();
					loginStatus=true;
					break;
			case 2:	System.out.println("You current E-Wallet Balance is Rs."+ db.checkBalanceVendor(uname));
					loginStatus=true;
					break;
			case 3: viewLogs();
					loginStatus=true;
					break;
			case 4: System.out.println("Thank you "+name+" for using BITS-Goa E-Wallet!");
					loginStatus = false;
					break;
			 default: System.out.println("Invalid Input");
			}
		}
		
		
		in.close();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        
	    System.out.println("Hello User. Welcome to the BITS-Goa E-Wallet System.");
	    boolean a=true;
	    while(a){
	    	System.out.println("Are you \n1.Student\n2.Vendor\n\nEnter 1 or 2");
	    	while(!in.hasNextInt()) {
				System.out.println("Enter a valid integer input you doofus: ");
				in.next();
			}
	    	switch(in.nextInt()){
	    	case 1: student();
	    	a=false;
	    			break;
	    
	    	case 2: vendor();
	    			a=false;
	    			break;
	    
	    	case 999: admin();		
	    	default: System.out.println("There are only 2 options");
	    			a=true;
	    	}
	    	
	    }
	    in.close();
	}
}
