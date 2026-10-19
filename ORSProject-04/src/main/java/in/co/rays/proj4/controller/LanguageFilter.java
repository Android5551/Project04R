package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebFilter("/*")
public class LanguageFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		MessageSource ms = MessageSource.getInstance();
		String lang = DataUtility.getString(req.getParameter("lang"));
		if (DataValidator.isNotNull(lang)) {
			ms.setLocale(lang);
		}
		chain.doFilter(req, res);
		// if u put else here ; it shows blank
		// better to run it everytime regardless of
		// lang value.
		// Browser → LanguageFilter → (other filters, if any)
		// → LoginCtl → LoginView.jsp
		

	}

	@Override
	public void destroy() {

	}

}
