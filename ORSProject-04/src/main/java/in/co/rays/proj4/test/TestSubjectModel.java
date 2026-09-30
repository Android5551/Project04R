package in.co.rays.proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {
	public static SubjectModel m = new SubjectModel();
	
	public static void main(String[] args) {
		testAdd();
//		testUpdate();
//		testSearch();
//		testFindByPk();
//		testDelete();
	}

	private static void testDelete() {
		// TODO Auto-generated method stub
		m.delete(0);
	}

	private static void testFindByPk() {
		SubjectBean b = m.findByPk(0);
		System.out.println(b.getName());
		System.out.println(b.getDescription());
		System.out.println(b.getCourseId());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getCreatedDatetime());
		System.out.println(b.getModifiedDatetime());
		
	}

	private static void testSearch() {
		SubjectBean b = new SubjectBean();
		List<SubjectBean> l = m.search(b, 1, 5);
		Iterator<SubjectBean> i = l.iterator();
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getName());
			System.out.println(b.getDescription());
			System.out.println(b.getCourseId());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
	}

	private static void testUpdate() {
		SubjectBean b = new SubjectBean();
		b.setName(null);
		b.setDescription(null);
		b.setCourseId(0);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		b.setId(0);
		m.update(b);
		
	}

	private static void testAdd() {
		SubjectBean b = new SubjectBean();
		b.setName(null);
		b.setDescription(null);
		b.setCourseId(0);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		m.add(b);
		
	}
	
}
