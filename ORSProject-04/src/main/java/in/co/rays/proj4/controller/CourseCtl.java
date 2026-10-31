package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/CourseCtl")
public class CourseCtl extends BaseCtl<CourseBean, CourseModel> {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("courseName"))) {
			request.setAttribute("courseName", "Course Name is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("courseDescription"))) {
			request.setAttribute("courseDescription", "Course Description is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("courseDuration"))) {
			request.setAttribute("courseDuration", "Course Duration is required");
			pass = false;
		}
		return pass;
	}
	
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {
		CourseBean bean = new CourseBean();
//		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("courseName")));
		bean.setDescription(DataUtility.getString(request.getParameter("courseDescription")));
		bean.setDuration(DataUtility.getString(request.getParameter("courseDuration")));
		populateDTO(bean, request);
		return bean;
	}
	
	
	
	@Override
	public CourseModel getModel() {
		// TODO Auto-generated method stub
		return new CourseModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_VIEW;
	}

}
