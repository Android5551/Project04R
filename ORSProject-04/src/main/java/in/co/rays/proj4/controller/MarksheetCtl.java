package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Roll No is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", "Student Id is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "Name is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", "Physics marks are required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", "Chemistry marks are required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", "Maths marks are required");
			pass = false;
		}
		return pass;
	}

	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {
		MarksheetBean b = new MarksheetBean();
		b.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		b.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		b.setName(DataUtility.getString(request.getParameter("name")));
		b.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		b.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		b.setMaths(DataUtility.getInt(request.getParameter("maths")));
		populateDTO(b, request);
		return b;
	}

	
	@Override
	public MarksheetModel getModel() {
		// TODO Auto-generated method stub
		return new MarksheetModel();
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_VIEW;
	}
	


}
