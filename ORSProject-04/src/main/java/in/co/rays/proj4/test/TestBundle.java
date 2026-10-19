package in.co.rays.proj4.test;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestBundle {
	

	public TestBundle() {
		this("hello");
	}
	
	public TestBundle(String t) {
		System.out.println(t);
	}
	
	public static void main(String[] args) {
		ResourceBundle r = ResourceBundle.getBundle("in.co.rays.proj4.bundle.message",new Locale("hi"));
		r.getString("login.title");
		System.out.println(r.getString("login.title"));
		TestBundle x = new TestBundle();
	}
}
