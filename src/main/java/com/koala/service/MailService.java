package com.koala.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	String smtp_server = "smtp.163.com";
	String smtp_account = "15220061238@163.com";
	String smtp_password = "hjf0419";

	public void send(String toEmail, String toName, String fromName, String subject, String htmlbody) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject(subject);
				helper.setFrom(smtp_account, fromName);
				helper.setTo(toEmail);

				helper.setText(htmlbody, true);

				// Additionally, let's add a resource as an attachment as well.
				//helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));

			}
		};

		try {
			javaMailSender.send(preparator);
			System.out.println("Message has been sent.............................");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
