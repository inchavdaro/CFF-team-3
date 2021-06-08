package ccf.project.service.impl;

import ccf.project.domain.UserModel;
import ccf.project.repository.UserRepository;
import ccf.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserService implements UserDetailsService, UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Boolean insertUser(UserModel userModel) {
        userModel.setPass(encoder.encode(userModel.getPass()));
        userRepository.save(userModel);
        return true;
    }

    @Override
    public Boolean deleteUser(String username) {
        return userRepository.deleteByUsername(username) > 0;
    }

    @Override
    public Boolean changePassword(String username, String password, String newPassword) {
        return userRepository.findByUsername(username).filter(user -> encoder.matches(password,user.getPass()))
                .map(user ->
                {
                    user.setPass(encoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                }).orElse(false);
    }

    @Override
    public Optional<UserModel> getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userModel -> User.builder().username(userModel.getUsername())
                        .password(userModel.getPass())
                        .roles(userModel.getRole().name()).build())
                .orElseThrow(() -> new UsernameNotFoundException("User Name is not Found"));
    }
}
