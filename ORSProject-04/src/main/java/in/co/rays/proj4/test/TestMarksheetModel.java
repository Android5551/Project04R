package in.co.rays.proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {
	public static MarksheetModel m = new MarksheetModel();

	public static void main(String[] args) {
		testAdd();
		testUpdate();
		testDelete();
		testSearch();
		testFindByPk();
	}

	private static void testFindByPk() {
		MarksheetBean b = m.findByPk(0);
		System.out.println(b.getRollNo());
		System.out.println(b.getStudentId());
		System.out.println(b.getName());
		System.out.println(b.getPhysics());
		System.out.println(b.getChemistry());
		System.out.println(b.getMaths());
		System.out.println(b.getCreatedBy());
		System.out.println(b.getModifiedBy());
		System.out.println(b.getPhysics());
		System.out.println(b.getChemistry());
		System.out.println(b.getMaths());

	}

	private static void testSearch() {
		MarksheetBean b = new MarksheetBean();
		b.setName(null);
		List<MarksheetBean> l = m.search(b, 1, 5);
		
		Iterator <MarksheetBean> i = l.iterator();
		while(i.hasNext()) {
			b = i.next();
			System.out.println(b.getRollNo());
			System.out.println(b.getStudentId());
			System.out.println(b.getName());
			System.out.println(b.getPhysics());
			System.out.println(b.getChemistry());
			System.out.println(b.getMaths());
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
		MarksheetBean b = new MarksheetBean();
		b.setRollNo(null);
		b.setStudentId(0);
		b.setName(null);
		b.setPhysics(0);
		b.setChemistry(0);
		b.setMaths(0);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		b.setId(0);
		m.update(b);

	}

	private static void testAdd() {
		MarksheetBean b = new MarksheetBean();
		b.setRollNo(null);
		b.setStudentId(0);
		b.setName(null);
		b.setPhysics(0);
		b.setChemistry(0);
		b.setMaths(0);
		b.setCreatedBy(null);
		b.setModifiedBy(null);
		b.setCreatedDatetime(null);
		b.setModifiedDatetime(null);
		m.add(b);
	}
}
