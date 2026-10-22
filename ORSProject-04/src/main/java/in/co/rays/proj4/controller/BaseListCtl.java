package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseListCtl <B extends BaseBean,M extends BaseModel> extends BaseCtl<B, M>{
	// it will override baseCtl's do get
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		B bean = populateBean(request);
		M model = getModel();
		int pageNo = 1;
		int pageSize = 5;
		System.out.println("do Get of BaseListCtl");
		System.out.println("list");
		List<B> list = model.search(bean, 1, 5);
		System.out.println("Nextlist");
		List<B> nl = model.search(bean, pageNo+1, pageSize);
		request.setAttribute("nl",nl);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		
		
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In doPost of BaseListCtl");
		String op = req.getParameter("operation");
		System.out.println(op);
		int pageNo = 1;
		int pageSize = 5;
		B bean = populateBean(req);
		M model = getModel();
		
		if(OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = DataUtility.getInt(req.getParameter("pageNo"));
			String [] ids = req.getParameterValues("ids");
			System.out.println("-----------I D S "+ids);
			if(ids != null && ids.length>0) {
				for(String id : ids) {
					model.delete(DataUtility.getInt(id));
					ServletUtility.setSuccessMessage("record deleted successfully", req);
					
				}
			}else {
				ServletUtility.setErrorMessage("Select at least one record to delete", req);
			}
		}
		if(OP_NEXT.equalsIgnoreCase(op)) {
			System.out.println("in next");
			pageNo = DataUtility.getInt(req.getParameter("pageNo"));
			System.out.println(pageNo);
			pageNo++;
			System.out.println(pageNo);
		}
		if(OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo = DataUtility.getInt(req.getParameter("pageNo"));
			pageNo--;
		}
		if(OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1; 
		}
		
		System.out.println("list");
		List<B> l = model.search(bean, pageNo, pageSize);
		System.out.println("Nextlist");
		List<B> nl	= model.search(bean, pageNo+1, pageSize);
		req.setAttribute("nl", nl);
		ServletUtility.setList(l, req);
		ServletUtility.setPageNo(pageNo, req);
		ServletUtility.setPageSize(pageSize, req);
		
		ServletUtility.forward(getView(), req, resp);
		
	}
}
