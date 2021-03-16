package ccf.project.service;


import ccf.project.domain.UserModel;

public interface UserService
{
    UserModel getUserByName(String name);
}
