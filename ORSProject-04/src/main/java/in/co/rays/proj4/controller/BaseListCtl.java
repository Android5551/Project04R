package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
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
		int pageSize = 10;
		
		List<B> list = model.search(bean, 1, 10);
		
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		
		
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
