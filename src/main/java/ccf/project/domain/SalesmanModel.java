package ccf.project.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "salesman", schema = "test1")
public class SalesmanModel {
    private int id;
    private String fullname;
    private String email;
    private Collection<SaleModel> salesById;
    private Collection<UserModel> usersById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesmanModel that = (SalesmanModel) o;
        return id == that.id && Objects.equals(fullname, that.fullname) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, email);
    }

    @OneToMany(mappedBy = "salesmanBySalesmanId")
    public Collection<SaleModel> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<SaleModel> salesById) {
        this.salesById = salesById;
    }

    @OneToMany(mappedBy = "salesmanBySalesmanId")
    public Collection<UserModel> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UserModel> usersById) {
        this.usersById = usersById;
    }
}
