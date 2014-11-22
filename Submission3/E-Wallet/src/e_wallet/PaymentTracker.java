package e_wallet;

import java.util.*;

public class PaymentTracker {
	
	String lcred;
	int balance;
	int amount;
	Database db;
	Student student;
	Vendor vendor;
	public PaymentTracker(Database db,String lcred){
		this.db=db;
		this.lcred = lcred;
		
	
	}
	
	public void createLog(Date start, Date end, int amount,int balance){
		
	}
	public void displayTransactionDetails(){
		
	}
	public void initiateTransaction(){
		vendor = db.getVendor(lcred);
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the total Bill Amount");
		while(!in.hasNextInt()) {
			System.out.println("Enter a valid integral input you doofus: ");
			in.next();
		}
		amount=in.nextInt();
		ReceivePayment rpobject = new ReceivePayment(db,lcred,amount);
		rpobject.enterStudentDetails();
		
	}
	public void initiateRecharge(){
		student = db.getStudent(lcred);
		Recharge robject = new Recharge(db,lcred);
		robject.enterRechargeAmount();
	}
}
