package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.http.HttpServletRequest;

public class StudentListCtl extends BaseListCtl<StudentBean, StudentModel> {
	
	protected StudentBean populateBean(HttpServletRequest request) {
		StudentBean b = new StudentBean();
		b.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
//		b.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		b.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		b.setLastName(DataUtility.getString(request.getParameter("lastName")));
		b.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		b.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		b.setEmail(DataUtility.getString(request.getParameter("email")));

		populateDTO(b, request);
		return b;

	}

	
	@Override
	public StudentModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
