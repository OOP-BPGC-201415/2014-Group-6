The e-Wallet folder contains a beta version of our e-Wallet software.
Currently, the basic functionality has been implemented, i.e., receiving payments and recharging the e-Wallet account. The View Logs functionality will be implemented in the final version alongwith the added features like, loyalty benefits for using the e-Wallet payment.

Spring framework has been used for jdbc. Spring framework needs commons logging and mysql connector.

The following classes have been used in the design:

Controller.java
Database.java
PaymentTracker.java
Recharge.java
ReceivePayment.java
Login.java
Vendor.java
VendorMapper.java
Student.java
StudentMapper.java


The database has been linked through the Beans.xml file using the jdbcDriver. The setup required for the database has been mentioned in the Beans.xml file.
Currently hosted on localhost over port 3306.

The database should contain two tables, viz. student and vendor.
The Student table contains nine columns viz. id(primary+auto_increment), name(varchar), bits_id(primary)(varchar), password(varchar), pin(int), ewbalance(default=0), credit_avail(default=5000), swdbalance(default=10000) and log
The Vendor table contains nine columns viz. id(primary+auto_increment), name(varchar), uname(varchar), password(varchar), ewbalance(default=0), and log(varchar)

(The security features will be implemented in the final version, i.e., password and pin hashing.)

Usage:
	The payments are done using the Receive Payment option available to the Vendor. During each payment the student is required to enter his ID and PIN to complete the transaction. In case of insufficient balance the student is given the option to recharge in between the payment process which requires the student to also enter their E-Wallet password. 
	Recharge facility is available to the student under the Student menu as well. Credit facility is available in case of insufficient Virtual Balance in the account. PIN is required for Recharging the E-Wallet.
	Check Balance facility is available for both the Student and the Vendor.

	Current package is a menu driven CLI. The GUI will be implemented in the final build.

