package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/UserListCtl")
public class UserListCtl extends BaseListCtl<UserBean, UserModel>{

	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		UserBean b = new UserBean();
		b.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		b.setLastName(DataUtility.getString(request.getParameter("lastName")));
		b.setLogin(DataUtility.getString(request.getParameter("login")));
		
		b.setDob(DataUtility.getDate(request.getParameter("dob")));
//		b.setMobileNo(DataUtility.getString(request.getParameter("mobile_no")));
		b.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
//		b.setUnsuccessfulLogin(DataUtility.getInt(request.getParameter("unsuccessful_login")));
		b.setGender(DataUtility.getString(request.getParameter("gender")));
//		b.setLastLogin(DataUtility.getTimestamp(request.getParameter("last_login")));
//		b.setUserLock(DataUtility.getString(request.getParameter("user_lock")));
//		b.setRegisteredIp(DataUtility.getString(request.getParameter("registered_ip")));
//		b.setLastLoginIp(DataUtility.getString(request.getParameter("last_login_ip")));


		return b;
				
	}
	
	@Override
	public UserModel getModel() {
		// TODO Auto-generated method stub
		return new UserModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.USER_LIST_VIEW;
	}

}
