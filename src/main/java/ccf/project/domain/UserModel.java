package ccf.project.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "test1")
public class UserModel {
    private int id;
    private String username;
    private String pass;
   // private Integer salesmanId;
    private int role;
    private SalesmanModel salesmanBySalesmanId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    @Basic
//    @Column(name = "salesman_id")
//    public Integer getSalesmanId() {
//        return salesmanId;
//    }
//
//    public void setSalesmanId(Integer salesmanId) {
//        this.salesmanId = salesmanId;
//    }

    @Basic
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
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

    @ManyToOne
    @JoinColumn(name = "salesman_id", referencedColumnName = "id")
    public SalesmanModel getSalesmanBySalesmanId() {
        return salesmanBySalesmanId;
    }

    public void setSalesmanBySalesmanId(SalesmanModel salesmanBySalesmanId) {
        this.salesmanBySalesmanId = salesmanBySalesmanId;
    }
}
