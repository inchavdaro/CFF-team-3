package ccf.project.service.impl;

import ccf.project.domain.UserModel;
import ccf.project.repository.UserRepository;
import ccf.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserModel getUserByName(String name)
    {
        return userRepository.findByUsername(name);
    }
}
