package e_wallet;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {
   public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
      Student student = new Student();
      student.setId(rs.getInt("id"));
      student.setName(rs.getString("name"));
      student.setBits_id(rs.getString("bits_id"));
      student.setPassword(rs.getString("password"));
      student.setPIN(rs.getInt("pin"));
      student.setEWBalance(rs.getInt("ewbalance"));
      student.setCredit_avail(rs.getInt("credit_avail"));
      student.setSwdbalance(rs.getInt("swdbalance"));
      student.setLog(rs.getString("log"));
      return student;
   }
}