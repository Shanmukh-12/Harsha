package main.bll.login;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.models.loginModel.inputModels.MailDetails;
import main.service.procurement.ProcurementService;

@Component
public class SendingEmailBLL {
	@Autowired
	RandomNumberBLL rn;
	@Autowired
	ProcurementService x;

	public void sendEmail(MailDetails md) throws AddressException, MessagingException {
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp-mail.outlook.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");

		Session sess = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("devarmadd5@hotmail.com", "my mother43");
			}

		});
		prop.put("mail.smtp.ssl.trust", "*");
		sess.setDebug(true);
		int number = rn.getNumber();

		Message msg = new MimeMessage(sess);
		msg.setFrom(new InternetAddress("devarmadd5@hotmail.com"));

		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(md.getMail()));
		msg.setSubject("password reset");
		msg.setText("your otp is :" + number);
		Transport.send(msg);
		x.getDat(md, String.valueOf(number));

	}

}
