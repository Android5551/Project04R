package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class RoleBean extends BaseBean{
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setName(rs.getString("name")); // col name
			setDescription(rs.getString("description"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.setResultSet(rs); // csuperall it to set rs to 
	}
	
}
