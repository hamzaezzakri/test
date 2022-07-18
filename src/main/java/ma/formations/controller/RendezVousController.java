package ma.formations.controller;

import ma.formations.dao.*;
import ma.formations.service.IPatientService;
import ma.formations.service.IRendezVousService;
import ma.formations.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/21/2022 4:52 PM
 */

@RestController
@RequestMapping("rendez-vous")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RendezVousController {

    @Autowired
    private IRendezVousService rendezVousService;
    @Autowired
    private IPatientService patientService;

    @GetMapping("/all-rendez-vous")
    public ResponseEntity<List<RendezVous>> getAll(){

        return ResponseEntity.ok(rendezVousService.getAll().stream()
                .filter(rdv -> rdv.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @PostMapping("/save-consultation")
    public ResponseEntity<?> saveConsultation(@RequestBody @Valid RendezVous rendezVous){

        if(rendezVous.getConsultation() == null || rendezVous.getConsultation().getOrdonnance() != null){

            return ResponseEntity.badRequest().body("merci d'associer une consultation au rendez-vous sans ordonnance");
        }

        RendezVous oldRendezVous = rendezVousService.getById(rendezVous.getId());
        if(oldRendezVous == null) {

            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");
        }

        if(oldRendezVous.getConsultation() != null){

            return ResponseEntity.badRequest().body("rendez-vous ne peut avoir qu'une seule consultation");
        }

        oldRendezVous.setConsultation(rendezVous.getConsultation());
        rendezVousService.addConsultationToRendezVous(oldRendezVous);
        return ResponseEntity.status(HttpStatus.CREATED).body("consultation enregistrée avec succès");
    }

    @PutMapping("/update-rendez-vous/{id}")
    public ResponseEntity<?> updateRendezVous(@RequestBody @Valid RendezVous rendezVous, @PathVariable("id") Long idRendezVous){

        if(rendezVous.getPatient() != null || rendezVous.getConsultation() != null){

            return ResponseEntity.badRequest().body("merci de ne pas associer un patient ou une consultation au rendez-vous");
        }

        if(!rendezVousService.existsById(idRendezVous))
            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");

        if(patientService.existsByDateVisiteAndHeureVisite(rendezVous.getDateVisite(), rendezVous.getHeureVisite()))
            return ResponseEntity.badRequest().body("rendez-vous existe déjà, veuillez choisir une autre date");

        rendezVousService.updateRendezVous(rendezVous, idRendezVous);
        return ResponseEntity.ok().body("rendez-vous enregistré avec succès");
    }

    @DeleteMapping("/delete-rendez-vous/{id}")
    public ResponseEntity<?> deleteRendezVous(@PathVariable("id") Long idRendezVous){

        if(!rendezVousService.existsById(idRendezVous))
            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");

        rendezVousService.deleteRendezVous(idRendezVous);
        return ResponseEntity.ok().body("rendez-vous supprimé avec succès");
    }








































    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private OrdonnanceRepository ordonnanceRepository;

    @GetMapping("/allPatient")
    public ResponseEntity<List<Patient>>getAllPatients(){
        return ResponseEntity.ok().body(patientRepository.findAll().stream()
                .filter(p -> p.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @GetMapping("/allRdv")
    public ResponseEntity<List<RendezVous>>getAllRdvs(){
        return ResponseEntity.ok().body(rendezVousRepository.findAll().stream()
                .filter(p -> p.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @GetMapping("/allFacture")
    public ResponseEntity<List<Facture>>getAllFactures(){
        return ResponseEntity.ok().body(factureRepository.findAll().stream()
                .filter(f -> f.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @GetMapping("/allConsultation")
    public ResponseEntity<List<Consultation>>getAllConsultationes(){
        return ResponseEntity.ok().body(consultationRepository.findAll().stream()
                .filter(f -> f.getIsEnabled() && f.getRendezVous().getIsEnabled())
                .collect(Collectors.toList()));
    }

    @GetMapping("/allOrdonnance")
    public ResponseEntity<List<Ordonnance>>getAllOrdonnances(){
        return ResponseEntity.ok().body(ordonnanceRepository.findAll().stream()
                .filter(f -> f.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @GetMapping("/getRdvsByDate")
    public ResponseEntity<?>getRdvsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateVisite,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime heureVisite){

        RendezVous rendezVous = rendezVousRepository.findByDateVisiteAndHeureVisite(dateVisite, heureVisite);
        if(rendezVous == null || !rendezVous.getIsEnabled())
            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");

        return ResponseEntity.ok().body(rendezVous);
    }

    @GetMapping("/getRdvsByPatient")
    public ResponseEntity<List<RendezVous>>getRdvsByPatient(@RequestParam String nomPrenom){

        return ResponseEntity.ok().body(patientRepository.findByNomPrenom(nomPrenom).getRendezVous().stream()
                .filter(rdv -> rdv.getIsEnabled())
                .collect(Collectors.toList()));
    }

}
