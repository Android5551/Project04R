package in.co.rays.proj4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {
	public static FacultyModel m = new FacultyModel();
	public static SimpleDateFormat s = 
			new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) throws ParseException {
		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();
//		testFindByPk();
	}
	private static void testSearch() {
		FacultyBean b = new FacultyBean();
		b.setFirstName(null);
		
		List<FacultyBean> l = m.search(b, 1, 5);
		Iterator<FacultyBean> i = l.iterator();
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getCollegeId());
			System.out.println(b.getCollegeName());
			System.out.println(b.getFirstName());
			System.out.println(b.getLastName());
			System.out.println(b.getEmail());
			System.out.println(b.getMobileNo());
			System.out.println(b.getEmail());
			System.out.println(b.getMobileNo());
			System.out.println(b.getAddress());
			System.out.println(b.getGender());
			System.out.println(b.getDateOfBirth());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
		
		
	}
	private static void testDelete() {
		m.delete(0);
	}
	private static void testUpdate() {
		FacultyBean b = new FacultyBean();
		
		b.setCollegeId(0);
		b.setCollegeName(null);
		b.setFirstName(null);
		b.setLastName(null);
		b.setEmail(null);
		b.setMobileNo(null);
		b.setAddress(null);
		b.setGender(null);
		b.setDateOfBirth(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		b.setId(0);
		m.update(b);
		
	}
	private static void testAdd() throws ParseException {
		FacultyBean b = new FacultyBean();
		b.setCollegeId(1);
//		b.setCollegeName(null);
		b.setFirstName(null);
		b.setLastName(null);
		b.setEmail(null);
		b.setMobileNo(null);
		b.setAddress(null);
		b.setGender(null);
		b.setDateOfBirth(s.parse("2000-09-12"));
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		m.add(b);
	}
	private static void testFindByPk() {
		FacultyBean b = m.findByPk(0);
		System.out.println(b.getCollegeId());
		System.out.println(b.getCollegeName());
		System.out.println(b.getFirstName());
		System.out.println(b.getLastName());
		System.out.println(b.getEmail());
		System.out.println(b.getMobileNo());
		System.out.println(b.getAddress());
		System.out.println(b.getGender());
		System.out.println(b.getDateOfBirth());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getCreatedDatetime());
		System.out.println(b.getModifiedDatetime());
	}
	
}
