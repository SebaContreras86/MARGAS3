/**
 * 
 */
package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Seba
 *
 */
public class Emailer {

	public void sendMail(String mensaje, String nombreProveedor) throws AddressException, MessagingException {
		String remitente = "punk_now77@hotmail.com";
		String password = "4555396*";

		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.ssl.trust", "smtp-mail.outlook.com");
		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port", "587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", remitente);

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		// Quien envia el correo
		message.setFrom(new InternetAddress(remitente));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(nombreProveedor));

		message.setSubject("Hola");
		message.setText(mensaje);

		Transport t = session.getTransport("smtp");
		t.connect(remitente, password);
		t.sendMessage(message, message.getAllRecipients());
		t.close();
	}
}
