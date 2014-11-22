package e_wallet;

public class Vendor {
	public int id;
	public String name;
	public String uname;
	public String password;
	public int ewbalance;
	public String log;
	
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEwbalance() {
		return ewbalance;
	}
	public void setEwbalance(int ewbalance) {
		this.ewbalance = ewbalance;
	}
	
}
