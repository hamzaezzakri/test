package ma.formations;

import ma.formations.dao.ConsultationRepository;
import ma.formations.dao.OrdonnanceRepository;
import ma.formations.dao.PatientRepository;
import ma.formations.dao.RendezVousRepository;
import ma.formations.dto.EmpDTO;
import ma.formations.dto.RoleDTO;
import ma.formations.dto.UserDTO;
import ma.formations.service.IEmpService;
import ma.formations.service.IUserService;
import ma.formations.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class ExemplejwtwithspringsecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ExemplejwtwithspringsecurityApplication.class, args);

	}

	@Autowired
	private IUserService userService;
	@Autowired
	private IEmpService empService;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private RendezVousRepository rendezVousRepository;
	@Autowired
	private ConsultationRepository consultationRepository;
	@Autowired
	private OrdonnanceRepository ordonnanceRepository;

	@Override
	public void run(String... args) throws Exception {

		userService.save(new RoleDTO("ADMIN"));
		userService.save(new RoleDTO("CLIENT"));

		RoleDTO roleAdmin = userService.getRoleByName("ADMIN");
		RoleDTO roleClient = userService.getRoleByName("CLIENT");

		UserDTO admin1 = new UserDTO("fbriere@gmail.com","admin","admin", Arrays.asList(roleAdmin));
		UserDTO admin2 = new UserDTO("mhassel@live.com","admin2","admin2", Arrays.asList(roleAdmin));
		UserDTO client1 = new UserDTO("vmalik@outlook.com","client1","client1", Arrays.asList(roleClient));
		UserDTO client2 = new UserDTO("nakia10@gmail.com","client2","client2", Arrays.asList(roleClient));

		userService.save(admin1);
		userService.save(admin2);
		userService.save(client1);
		userService.save(client2);

		empService.save(new EmpDTO("emp1",10000d,"Fonction1"));
		empService.save(new EmpDTO("emp2",20000d,"Fonction2"));
		empService.save(new EmpDTO("emp3",30000d,"Fonction3"));
		empService.save(new EmpDTO("emp4",40000d,"Fonction4"));
		empService.save(new EmpDTO("emp5",50000d,"Fonction5"));

		UserDTO userDTO = userService.addRoleToUser(client1.getUsername(), roleAdmin.getRole());

		System.out.println(userDTO);

		RendezVous rendezVous = new RendezVous(LocalDate.now().plusDays(3), LocalTime.of(16,00),
				Etat.EN_ATTENTE,"Prendre rendez-vous chez le médecin",
				LocalDateTime.now(),true);

		Facture facture = new Facture(12000,LocalDateTime.now(),true);

		Patient patient = new Patient("1288vg","patient p1",
				Sexe.HOMME,LocalDate.now().minusYears(24),"patient1@gmail.com",
				"casa","064725343", LocalDateTime.now(),true, Arrays.asList(rendezVous));

		rendezVous.setPatient(patient);
		facture.setPatient(patient);
		//patient.getRendezVous().add(rendezVous);
		patient.getFactures().add(facture);
		System.out.println(patient.getRendezVous().get(0).getDateVisite() + " " + patient.getRendezVous().get(0).getHeureVisite());
		patientRepository.save(patient);

		RendezVous rendezVous1 = new RendezVous(LocalDate.now().plusDays(6), LocalTime.of(12,00),
				Etat.EN_ATTENTE,"Prendre rendez-vous chez le médecin",
				LocalDateTime.now(),true);

		Patient patient1 = new Patient("BJ475639","patient p2",
				Sexe.FEMME,LocalDate.now().minusYears(10),"patient2@gmail.com",
				"tanger","0755325298", LocalDateTime.now(),true, Arrays.asList(rendezVous1));


		rendezVous1.setPatient(patient1);
		//patient1.getRendezVous().add(rendezVous1);
		patientRepository.save(patient1);

		Patient patient2 = patientRepository.findByNomPrenom("patient p1");
		//patient2.setEnabled(false);
		patient2.getRendezVous().stream().forEach(rdv -> rdv.setEtat(Etat.FAIT));
		patientRepository.save(patient2);

		Consultation consultation = new Consultation("consultation 1", LocalDateTime.now(),true);
		RendezVous rendezVous2 = rendezVousRepository.findByEtat(Etat.FAIT);
		consultation.setRendezVous(rendezVous2);
		rendezVous2.setConsultation(consultation);
		rendezVousRepository.save(rendezVous2);

		Ordonnance ordonnance = new Ordonnance("ordonnance 1", LocalDateTime.now(),true);
		Consultation consultation1 = consultationRepository.findById(17L).get();
		ordonnance.setConsultation(consultation1);
		consultation1.setOrdonnance(ordonnance);
		consultationRepository.save(consultation1);

	}
}
