package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.Servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl<StudentBean, StudentModel> {
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel c = new CollegeModel();
		List<CollegeBean> l = c.search(null, 0, 0);
		ServletUtility.setList(l, request);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateOfBirth"))) {
			request.setAttribute("dateOfBirth", "dateOfBirth is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobileNo is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "email is required");
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", "email is not in valid formate");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "collegeId is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeName"))) {
			request.setAttribute("collegeName", "collegeName is required");
			pass = false;
		}
		return pass;
	}

	@Override
	protected StudentBean populateBean(HttpServletRequest request) {
		StudentBean b = new StudentBean();
		b.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		b.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
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
		return new StudentModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.STUDENT_VIEW;
	}

}
