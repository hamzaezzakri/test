package ma.formations;

import ma.formations.dao.RoleRepository;
import ma.formations.service.model.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ExemplejwtwithspringsecurityApplicationTests {

	@Autowired
	private RoleRepository roleRepository;
	@Value("${app.personal}")
	private String personal;

	@Test
	public void creationTest(){

		Role role = new Role();
		role.setRole("USER");
		roleRepository.save(role);
		assertNotNull(roleRepository.findByRole("USER"));
	}

	@Test
	public void simpleTest(){

		assertEquals("Mtiste",personal);
		Assertions.assertThat(roleRepository.findById(1).get().getRole()).isEqualTo("ADMIN");
		Assertions.assertThat(roleRepository.existsById(12)).isFalse();
	}

	@Test
	public void getallRolesTest(){

		Assertions.assertThat(roleRepository.findAll()).size().isGreaterThan(0);
	}

}
