package ccf.project.service.impl;

import ccf.project.domain.UserModel;
import ccf.project.domain.enums.UserRole;
import ccf.project.repository.UserRepository;
import ccf.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserDetailsService, UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    private void postConstruct() {
        UserModel admin = new UserModel();
        admin.setRole(UserRole.ADMIN);
        admin.setEmail("admin@place.holder");
        admin.setPass("admin");
        UserModel normalUser = new UserModel();
        normalUser.setRole(UserRole.SALESMAN);
        normalUser.setEmail("salesman@place.holder");
        normalUser.setPass("salesman");
        userRepository.save(admin);
        System.out.println("ADDED ADMIN with pass:" + admin.getPass() + " and email:" + admin.getEmail() + "!!");
        userRepository.save(normalUser);
        System.out.println("ADDED SALESMAN with pass:" + normalUser.getPass() + " and email:" + normalUser.getEmail() + "!!");


    }
    @Override
    public Boolean insertUser(UserModel userModel) {
        userModel.setPass(encoder.encode(userModel.getPass()));
        userRepository.save(userModel);
        return true;
    }

    @Override
    public Boolean deleteUser(String username) {
        return userRepository.deleteByEmail(username) > 0;
    }

    @Override
    public Boolean changePassword(String username, String password, String newPassword) {
        return userRepository.findByEmail(username).filter(user -> encoder.matches(password, user.getPass()))
                .map(user ->
                {
                    user.setPass(encoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                }).orElse(false);
    }

    @Override
    public Optional<UserModel> getUserByName(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public List<String> getAllAdminEmails() {
        return userRepository.findByRole(UserRole.ADMIN).stream().map(UserModel::getEmail).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllSalesmanEmails() {
        return userRepository.findByRole(UserRole.SALESMAN).stream().map(UserModel::getEmail).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(userModel -> User.builder().username(userModel.getEmail())
                        .password(userModel.getPass())
                        .roles(userModel.getRole().name()).build())
                .orElseThrow(() -> new UsernameNotFoundException("User Name is not Found"));
    }
}
