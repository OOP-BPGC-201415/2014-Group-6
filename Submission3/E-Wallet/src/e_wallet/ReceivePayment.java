package e_wallet;

import java.util.*;

public class ReceivePayment {
	private String bits_id;
	private String lcred;
	private String password;
	private int amount;
	private int pin;
	Database db;
	Vendor vendor;
	Student student=null;
	private int trycount=0;
	
	public ReceivePayment(Database db, String lcred, int amount){
		this.lcred=lcred;
		this.db=db;
		this.amount=amount;
		this.vendor = db.getVendor(lcred);
	}
	
	public void enterStudentDetails(){
		while(trycount<3){
			Scanner in = new Scanner(System.in);
			System.out.printf("You have requested the amount Rs.%d.\n1.Correct\n2.Incorrect\n\nEnter 1 or 2.",amount);
			while(!in.hasNextInt()) {
				System.out.println("Enter a valid integer input you doofus: ");
				in.next();
			}
			if(in.nextInt()==1){
				Scanner a = new Scanner(System.in);
				System.out.println("Enter the Student ID");
				bits_id=a.nextLine();
				bits_id=bits_id.toLowerCase();
				if(db.verifyIDExists(1,bits_id)){
					student = db.getStudent(bits_id);
					if(amount<=student.ewbalance){
					
						System.out.println("Enter PIN");
						while(!a.hasNextInt()) {
							System.out.println("Enter a valid PIN you doofus: ");
							a.next();
						}
						pin=a.nextInt();
						if(db.verifyPIN(student,pin)){
							student.ewbalance-=amount;
							vendor.ewbalance+=amount;
							db.updateBalance(lcred, vendor.ewbalance);
							db.updateBalance(student.bits_id, student.ewbalance, student.swdbalance);
							trycount=10;
						}
						else{
							System.out.println("The Entered PIN is incorrect.\nYou have "+(2-trycount)+" tries left"); 
							trycount++;
					
						}
					}
				
					else{
						System.out.println("The amount entered exceeds your E-Wallet Balance. Would you like to Recharge?\n1.Yes\n2.No");
						switch(a.nextInt()){
						case 1:Scanner ob=new Scanner(System.in);
							System.out.println("Enter your password");
							password=ob.nextLine();
							if(db.verifyPassword(student, password)){
								PaymentTracker pt=new PaymentTracker(db,bits_id);
								pt.initiateRecharge();
							}
							else{
								System.out.println("Invalid Password.");
								break;
							}
					
						case 2:break;
						default:System.out.println("Invalid Choice");
						}
					}
				
				}
				else
					System.out.println("Entered ID does not exist\nTry Again.");
		
			
			}
			else
				trycount=10;
	
		
		}
	}	
}

