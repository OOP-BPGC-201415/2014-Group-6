package e_wallet;

import org.springframework.dao.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Database {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	int currentBalance;
	
	 public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	
	 public void create(String name, String uname, String password) {
	      
		 String SQL = "insert into vendor (name, uname, password, log) values (?, ?, ?, ?)";
	      String log=uname+".txt";
	      jdbcTemplateObject.update( SQL, name, uname, password, log);
	      System.out.println("Created Record Name = " + name + " User Name = " + uname);
	      return;
	 }
	 
	 public void create(String name, String bits_id, String password, int pin) {
	      String SQL = "insert into student (name, bits_id, password, pin, log) values (?, ?, ?, ?, ?)";
	      String log=bits_id+".txt";
	      jdbcTemplateObject.update( SQL, name, bits_id, password, pin, log);
	      System.out.println("Created Record Name = " + name + " BITS ID = " + bits_id);
	      return;
	 }
	 
	 public void delete(int i,String lcred){
	     String SQL; 
		 if(i==1)
		 	  SQL = "delete from student where bits_id = ?";
	      else
	    	  SQL = "delete from vendor where uname = ?";
	      jdbcTemplateObject.update(SQL, lcred);
	      System.out.println("Deleted Record with ID = " + lcred );
	      return;
	   }
	 
	 public Student getStudent(String bits_id) {
		 Student student=null;
		 try{ 
		 String SQL = "select * from Student where bits_id = ?";
	     student = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{bits_id}, new StudentMapper());
	     
	   
	     }catch(EmptyResultDataAccessException e){
	    	 
	     }
	     
	    return student; 
	     
	 }
	 
	 public Vendor getVendor(String uname) {
		 Vendor vendor=null;
		 try{ 
		 String SQL = "select * from Vendor where uname = ?";
	     vendor = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{uname}, new VendorMapper());
	     
	   
	     }catch(EmptyResultDataAccessException e){
	    	 
	     }
	     
	    return vendor; 
	     
	 }
	 
	  public void updateBalance(String uname, int ewbalance){
	      String SQL = "update Vendor set ewbalance = ? where uname = ?";
	      jdbcTemplateObject.update(SQL, ewbalance, uname);
	      System.out.println("Transferred amount to" + uname);
	      return;
	  }
	  public void updateBalance(String bits_id, int ewbalance, int swdbalance){
	      String SQL = "update Student set ewbalance = ?, swdbalance = ? where bits_id = ?";
	      jdbcTemplateObject.update(SQL, ewbalance, swdbalance, bits_id);
	      System.out.printf("The new E-Wallet Balance for "+bits_id+" is %d\nReturning back to the main menu.\n", ewbalance);
	      return;
	  }
	  
	  public void updateBalance(String bits_id, int ewbalance, int swdbalance, int credit_avail){
	      String SQL = "update Student set ewbalance = ?, swdbalance = ?, credit_avail = ? where bits_id = ?";
	      jdbcTemplateObject.update(SQL, ewbalance, swdbalance, credit_avail, bits_id);
	      System.out.printf("The new E-Wallet Balance for "+bits_id+" is %d\nReturning back to the main menu.\n", ewbalance);
	      return;
	   }
	 
	public boolean verifyIDExists(int i,String lcred){
		boolean result=true;
		String SQL;
		try{ 
			if(i==1){
				SQL = "select * from Student where bits_id = ?";
				Student student = jdbcTemplateObject.queryForObject(SQL, 
                    new Object[]{lcred}, new StudentMapper());
			}
			else{
				SQL = "select * from vendor where uname = ?";
				Vendor vendor = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{lcred}, new VendorMapper());
			}
		   
		     }catch(EmptyResultDataAccessException e){
		    	 result=false;
		     }
		     
		    return result; 
		     
	}
	public boolean verifyPassword(Student student,String password){
		if(password.equals(student.password))
			return true;
		else
			return false;
	}
	
	public boolean verifyPassword(Vendor vendor,String password){
		if(password.equals(vendor.password))
			return true;
		else
			return false;
	}
	
	public boolean verifyPIN(Student student,int pin){
		if(student.pin==pin)
			return true;
		else
			return false;
	}
	
	public int checkBalance(String bits_id){
		Student student = getStudent(bits_id);
		return student.ewbalance;
	}
	
	public int checkBalanceVendor(String uname){
		Vendor vendor = getVendor(uname);
		return vendor.ewbalance;
	}
}
