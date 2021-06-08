package ccf.project.controller;

import ccf.project.domain.UserModel;
import ccf.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Boolean> createUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @PutMapping
    public ResponseEntity<Boolean> changePassword(@RequestParam String username,
                                                  @RequestParam String password,
                                                  @RequestParam String newPassword) {
        return ResponseEntity.ok(userService.changePassword(username, password, newPassword));
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUser(username));
    }
}
