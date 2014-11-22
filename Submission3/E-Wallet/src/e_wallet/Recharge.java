package e_wallet;

import java.util.*;

public class Recharge {
	private String bits_id;
	private int amount;
	private int pin;
	Database db;
	Student student;
	private int trycount=0;
	Scanner in = new Scanner(System.in);
	public Recharge(Database db, String bits_id){
		this.bits_id=bits_id;
		this.db=db;
		this.student = db.getStudent(bits_id);
	}
	
	public void enterRechargeAmount(){
		while(trycount<=2){
			System.out.println("Your Current E-Wallet Balance is "+ student.ewbalance);	
			System.out.println("Your Current SWD Balance is "+ student.swdbalance);	
			System.out.println("Enter the recharge amount.");
			amount=in.nextInt();
			if(amount<=student.swdbalance){
				System.out.println("Enter PIN");
				while(!in.hasNextInt()) {
					System.out.println("Enter a valid PIN you doofus: ");
					in.next();
				}
				pin=in.nextInt();
				if(db.verifyPIN(student,pin)){
					student.ewbalance+=amount;
					student.swdbalance-=amount;
					db.updateBalance(student.bits_id, student.ewbalance, student.swdbalance);
					trycount=10;
				}
				else{
					System.out.printf("The Entered PIN is incorrect.\nYou have %d counts remaining.\n",2-trycount); 
					trycount++;
				}
			}
			else{
				System.out.println("The amount entered exceeds your SWD Balance. would you like to avail credit?\n1.Yes\n2.No");
				switch(in.nextInt()){
				case 1:takeCredit();
						break;
				case 2:System.out.println("Okay. Exiting to main menu...");	
					trycount=10;
					break;
				default:System.out.println("Invalid Choice");
				}
			}
		}
	}
	public void takeCredit(){
		
		
			System.out.printf("According to your requested amount you would like to take Credit for Rs. %d\n", amount-student.swdbalance);
			System.out.println("Available Credits " + student.credit_avail);
			if((amount-student.swdbalance)<=student.credit_avail){
				System.out.println("Enter PIN");
				while(!in.hasNextInt()) {
					System.out.println("Enter a valid PIN you doofus: ");
					in.next();
				}
				pin=in.nextInt();
				if(db.verifyPIN(student,pin)){
					student.ewbalance+=amount;
					amount-=student.swdbalance;
					student.swdbalance=0;
					
					student.credit_avail-=amount;
					db.updateBalance(student.bits_id, student.ewbalance, student.swdbalance, student.credit_avail);
					trycount = 10;
				}
				else{
					System.out.println("The Entered PIN is incorrect.\nYou have "+(2-trycount)+" tries left"); 
					trycount++;
				}
			}
			else{
				System.out.println("You don't have enough credits available\nWould you like to try again.?\n1.Yes\n2.No");
				while(!in.hasNextInt()) {
					System.out.println("Enter 1 or 2 you doofus: ");
					in.next();
				}
				if(in.nextInt()!=1)
					trycount=10;
			}	
		
	}
}
