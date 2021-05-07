package ccf.project;

import ccf.project.domain.UserModel;
import ccf.project.domain.enums.UserRole;
import ccf.project.repository.UserRepository;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrmApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {


		int j = 0;

		UserModel u1 = new UserModel();
		u1.setUsername("Ivan");
		u1.setPass("admin");
		u1.setRole(UserRole.ADMIN);
		userRepository.save(u1);
		UserModel ivan = userRepository.findByUsername("Ivan");
		Assertions.assertNotNull(ivan);
		Assertions.assertEquals(ivan.getPass(), "admin");

		int i = 0;
	}

}
