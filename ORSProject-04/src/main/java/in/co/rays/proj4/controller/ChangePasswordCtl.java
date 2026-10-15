package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl<UserBean, UserModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", "oldPassword is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", "newPassword is required");
			pass = false;
		}

		return pass;

	}

	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));
	
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserBean bean = populateBean(req);
		UserModel model = getModel();
		// searches user data using findbylogin(login)
		bean = model.changePassword(req.getParameter("newPassword"), bean.getPassword(), bean.getLogin());

		if (bean != null) {
			ServletUtility.setSuccessMessage("your password has been changed check you email", req);
		} else {
			//login id is not in database.
			ServletUtility.setErrorMessage("your email is incorrect", req);
		}
		  ServletUtility.forward(getView(), req, resp);
//		super.doPost(req, resp);
	}

	@Override
	public UserModel getModel() {
		// TODO Auto-generated method stub
		return new UserModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
