package ccf.project.controller;

import ccf.project.domain.UserModel;
import ccf.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @PreAuthorize("#username == authentication.principal.username")
    @PutMapping
    public ResponseEntity<Boolean> changePassword(@RequestParam String username,
                                                  @RequestParam String password,
                                                  @RequestParam String newPassword) {
        return ResponseEntity.ok(userService.changePassword(username, password, newPassword));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{username}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUser(username));
    }
}
