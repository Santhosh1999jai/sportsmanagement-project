package com.example.demo.security.iml;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
public class EmailServiceImp extends HttpServlet {

	private static final long serialVersionUID = 195843178673673693L;

	private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImp.class);

	@Value("${email.host}")
	private String host;

	@Value("${email.port}")
	private Integer port;

	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;

	@Value("${spring.mail.transport.protocol}")
	private String transportProtocol;

	@Value("${env}")
	private String env;

	// Supply your SMTP credentials below. Note that your SMTP credentials are
	// different from your AWS credentials.
	@Value("${smtp.username}")
	private String SMTP_USERNAME;

	@Value("${smtp.password}")
	private String SMTP_PASSWORD;

	public boolean sendEmailDEV(String output, String subject, String output2) {
		try {
			LOG.info("In  sendEmailDEV  START: " + env);

			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo("testing email!.....");
			mail.setFrom(username);
			mail.setSubject(subject);
			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.trust", host);

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			InternetAddress[] toAddresses = { new InternetAddress(output) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setText(output2);
			// set plain text message
			msg.setContent(" welcome to Sports management", "text/html");
			// sends the e-mail
			LOG.info("Attempting to send an email");

			Transport.send(msg);

			LOG.info("Email sent successfully !");
			LOG.info("In  sendEmailDEV  END: " + env);
			return true;
		} catch (MailException e) {
			LOG.info("Problem in sending mail sendEmailDEV  END: " + env);
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			LOG.info("Problem in sending mail sendEmailDEV  END: " + env);
			e.printStackTrace();
		}
		return false;
	}

}
