package ccf.project.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
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

    @OneToMany(mappedBy = "salesman")
    private List<SaleModel> sales;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
        return user.getEmail();
    }

    public List<SaleModel> getSales() {
        return sales;
    }

    public void setSales(List<SaleModel> salesById) {
        this.sales = salesById;
    }

    public int getUserId() {
        return user.getId();
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
