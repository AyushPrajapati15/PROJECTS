package com.jsp.medimart.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.jsp.medimart.Model.Drug;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.Model.Orders;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MedimartMailSender {

	@Autowired
	JavaMailSender javaMailSender;

	private final static String fromemail = "aayushprajapati867@gmail.com";

	public void requestEnableMember(Member member) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			// SimpleMailMessage messages = new SimpleMailMessage();
			helper.setFrom(fromemail);
			helper.setTo(member.getEmailid());
			helper.setSubject("Welcome to Medikart! Registration Successful");

			// String mailBody = String.format("Dear %s,%n%n" + "I hope this email finds you
			// well.%n%n"
			// + "Congratulations on successfully registering for the Medikart app! We are
			// thrilled to have you onboard and are excited to be a part of your healthcare
			// journey.%n%n"
			// + "With Medikart, you can now enjoy a seamless and reliable platform for all
			// your pharmaceutical needs. Whether you're ordering medicines, tracking
			// prescriptions, or exploring health-related products, we're here to make the
			// process as smooth as possible.%n%n"
			// + "Your registration details are as follows:%n%n" + "ID: %s%n" + "Name: %s%n"
			// + "Gender: %s%n"
			// + "Date of Birth: %s%n" + "Email: %s%n" + "Mobile: %s%n" + "Address: %s%n%n"
			// + "Please do not hesitate to reach out if you have any questions or need
			// assistance navigating the app. Our customer support team is always ready to
			// help.%n%n"
			// + "We wish you a great experience with Medikart and look forward to serving
			// you in the future!%n%n"
			// + "Best regards,%n" + "Ayush Prajapati%n" + "Medikart Team",
			// member.getName(), member.getId(),
			// member.getName(), member.getGender(), member.getDob(), member.getEmailid(),
			// member.getMobile(),
			// member.getAddress());

			String mailBody = String.format(
					"<html>" +
							"<body>" +
							"<p>Dear %s,</p>" +
							"<p>I hope this email finds you well.</p>" +
							"<p>Congratulations on successfully registering for the Medikart app! We are thrilled to have you onboard and are excited to be a part of your healthcare journey.</p>"
							+
							"<p>Your registration details are as follows:</p>" +
							"<table border='1' cellpadding='10' cellspacing='0'>" + // Table with border and padding
							"<tr><td><b>ID</b></td><td>%s</td></tr>" +
							"<tr><td><b>Name</b></td><td>%s</td></tr>" +
							"<tr><td><b>Gender</b></td><td>%s</td></tr>" +
							"<tr><td><b>Date of Birth</b></td><td>%s</td></tr>" +
							"<tr><td><b>Email</b></td><td>%s</td></tr>" +
							"<tr><td><b>Mobile</b></td><td>%s</td></tr>" +
							"<tr><td><b>Address</b></td><td>%s</td></tr>" +
							"</table>" +
							"<p>Please do not hesitate to reach out if you have any questions or need assistance navigating the app. Our customer support team is always ready to help.</p>"
							+
							"<p>We wish you a great experience with Medikart and look forward to serving you in the future!</p>"
							+
							"<p>Best regards,</p>" +
							"<p>Ayush Prajapati</p>" +
							"<p>Medikart Team</p>" +
							"<img src='cid:imageIdentifier' alt='Medikart Logo'/>" + // Embed image
							"</body>" +
							"</html>",
					member.getName(), member.getId(), member.getName(), member.getGender(),
					member.getDob(), member.getEmailid(), member.getMobile(), member.getAddress());

			helper.setText(mailBody, true);
			// FileSystemResource file = new FileSystemResource(new
			// File("src/main/resources/templates/Ferrari.jpg"));
			// helper.addAttachment("src/main/resources/templates/medi.jpeg", file);
			ClassPathResource image = new ClassPathResource("templates/medi.jpeg");

			helper.addInline("imageIdentifier", image);

			javaMailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// MAIL FOR PLACING ORDER
	public void orderPlaced(String email, String memberName, Orders orders) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(fromemail);
			helper.setTo(email);
			helper.setSubject("Order placed successfully");

			String mailBody = String.format(
					"<html>" +
							"<body>" +
							"<p>Dear %s,</p>" +
							"<p>Thank you for shopping with Medikart. We are pleased to inform you that your order has been placed successfully!</p>"
							+
							"<p>Your order details are as follows:</p>" +
							"<table border='1' cellpadding='10' cellspacing='0'>" +
							"<tr><th>Item</th><th>Quantity</th><th>Price</th></tr>",
					memberName);

			for (Drug drug : orders.getDrug()) {
				mailBody += String.format(
						"<tr><td>%s</td><td>%d</td><td>₹%.2f</td></tr>",
						drug.getName(), 1, drug.getPrice());
			}

			mailBody += String.format(
					"<tr><td colspan='2'><b>Total Price</b></td><td><b>₹%.2f</b></td></tr>" + // Total price row
							"</table>" +
							"<p>We hope you enjoy your purchase. Your order will be shipped soon, and we’ll keep you updated on its progress.</p>"
							+
							"<p>If you have any questions, feel free to contact our support team.</p>" +
							"<p>Best regards,</p>" +
							"<p>Ayush Prajapati</p>" +
							"<p>Medikart Team</p>" +
							"<img src='cid:imageIdentifier' alt='Medikart Logo'/>" + // Embed image
							"</body>" +
							"</html>",
					orders.getOrderamount() // Total order amount
			);

			helper.setText(mailBody, true);
			ClassPathResource image = new ClassPathResource("templates/medi.jpeg");
			helper.addInline("imageIdentifier", image);
			javaMailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
