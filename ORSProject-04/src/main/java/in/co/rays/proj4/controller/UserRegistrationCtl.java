package in.co.rays.proj4.controller;

import java.io.IOException;

import org.jfree.chart.servlet.ServletUtilities;

import com.ctc.wstx.util.DataUtil;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl<UserBean, UserModel> {

	public static final String OP_SIGN_UP = "SignUp";

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
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is not in valid formate");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "confirmPassword is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
		}
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Confirm  Password  should not be matched.");
			pass = false;
		}

		return pass;
	}
	@Override
	protected UserBean populateBean(HttpServletRequest r) {
		UserBean b = new UserBean();
		b.setFirstName(DataUtility.getString(r.getParameter("firstName")));
		b.setLastName(DataUtility.getString(r.getParameter("lastName")));
		b.setLogin(DataUtility.getString(r.getParameter("login")));
		b.setPassword(DataUtility.getString(r.getParameter("password")));
		b.setConfirmPassword(DataUtility.getString(r.getParameter("confirmPassword")));
		b.setDob(DataUtility.getDate(r.getParameter("dob")));
		b.setRoleId(2L); // role is student
		
		populateDTO(b,r);
		return b;
	}
	@Override
	protected void doPost(HttpServletRequest r, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(r.getParameter("operation"));
		UserBean b = populateBean(r);
		UserModel m = getModel();
		if(OP_SIGN_UP.equalsIgnoreCase(op)) {
			try {
				m.add(b);
				ServletUtility.setSuccessMessage("User is registered, login now", r);
			} catch (Exception e) {
				ServletUtility.setErrorMessage("Login id already exist", r);
			}
		}
		ServletUtility.forward(getView(), r, response);
	}

	@Override
	public UserModel getModel() {
		return new UserModel();
	}

	@Override
	public String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}