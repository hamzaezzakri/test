package ma.formations.controller;

import freemarker.template.TemplateException;
import ma.formations.service.IJavaMailSender;
import ma.formations.service.IPatientService;
import ma.formations.service.model.Patient;
import ma.formations.service.model.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/27/2022 5:23 PM
 */

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IJavaMailSender javaMailSender;

    @GetMapping("/all-patients")
    public ResponseEntity<List<Patient>> getAll(){

        return ResponseEntity.ok(patientService.getAll().stream()
                .filter(p -> p.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @GetMapping("/patient-count")
    public ResponseEntity<Long> getCount(){

        return ResponseEntity.ok(patientService.getAll().stream()
                .filter(p -> p.getIsEnabled())
                .count());
    }

    @PostMapping("/save-patient")
    public ResponseEntity<?> savePatient(@RequestBody @Valid Patient patient) throws MessagingException, IOException, TemplateException {

        if(patient.getRendezVous().size() != 1 || patient.getFactures().size() != 0 || patient.getRendezVous().get(0).getConsultation() != null){

            return ResponseEntity.badRequest().body("veuillez associer un rendez-vous au patient sans consultation");
        }

        if(patientService.existsByDateVisiteAndHeureVisite(patient.getRendezVous().get(0).getDateVisite(),
                patient.getRendezVous().get(0).getHeureVisite()))
            return ResponseEntity.badRequest().body("rendez-vous existe déjà");

        Patient oldPatient = patientService.findByCin(patient.getCin());
        if(oldPatient == null) {

            patientService.addPatient(patient);
            javaMailSender.sendEmail(patient/*patient.getEmail(), patient.getNomPrenom(), patient.getRendezVous().get(0).getDateVisite(),
                    patient.getRendezVous().get(0).getHeureVisite()*/);
            return ResponseEntity.status(HttpStatus.CREATED).body("rendez-vous enregistré avec succès");
        }

        if(oldPatient.getRendezVous().get(oldPatient.getRendezVous().size()-1).getDateVisite().compareTo(patient.getRendezVous().get(0).getDateVisite()) == 0){

            return ResponseEntity.badRequest().body("vous ne pouvez pas prendre plusieurs rendez-vous dans la même journée");
        }

        List<RendezVous> rendezVous = oldPatient.getRendezVous().stream()
                        .filter(rdv -> rdv.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                        .collect(Collectors.toList());

        if(rendezVous.size() >= 1){

            return ResponseEntity.badRequest().body("vous ne pouvez pas réserver plusieurs rendez-vous le même jour");
        }

        patient.setId(oldPatient.getId());
        patient.setCreatedAt(oldPatient.getCreatedAt());
        patient.setIsEnabled(oldPatient.getIsEnabled());
        patientService.addRendezVousToPatient(patient);
        javaMailSender.sendEmail(patient/*patient.getEmail(), patient.getNomPrenom(), patient.getRendezVous().get(0).getDateVisite(),
                patient.getRendezVous().get(0).getHeureVisite()*/);
        return ResponseEntity.status(HttpStatus.CREATED).body("rendez-vous enregistré avec succès");
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<?> updatePatient(@RequestBody @Valid Patient patient, @PathVariable("id") Long idPatient){

        if(patient.getRendezVous().size() != 0 || patient.getFactures().size() != 0){

            return ResponseEntity.badRequest().body("merci de ne pas associer de rendez-vous ou de factures au patient");
        }

        if(!patientService.existsById(idPatient))
            return ResponseEntity.badRequest().body("patient n'existe pas");

        patientService.updatePatient(patient, idPatient);
        return ResponseEntity.ok().body("patient enregistré avec succès");
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long idPatient){

        if(!patientService.existsById(idPatient))
            return ResponseEntity.badRequest().body("patient n'existe pas");

        patientService.deletePatient(idPatient);
        return ResponseEntity.ok().body("patient supprimé avec succès");
    }

    @PostMapping("/save-facture")
    public ResponseEntity<?> saveFacture(@RequestBody @Valid Patient patient){

        if(patient.getFactures().size() != 1 || patient.getRendezVous().size() != 0){

            return ResponseEntity.badRequest().body("veuillez associer une facture au patient");
        }

        Patient oldPatient = patientService.getById(patient.getId());
        if(oldPatient == null) {

            return ResponseEntity.badRequest().body("patient n'existe pas");
        }

        oldPatient.setFactures(patient.getFactures());
        patientService.addFactureToPatient(oldPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body("facture enregistrée avec succès");
    }

}
