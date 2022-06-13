package ma.formations;

import ma.formations.dto.EmpDTO;
import ma.formations.dto.RoleDTO;
import ma.formations.dto.UserDTO;
import ma.formations.service.IEmpService;
import ma.formations.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	@Override
	public void run(String... args) throws Exception {

		userService.cleanDataBase();

		userService.save(new RoleDTO("ADMIN"));
		userService.save(new RoleDTO("CLIENT"));

		RoleDTO roleAdmin = userService.getRoleByName("ADMIN");
		RoleDTO roleClient = userService.getRoleByName("CLIENT");

		UserDTO admin1 = new UserDTO("admin1","admin1", Arrays.asList(roleAdmin));
		UserDTO admin2 = new UserDTO("admin2","admin2", Arrays.asList(roleAdmin));
		UserDTO client1 = new UserDTO("client1","client1", Arrays.asList(roleClient));
		UserDTO client2 = new UserDTO("client2","client2", Arrays.asList(roleClient));

		userService.save(admin1);
		userService.save(admin2);
		userService.save(client1);
		userService.save(client2);

		empService.save(new EmpDTO("emp1",10000d,"Fonction1"));
		empService.save(new EmpDTO("emp2",20000d,"Fonction2"));
		empService.save(new EmpDTO("emp3",30000d,"Fonction3"));
		empService.save(new EmpDTO("emp4",40000d,"Fonction4"));
		empService.save(new EmpDTO("emp5",50000d,"Fonction5"));

		userService.addRoleToUser(client1.getUsername(), roleAdmin.getRole());

	}
}
