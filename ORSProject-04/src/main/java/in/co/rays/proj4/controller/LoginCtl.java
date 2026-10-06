package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl<UserBean, UserModel> {

	public final static String OP_SIGNIN = "SignIn";

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;
		System.out.println("In login's validate");
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			System.out.println("in login's login");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			System.out.println("in login's password");
			pass = false;
		}

		return pass;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		if (op != null) {
			HttpSession session = request.getSession();
			session.invalidate();
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected UserBean populateBean(HttpServletRequest r) {
		UserBean b = new UserBean();
		b.setLogin(DataUtility.getString(r.getParameter("login")));
		b.setPassword(DataUtility.getString(r.getParameter("password")));

		return b;
	}

	@Override
	protected void doPost(HttpServletRequest r, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(r.getParameter("operation"));

		UserBean b = populateBean(r);
		UserModel m = getModel();
		RoleModel rm = new RoleModel();
		HttpSession s = r.getSession();
		if (OP_SIGNIN.equalsIgnoreCase(op)) {

			b = m.authenticate(b.getLogin(), b.getPassword());
			if (b != null) {
				s.setAttribute("user", b); // user and user bean
				RoleBean rb = rm.findByPk(b.getRoleId());
				s.setAttribute("role", rb.getName()); // role admin
				ServletUtility.redirect(ORSView.WELCOME_CTL, r, response);
				return;
			} else {
				ServletUtility.setErrorMessage("Invalid login or password", r);
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
		return ORSView.LOGIN_VIEW;
	}

}