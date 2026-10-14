package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseListCtl <B extends BaseBean,M extends BaseModel> extends BaseCtl<B,M>{
	// it will override baseCtl's do get
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		B bean = populateBean(request);
		M model = getModel();
		int pageNo = 1;
		int pageSize = 5;
		
		List<B> list = model.search(bean, 1, 5);
		
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		
		
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");
		int pageNo = 1;
		int pageSize = 5;
		B bean = populateBean(req);
		M model = getModel();
		if("delete".equalsIgnoreCase(op)) {
			String [] ids = req.getParameterValues("ids");
			if(ids != null && ids.length>0) {
				for(String id : ids) {
					model.delete(DataUtility.getInt(id));
					ServletUtility.setSuccessMessage("record deleted successfully", req);
					
				}
			}else {
				ServletUtility.setErrorMessage("Select at least one record to delete", req);
			}
		}
		if("next".equalsIgnoreCase(op)) {
			System.out.println("in next");
			pageNo = DataUtility.getInt(req.getParameter("pageNo"));
			System.out.println(pageNo);
			pageNo++;
			System.out.println(pageNo);
		}
		if("previous".equalsIgnoreCase(op)) {
			pageNo = DataUtility.getInt(req.getParameter("pageNo"));
			pageNo--;
		}
		
		
		List<B> l = model.search(bean, pageNo, pageSize);
		
		ServletUtility.setList(l, req);
		ServletUtility.setPageNo(pageNo, req);
		ServletUtility.setPageSize(pageSize, req);
		
		ServletUtility.forward(getView(), req, resp);
		
	}
}
