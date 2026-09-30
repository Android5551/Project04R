package in.co.rays.proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {
	public static CourseModel m = new CourseModel();
	
	public static void main(String[] args) {
		testAdd();
		testUpdate();
		testDelete();
		testFindByPk();
		testSearch();
		
	}

	private static void testSearch() {
		CourseBean b = new CourseBean();
		b.setName(null);
		List <CourseBean> l = m.search(b, 1, 5);
		Iterator<CourseBean> i = l.iterator();
		
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getName());
			System.out.println(b.getDescription());
			System.out.println(b.getDuration());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
	}

	private static void testFindByPk() {
		CourseBean b = m.findByPk(0);
		System.out.println(b.getName());
		System.out.println(b.getDescription());
		System.out.println(b.getDuration());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getCreatedDatetime());
		System.out.println(b.getModifiedDatetime());
		
	}

	private static void testDelete() {
		m.delete(0);
		
	}

	private static void testUpdate() {
		CourseBean b = new CourseBean();
		b.setId(0);
		b.setName(null);
		b.setDescription(null);
		b.setDuration(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		m.update(b);
	}

	private static void testAdd() {
		CourseBean b = new CourseBean();
		b.setName(null);
		b.setDescription(null);
		b.setDuration(null);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		m.add(b);
		
	}
	
}
