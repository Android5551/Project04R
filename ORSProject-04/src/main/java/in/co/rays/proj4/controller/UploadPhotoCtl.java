package in.co.rays.proj4.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet("/ctl/UploadPhotoCtl")
@MultipartConfig
public class UploadPhotoCtl extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = DataUtility.getLong(req.getParameter("id"));
		UserModel model = new UserModel();
		UserBean bean = model.findByPk(id);
		ServletUtility.setBean(bean, req);
		//		RoleModel model = new RoleModel();
//		try {
//			List l = model.list(null,0,0);
//			req.setAttribute("roleList", l);
//		} catch (ApplicationException e) {
//		}
//		
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = new UserModel();

		UserBean bean = null;

		bean = model.findByPk(id);

		String photoPath = PropertyReader.getValue("photoPath");

		// User photo available? bean and photo is not null
		if (bean != null && DataValidator.isNotNull(bean.getPhoto())) {
			// folder's path + photo name
			File file = new File(photoPath, bean.getPhoto());
			// Check if file exists
			if (file.exists()) {
				// can take any type of image.
				response.setContentType(getServletContext().getMimeType(file.getName()));
				// reads input file and writes into output
				InputStream in = new java.io.FileInputStream(file);
				OutputStream out = response.getOutputStream();

				byte[] buffer = new byte[4096];
				int bytes = in.read(buffer);

				while (bytes != -1) {
					out.write(buffer, 0, bytes);
					bytes = in.read(buffer);
				}

				in.close();
				out.close();

				return;
			}
		}

		// Get Default photo if no photo found.
		InputStream in = getServletContext().getResourceAsStream("/img/logo.png");

		response.setContentType("image/png");

		OutputStream out = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytes = in.read(buffer);

		while (bytes != -1) {
			out.write(buffer, 0, bytes);
			bytes = in.read(buffer);
		}

		in.close();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
//		UserModel model = new UserModel();
//		UserBean bean = model.findByPk(id);
//		ServletUtility.setBean(bean, request);

		// Get uploaded photo
		Part part = request.getPart("photo");

		if (part == null || part.getSize() == 0) {
			ServletUtility.setErrorMessage("Photo is required", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		String fileName = part.getSubmittedFileName();

		System.out.println("image name: " + fileName);

		String photoPath = PropertyReader.getValue("photoPath");

		System.out.println("folder path: " + photoPath);

		File folder = new File(photoPath);

		if (!folder.exists()) {
			folder.mkdirs();
		}

		File destFile = new File(folder, fileName);

		InputStream input = part.getInputStream();
		FileOutputStream output = new FileOutputStream(destFile);

		byte[] buffer = new byte[4096];

		int bytesRead = input.read(buffer);

		while (bytesRead != -1) {
			output.write(buffer, 0, bytesRead);
			bytesRead = input.read(buffer);
		}

		input.close();
		output.close();

		try {
			UserModel model = new UserModel();
			model.updatephoto(id, fileName);
			ServletUtility.setSuccessMessage("photo upload successfully", request);

			System.out.println("Photo uploaded successfully: " + fileName);

		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.setErrorMessage("Photo upload failed", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	public String getView() {
		return ORSView.USER_VIEW;
	}

}