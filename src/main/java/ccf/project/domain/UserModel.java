package ccf.project.domain;

import ccf.project.domain.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue
    private int id;

    @Size(max = 20)
    @Column(length = 20)
    private String username;

    @Size(min=4)
    @Column(length = 30)
    private String pass;

    private UserRole role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserRole getRole()
    {
        return role;
    }

    public void setRole(UserRole role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && role == userModel.role && Objects.equals(username, userModel.username) && Objects.equals(pass, userModel.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, pass,  role);
    }
}
