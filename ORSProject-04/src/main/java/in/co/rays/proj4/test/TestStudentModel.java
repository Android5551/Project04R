package in.co.rays.proj4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {
	public static StudentModel m = new StudentModel();
	public static SimpleDateFormat s = 
			new SimpleDateFormat("yyyy-MM-dd"); 
	
	public static void main(String[] args) throws Exception {
		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
	}

	private static void testSearch() {
		StudentBean b = new StudentBean();
		b.setCollegeName(null);
		List<StudentBean> l = m.search(b, 1, 5);
		Iterator<StudentBean> i = l.iterator();
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getCollegeId());
			System.out.println(b.getCollegeName());
			System.out.println(b.getFirstName());
			System.out.println(b.getLastName());
			System.out.println(b.getDateOfBirth());
			System.out.println(b.getMobileNo());
			System.out.println(b.getEmail());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
		
	}

	private static void testFindByPk() {
		StudentBean b = m.findByPk(0);
		System.out.println(b.getCollegeId());
		System.out.println(b.getCollegeName());
		System.out.println(b.getFirstName());
		System.out.println(b.getLastName());
		System.out.println(b.getDateOfBirth());
		System.out.println(b.getMobileNo());
		System.out.println(b.getEmail());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getCreatedDatetime());
		System.out.println(b.getModifiedDatetime());
		
		
	}

	private static void testDelete() {
		m.delete(0);
		
	}

	private static void testUpdate() {
		StudentBean b = new StudentBean();
		b.setCollegeId(0);
		b.setCollegeName(null);
		b.setFirstName(null);
		b.setLastName(null);
		b.setDateOfBirth(null);
		b.setMobileNo(null);
		b.setEmail(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		m.update(b);
	}

	private static void testAdd() throws Exception {
		StudentBean b = new StudentBean();
//		b.setCollegeName(null);
		b.setCollegeId(1);
		b.setFirstName(null);
		b.setLastName(null);
		b.setDateOfBirth(s.parse("2000-09-20"));
		b.setMobileNo(null);
		b.setEmail(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
//		b.setCollegeId(0);
		m.add(b);
	}
}
