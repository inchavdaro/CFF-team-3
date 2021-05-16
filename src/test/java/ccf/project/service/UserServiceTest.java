package ccf.project.service;

import ccf.project.domain.UserModel;
import ccf.project.domain.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void givenUserModelWhenInsertedThenReturnCorrectResult() {
        UserModel u1 = new UserModel();
        u1.setUsername("test");
        u1.setPass("test");
        u1.setRole(UserRole.ADMIN);

        Boolean result = userService.saveUser(u1);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenInsertedUserWhenGetUserThenReturnCorrectResult()
    {
        UserModel u1 = new UserModel();
        u1.setUsername("test");
        u1.setPass("test");
        u1.setRole(UserRole.ADMIN);
        userService.saveUser(u1);

        Optional<UserModel> userOpt = userService.getUserByName(u1.getUsername());

        Assertions.assertTrue(userOpt.isPresent());
        UserModel user = userOpt.get();
        Assertions.assertEquals(u1.getUsername(),user.getUsername());
        Assertions.assertEquals(u1.getRole(),user.getRole());
    }

    @Test
    public void givenInsertedUserWhenDeleteUserThenReturnCorrectResult()
    {
        UserModel u1 = new UserModel();
        u1.setUsername("test");
        u1.setPass("test");
        u1.setRole(UserRole.ADMIN);
        userService.saveUser(u1);

        Boolean result = userService.deleteUser(u1.getUsername());

        Assertions.assertTrue(result);
    }

    @Test
    public void givenInsertedUserWhenChangePasswordThenReturnCorrectResult()
    {
        UserModel u1 = new UserModel();
        u1.setUsername("test");
        u1.setPass("test");
        u1.setRole(UserRole.ADMIN);
        userService.saveUser(u1);

        Boolean result = userService.changePassword(u1.getUsername(), "test", "newPass");

        Assertions.assertTrue(result);
    }
}
