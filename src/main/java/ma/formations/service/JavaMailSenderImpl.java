package ma.formations.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import ma.formations.service.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/30/2022 4:36 PM
 */

@Service
public class JavaMailSenderImpl implements IJavaMailSender{

    @Autowired
    private Configuration configuration;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${app.email}")
    private String from;
    @Value("${app.personal}")
    private String personal;

    @Override
    public void sendEmail(Patient patient/*String to, String patient, LocalDate dateVisite, LocalTime heureVisite*/)
            throws MessagingException, IOException, TemplateException {

        /*String date = dateVisite.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String time = heureVisite.format(DateTimeFormatter.ofPattern("HH:mm"));*/

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

       /* String content = "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;\">Cher(e) " + patient + ",</p>" +
                "<p></p>" +
                "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size:17px;\">Nous vous confirmons la réservation pour tous types de consultations médicales.</p>" +
                "<p></p>" +
                "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size:17px;\">Votre rendez-vous aura lieu le " + date + " à " + time + ".</p>" +
                "<p></p>" +
                "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size:17px;\">Merci de votre confiance.</p>" +
                "<p></p>" +
                "<p></p>" +
                "<hr><img src='cid:logo' width=\"80\" height=\"80\"/>";*/
        helper.setFrom(from,personal);
        helper.setTo(patient.getEmail());
        helper.setSubject("e-mail de confirmation de rendez vous");
        helper.setText(getEmailContent(patient),true);

        /*ClassPathResource resource = new ClassPathResource("/static/images/mtiste.png");
        helper.addInline("logo",resource);*/

        mailSender.send(message);
    }

    String getEmailContent(Patient patient) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", patient);
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
