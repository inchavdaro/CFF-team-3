package ccf.project;

import ccf.project.domain.UserModel;
import ccf.project.domain.enums.UserRole;
import ccf.project.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrmApplication {


    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }



}
