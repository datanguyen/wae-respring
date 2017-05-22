package wae.thesis.indiv.api.util;

import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ErrorCode;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Nguyen Tan Dat.
 */
public class MailService {
    private static final String REPLY_TO = "no_reply@waethesis.com";
    private static final String SUB_SUBJECT = "WAE E-Commerce Application";
    private static final String HOST_ADDRESS = "ecommercebook2016@gmail.com";
    private static final String HOST_PASSWORD = "3c0mm3rc3b00k2k16";

    public static void transportMail(Session session, String toMail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(REPLY_TO, SUB_SUBJECT));
            msg.setReplyTo(InternetAddress.parse(REPLY_TO, false));

            msg.setSubject(subject, "UTF-8");
            msg.setText(body);
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false));

            Transport.send(msg);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new CoreException("SEND_MAIL_FAILED", ErrorCode.CORE_EXCEPTION);
        }
    }

    public static void sendMail(final String recipientAddress, String content) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HOST_ADDRESS, HOST_PASSWORD);
            }
        };

        Session session = Session.getDefaultInstance(props, authenticator);
        MailService.transportMail(session, recipientAddress, SUB_SUBJECT, content);
    }
}
