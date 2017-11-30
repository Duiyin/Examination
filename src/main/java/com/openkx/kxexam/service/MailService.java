package com.openkx.kxexam.service;

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
	
	String smtp_server = "smtp.qq.com";
	String smtp_account = "865079657@qq.com";
	String smtp_password = "vticpklerwadbdhe";

	public void send(String toEmail, String toName, String fromName, String subject, String htmlbody) {

		/*try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName(smtp_server);
			email.setAuthentication(smtp_account, smtp_password);
			email.addTo(toEmail, toName);
			email.setFrom(smtp_account, fromName);
			email.setSubject(subject);
			System.out.println(htmlbody);
			email.setHtmlMsg(htmlbody);
			String status = email.send();
			System.out.println("=======sendmail============="+status+"=================");
		} catch (EmailException e) {
			e.printStackTrace();
		}*/

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
