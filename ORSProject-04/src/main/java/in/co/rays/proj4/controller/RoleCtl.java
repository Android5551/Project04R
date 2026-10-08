package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/RoleCtl")
public class RoleCtl extends BaseCtl<RoleBean, RoleModel>{

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("name"))) {
			ServletUtility.setErrorMessage("name is required", request);
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("description"))) {
			ServletUtility.setErrorMessage("description is required", request);;
			pass = false;
		}
		return pass;
	}
	
	@Override
	protected RoleBean populateBean(HttpServletRequest req) {
		RoleBean b = new RoleBean();
		b.setName(DataUtility.getString(req.getParameter("name")));
		b.setDescription(DataUtility.getString(req.getParameter("description")));
		populateDTO(b, req);
		return b;
	}
	
	@Override
	public RoleModel getModel() {
		// TODO Auto-generated method stub
		return new RoleModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROLE_VIEW;
	}
	
}
