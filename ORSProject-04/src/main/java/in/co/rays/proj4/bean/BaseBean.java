package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
// Parent class of all Beans in application. It contains generic attributes.
public abstract class BaseBean implements DropdownListBean{
	/* all common properties; as we have implemented interface either we need to make 
	 class abstract or override interface's methods. 
	 here we have overridden only key() and value() will be overridden in other child 
	classes making them concrete class.
	*/
	
	protected long id;
	protected String createdBy;
    protected String modifiedBy;
    protected Timestamp createdDatetime;
    protected Timestamp modifiedDatetime;
    
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}
	// as id is of type long.
	@Override
	public String getKey() {
		return id+"";
	}
	// get from rs set to bean return bean or list of bean.
	public void setResultSet(ResultSet rs) {
		try {
			setId(rs.getLong("id"));
			setCreatedBy(rs.getString("created_by"));
			setModifiedBy(rs.getString("modified_by"));
			setCreatedDatetime(rs.getTimestamp("created_datetime"));
			setModifiedDatetime(rs.getTimestamp("modified_datetime"));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
    
    
}
