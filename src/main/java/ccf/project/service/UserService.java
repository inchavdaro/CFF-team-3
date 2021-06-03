package ccf.project.service;


import ccf.project.domain.UserModel;

import java.util.Optional;

public interface UserService
{
    Boolean saveUser(UserModel userModel);

    Boolean deleteUser(String username);

    Boolean changePassword(String username, String password, String newPassword);

    Optional<UserModel> getUserByName(String username);
}
