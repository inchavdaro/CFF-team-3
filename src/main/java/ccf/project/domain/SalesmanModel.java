package ccf.project.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "salesman")
public class SalesmanModel {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @Column(length = 50)
    private String fullname;

    @Email
    private String email;

    @OneToMany(mappedBy = "salesmanBySalesmanId")
    private Collection<SaleModel> salesById;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private UserModel user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

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

    public Collection<SaleModel> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<SaleModel> salesById) {
        this.salesById = salesById;
    }

    public UserModel getUsersById() {
        return user;
    }

    public void setUsersById(UserModel usersById) {
        this.user = usersById;
    }
}
