package ccf.project;

import ccf.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrmApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {

	}

}
