package in.co.rays.proj4.test;

import java.util.HashMap;

import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

public class TestSmtp {

	public static void main(String[] args) {

//		testUserRegistrationMail();
//		testForgetPasswordMessage();
		testChangePasswordMessage();

	}

	public static void testUserRegistrationMail() {

		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", "tomarpoojasingh12@gmail.com"); //Recipient's login id.
		map.put("password", "12");

		msg.setTo(map.get("login")); //login id.
		msg.setSubject("User Rgistration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully");

	}
	public static void testForgetPasswordMessage() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();
		
		map.put("login", "tomarpoojasingh12@gmail.com"); //Recipient's login id.
		map.put("password", "12");
		map.put("firstName", "Pooja");
		map.put("lastName", "Singh Tomar");
		msg.setTo(map.get("login")); //login id.
		msg.setSubject("Forget Password");
		msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully");
		
	}
	public static void testChangePasswordMessage() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();
		
		map.put("login", "tomarpoojasingh12@gmail.com"); //Recipient's login id.
		map.put("password", "12");
		map.put("firstName", "Pooja");
		map.put("lastName", "Singh Tomar");
		msg.setTo(map.get("login")); //login id.
		msg.setSubject("Change Password");
		msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully");
		
	}

}
