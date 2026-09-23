package in.co.rays.proj4.test;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;

/*1 = admin
2 = student
3 = faculty
4 = college
5 = KIOSK
*/

public class TestRoleModel {
	public static RoleModel m = new RoleModel();
//	public static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testSearch();

	}

	private static void testAdd() {
		RoleBean r = new RoleBean();
		r.setId(2L);
		r.setName("KIOSK");
		r.setDescription("KIOSK role");
		r.setCreatedBy("root");
		r.setModifiedBy("root");
		r.setCreatedDatetime(new Timestamp(new Date().getTime())); // converting
		// current datetime to sql timestamp
		r.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.add(r);
	}

	private static void testUpdate() {
		RoleBean r = new RoleBean();
		r.setId(2L);
		r.setName("student");
		r.setDescription("student role");
		r.setCreatedBy("root");
		r.setModifiedBy("root");
		r.setCreatedDatetime(new Timestamp(new Date().getTime()));
		r.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.update(r);

	}

	// TODO:don't remove admin otherwise can't set it to id 1
	private static void testDelete() {
		m.delete(7);
	}

	// complete class
	private static void testFindByPk() {
		RoleBean b = m.findByPk(1);
		System.out.println("name" + " | " + "description" + " | " + "created by");
		System.out.println(b.getName() + " | " + b.getDescription() + " | " 
		+ b.getCreatedBy());
	}
	
	// complete class
	public static void testSearch() {
		RoleBean b = new RoleBean();
		List<RoleBean> l = m.search(, 0, 0);
		
	}
}
