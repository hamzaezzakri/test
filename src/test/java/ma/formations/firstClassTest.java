package ma.formations;

import ma.formations.dao.RoleRepository;
import ma.formations.dao.UserRepository;
import ma.formations.service.IUserService;
import ma.formations.service.model.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 7/16/2022 11:14 PM
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class firstClassTest {

    private String personal = "Mtiste";

    //this method must be static if the class is not annotated with @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @BeforeAll
    public void beforeAll(){

        System.out.println("this should be executed before all tests");
    }

    @BeforeEach
    public void beforeEach(){

        System.out.println("this should be executed before each test");
    }

    @Test
    @DisplayName("this should work")
    public void work(){

        Assertions.assertEquals("Mtiste",personal);
    }

    @AfterEach
    public void afterEach(){

        System.out.println("this should be executed after each test");
    }

    @AfterAll
    public void afterAll(){

        System.out.println("this should be executed after all tests");
    }
}