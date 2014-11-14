package e_wallet;

public class Login {
	
	String bits_id;
	String password;
	boolean result=false;
	Database db;
	
	public Login(String bits_id, String password,Database db){
		this.bits_id=bits_id;
		this.password=password;
		this.db=db;
	}
	
	public boolean resultStudent(){
		try{
		Student student = db.getStudent(bits_id);
		if(db.verifyPassword(student, password)){
			result = true;
		}
		}catch(NullPointerException e){
			return false;
		}
		return result;
	}
	
	public boolean resultVendor(){
		try{
		Vendor vendor = db.getVendor(bits_id);
		if(db.verifyPassword(vendor, password)){
			result = true;
		}
		}catch(NullPointerException e){
			return false;
		}
		return result;
	}
	
	public String getStudentName(){
		Student student = db.getStudent(bits_id);
		return student.name;
	}
	
	
	public String getVendorName(){
		Vendor vendor = db.getVendor(bits_id);
		return vendor.name;
	}
}
