package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

public class StudentBean extends BaseBean{
	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String mobileNo;
	private String email;
	
	

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	/*college_id, college_name, first_name, last_name, date_of_birth, mobile_no, email
	 * 	private long collegeId;
	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String mobileNo;
	private String email;
	 * */
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			// set in b and get from rs
			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setDateOfBirth(rs.getDate("date_of_birth"));
			setMobileNo(rs.getString("mobile_no"));
			setEmail(rs.getString("email"));
			System.out.println("Got from rs and set to b");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}
