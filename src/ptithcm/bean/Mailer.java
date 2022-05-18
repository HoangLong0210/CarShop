package ptithcm.bean;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

					//Service/Component tự khai báo bean mà không cần khai báo vào file cấu hình
					// nhưng cần khai báo package chứa bean
@Service("mailer")	//<context:component-scan base-package="ptithcm.controller,ptithcm.bean"/>
public class Mailer {
	@Autowired
	JavaMailSender mailer;
	
	public void send(String from, String to, String subject, String body) {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailer.send(mail);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
