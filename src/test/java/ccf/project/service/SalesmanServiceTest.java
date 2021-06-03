package ccf.project.service;

import ccf.project.domain.SalesmanModel;
import ccf.project.domain.UserModel;
import ccf.project.domain.enums.UserRole;
import ccf.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
public class SalesmanServiceTest {

    @Autowired
    SalesmanService salesmanService;

    @Autowired
    UserRepository userRepository;

    UserModel salesmanUser;

    @BeforeEach
    void setup() {
        UserModel userModel = new UserModel();
        userModel.setUsername("test");
        userModel.setPass("test");
        userModel.setRole(UserRole.SALESMAN);

        salesmanUser = userRepository.save(userModel);
    }

    @Test
    void givenSalesmanModelWhenCreateSalesmanThenGetCorrectResult() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setFullname("Test Test");
        salesmanModel.setEmail("test@test.com");
        salesmanModel.setUser(salesmanUser);

        SalesmanModel created = salesmanService.createSalesman(salesmanModel);

        Assertions.assertNotNull(created);
    }

    @Test
    void givenInsertedSalesmanWhenGetByIdThenGetCorrectResult() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setFullname("Test Test");
        salesmanModel.setEmail("test@test.com");
        salesmanModel.setUser(salesmanUser);

        SalesmanModel created = salesmanService.createSalesman(salesmanModel);
        Optional<SalesmanModel> salesmanOpt = salesmanService.getById(created.getId());

        Assertions.assertTrue(salesmanOpt.isPresent());
        Assertions.assertEquals(created,salesmanOpt.get());
    }

    @Test
    void givenInsertedSalesmanWhenGetByUserIdThenGetCorrectResult() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setFullname("Test Test");
        salesmanModel.setEmail("test@test.com");
        salesmanModel.setUser(salesmanUser);

        SalesmanModel created = salesmanService.createSalesman(salesmanModel);
        Optional<SalesmanModel> salesmanOpt = salesmanService.getByUserId(salesmanUser.getId());

        Assertions.assertTrue(salesmanOpt.isPresent());
        Assertions.assertEquals(created,salesmanOpt.get());
    }

    @Test
    void givenInsertedSalesmanWhenGetAllThenGetCorrectResult() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setFullname("Test Test");
        salesmanModel.setEmail("test@test.com");
        salesmanModel.setUser(salesmanUser);

        SalesmanModel created = salesmanService.createSalesman(salesmanModel);
        Page<SalesmanModel> salesmanOpt = salesmanService.getAll();

        Assertions.assertFalse(salesmanOpt.isEmpty());
        Assertions.assertEquals(created,salesmanOpt.iterator().next());
    }

    @Test
    void givenInsertedSalesmanWhenGetByPageThenGetCorrectResult() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setFullname("Test Test");
        salesmanModel.setEmail("test@test.com");
        salesmanModel.setUser(salesmanUser);

        SalesmanModel created = salesmanService.createSalesman(salesmanModel);
        Page<SalesmanModel> salesmanOpt = salesmanService.getAll(0,10);

        Assertions.assertFalse(salesmanOpt.isEmpty());
        Assertions.assertEquals(created,salesmanOpt.iterator().next());
    }

}
