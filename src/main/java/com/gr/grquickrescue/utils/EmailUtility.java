package com.gr.grquickrescue.utils;

import java.util.Properties;

import javax.ejb.Asynchronous;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@Asynchronous
public class EmailUtility {
	public static void sendAlertEmail(String recipient, String Address) {
		final String username = "ibrahimkhan1122@gmail.com";
		final String password = "1khurasan90";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username,password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ibrahimkhan1122@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("Alert Prfile Activation");
			message.setText("Hi Dear,"
				+ "\n\n Your Email Alert Profile has been activated for "+ Address
				+ "\n\n For any Queries contact at help@quickrescue.com  "
				+ "\n\n Thank You");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}