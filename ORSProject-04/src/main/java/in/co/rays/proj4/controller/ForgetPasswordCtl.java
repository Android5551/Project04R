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

@WebServlet("/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl<UserBean, UserModel> {
	@Override
	protected boolean validate(HttpServletRequest req) {
		boolean pass = true;

		if (DataValidator.isNull(req.getParameter("login"))) {
			req.setAttribute("login", "login is required");
			pass = false;
		}

		return pass;

	}

	@Override
	protected UserBean populateBean(HttpServletRequest req) {
		UserBean bean = new UserBean();
		bean.setLogin(DataUtility.getString(req.getParameter("login")));
		return bean;
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel model = getModel();
		UserBean bean = populateBean(request);

		bean = model.forgotPassword(bean.getLogin());

		if (bean != null) {
			ServletUtility.setSuccessMessage("Password reset successful! Check your email.", request);
		} else {
			ServletUtility.setErrorMessage("Invalid email address.", request);
		}

		ServletUtility.forward(getView(), request, response);
	}
	@Override
	public UserModel getModel() {
	
		return new UserModel();
	}

	@Override
	public String getView() {
		
		return ORSView.FORGET_PASSWORD_VIEW;
	}
}
