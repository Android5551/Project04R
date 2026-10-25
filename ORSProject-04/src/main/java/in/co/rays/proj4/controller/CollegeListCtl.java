package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/CollegeListCtl")
public class CollegeListCtl extends BaseListCtl<CollegeBean,CollegeModel> {
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {
		CollegeBean bean = new CollegeBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
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
		return ORSView.COLLEGE_LIST_VIEW;
	}

}
