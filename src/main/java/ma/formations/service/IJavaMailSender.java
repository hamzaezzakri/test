package ma.formations.service;

import freemarker.template.TemplateException;
import ma.formations.service.model.Patient;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/30/2022 4:36 PM
 */

public interface IJavaMailSender {

    void sendEmail(Patient patient/*String to, String patient, LocalDate dateVisite, LocalTime heureVisite*/) throws MessagingException, IOException, TemplateException;
}
