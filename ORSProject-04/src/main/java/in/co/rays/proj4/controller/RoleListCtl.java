package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends BaseListCtl<RoleBean, RoleModel>{
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {
		RoleBean b = new RoleBean();
		b.setName(DataUtility.getString(request.getParameter("name")));
		b.setDescription(DataUtility.getString(request.getParameter("description")));
		
		return b;
		
	}
	
	
	
	@Override
	public RoleModel getModel() {
		return new RoleModel();
	}

	@Override
	public String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}



}
