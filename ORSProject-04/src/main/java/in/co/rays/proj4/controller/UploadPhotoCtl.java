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
		UserBean bean = model.findByPk(id); //select * from st_user where id=?
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
		// to get specific part of multi part request; here it is photo.
		Part part = request.getPart("photo");
		
		// Input validation: Check if photo is uploaded or not
		if (part == null || part.getSize() == 0) {
			ServletUtility.setErrorMessage("Photo is required", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}
		// gets photo's original file name
		String fileName = part.getSubmittedFileName();

		System.out.println("image name: " + fileName);
		// gets image folder path from properties file
		String photoPath = PropertyReader.getValue("photoPath");

		System.out.println("folder path: " + photoPath);
		// created file's object and passed folder path.
		File folder = new File(photoPath);
		// if folder doesn't exist, create it.
		if (!folder.exists()) {
			folder.mkdirs();
		}
		// Creates a file at the specified folder with the given file name
		File destFile = new File(folder, fileName);
		System.out.println(destFile); //C:\Users\Piyush\Documents\CODING\REPEAT\Mission-Project04\img\m3.png
		// Reads the uploaded photo's data as bytes
		InputStream input = part.getInputStream();
		// writes that data into the destination file.
		FileOutputStream output = new FileOutputStream(destFile);
		/* Creates a temporary storage space to read the photo's data in chunks
		 * Instead of reading the entire photo at once, 
		 * we can read it in small chunks and write each chunk to the destination file.
		 * For example, a 1 MB photo can be copied in multiple chunks using this 4 KB buffer.
		 */
		byte[] buffer = new byte[4096]; //4kb
		
		int bytesRead = input.read(buffer);

		while (bytesRead != -1) {
			output.write(buffer, 0, bytesRead);
			bytesRead = input.read(buffer);
		}

		input.close();
		output.close();

		try {
			UserModel model = new UserModel();
			model.updatephoto(id, fileName); //1 row updated!
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