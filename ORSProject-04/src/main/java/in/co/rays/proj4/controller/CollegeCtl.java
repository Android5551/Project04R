package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl<CollegeBean,CollegeModel>{
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "Name is required");
			return false;
		}
		if(DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "Address is required");
			return false;
		}
		if(DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", "State is required");
			return false;
		}
		if(DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", "City is required");
			return false;
		}
		if(DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Phone No is required");
			return false;
		}
		return pass;
	}
	
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {
		CollegeBean bean = new CollegeBean();
//		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
		populateDTO(bean, request);
		return bean;
	}
	
	@Override
	public CollegeModel getModel() {
		// TODO Auto-generated method stub
		return new CollegeModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_VIEW;
	}

}
