package in.co.rays.proj4.test;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {
	public static CollegeModel m = new CollegeModel();
	
	public static void main(String[] args) {
		testAdd();
		testUpdate();
		testFindByPk();
		testSearch();
		testDelete();
	}

	private static void testDelete() {
		m.delete(0);
		
	}

	private static void testSearch() {
		CollegeBean b = new CollegeBean();
		b.setAddress(null);
		List<CollegeBean> l = m.search(b, 1, 5);
		Iterator<CollegeBean> i = l.iterator();
		
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getName());
			System.out.println(b.getAddress());
			System.out.println(b.getState());
			System.out.println(b.getCity());
			System.out.println(b.getPhoneNo());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
		
	}

	private static void testFindByPk() {
		CollegeBean b = m.findByPk(0);
		System.out.println(b.getName());
		System.out.println(b.getAddress());
		System.out.println(b.getState());
		System.out.println(b.getCity());
		System.out.println(b.getPhoneNo());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getCreatedDatetime());
		System.out.println(b.getModifiedDatetime());
		
	}

	private static void testUpdate() {
		CollegeBean b = new CollegeBean();
		b.setId(0);
		b.setName("");
		b.setAddress("");
		b.setState("");
		b.setName("");
		b.setCity("");
		b.setPhoneNo("");
		b.setCreatedBy("");
		b.setModifiedBy("");
		b.setCreatedDatetime(new Timestamp(new Date().getTime()));
		b.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.update(b);
		
	}

	private static void testAdd() {
		CollegeBean b = new CollegeBean();
		b.setId(0);
		b.setName("");
		b.setAddress("");
		b.setState("");
		b.setName("");
		b.setCity("");
		b.setPhoneNo("");
		b.setCreatedBy("");
		b.setModifiedBy("");
		b.setCreatedDatetime(new Timestamp(new Date().getTime()));
		b.setModifiedDatetime(new Timestamp(new Date().getTime()));

		m.add(b);
		
	}
	
}
