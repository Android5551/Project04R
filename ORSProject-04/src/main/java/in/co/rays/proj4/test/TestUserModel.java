package in.co.rays.proj4.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {
	public static UserModel m = new UserModel();
	public static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		testAdd();
		testUpdate();
		testDelete();
		testFindByPk();
		testSearch();
	}

	private static void testSearch() {
		UserBean b = new UserBean();
		b.setFirstName("");
		List<UserBean> l = m.search(b, 1, 5);
		Iterator<UserBean> i = l.iterator();
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getFirstName());
			System.out.println(b.getLastName());
			System.out.println(b.getLogin());
			System.out.println(b.getPassword());
			System.out.println(b.getDob());
			System.out.println(b.getMobileNo());
			System.out.println(b.getRoleId());
			System.out.println(b.getUnsuccessfulLogin());
			System.out.println(b.getGender());
			System.out.println(b.getLastLogin());
			System.out.println(b.getUserLock());
			System.out.println(b.getRegisteredIp());
			System.out.println(b.getLastLoginIp());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
	}

	private static void testFindByPk() {
		UserBean b = m.findByPk(1L);
		System.out.println(b.getFirstName());
		System.out.println(b.getLastName());
		System.out.println(b.getLogin());
		System.out.println(b.getPassword());
		System.out.println(b.getDob());
		System.out.println(b.getMobileNo());
		System.out.println(b.getRoleId());
		System.out.println(b.getUnsuccessfulLogin());
		System.out.println(b.getGender());
		System.out.println(b.getLastLogin());
		System.out.println(b.getUserLock());
		System.out.println(b.getRegisteredIp());
		System.out.println(b.getLastLoginIp());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getCreatedDatetime());
		System.out.println(b.getModifiedDatetime());
	}

	private static void testDelete() {
		m.delete(1);
		
	}

	private static void testUpdate() {
		UserBean b = new UserBean();
		b.setId(1L);
		b.setFirstName(null);
		b.setLastName(null);
		b.setLogin(null);
		b.setPassword(null);
		b.setDob(null);
		b.setMobileNo(null);
		b.setRoleId(0);
		b.setUnsuccessfulLogin(0);
		b.setGender(null);
		b.setLastLogin(null);
		b.setUserLock(null);
		b.setRegisteredIp(null);
		b.setLastLoginIp(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(new Timestamp(new Date().getTime()));
		b.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.add(b);
	}

	public static void testAdd() {
		UserBean b = new UserBean();
		b.setId(1L);
		b.setFirstName(null);
		b.setLastName(null);
		b.setLogin(null);
		b.setPassword(null);
		b.setDob(null);
		b.setMobileNo(null);
		b.setRoleId(0);
		b.setUnsuccessfulLogin(0);
		b.setGender(null);
		b.setLastLogin(null);
		b.setUserLock(null);
		b.setRegisteredIp(null);
		b.setLastLoginIp(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(new Timestamp(new Date().getTime()));
		b.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.update(b);
	}
	
	
}
