package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName","firstName is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName","lastName is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email","email is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo","mobileNo is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender","gender is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("dateOfBirth"))) {
			request.setAttribute("dateOfBirth","dateOfBirth is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId","collegeId is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("collegeName"))) {
			request.setAttribute("collegeName","collegeName is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address","address is required");
			pass = false;
		}
		return pass;
	}	
	
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		FacultyBean bean = new FacultyBean();
//		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		populateDTO(bean, request);
		return bean;
	}
	
	
	@Override
	public FacultyModel getModel() {
		// TODO Auto-generated method stub
		return new FacultyModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_VIEW;
	}

}
