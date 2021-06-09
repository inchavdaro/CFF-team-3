package ccf.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "client")
public class ClientModel {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @Column(length = 30)
    private String name;

    @NotEmpty
    @Column(length = 30)
    private String address;

    @NotEmpty
    @Column(length = 30, unique = true)
    private String bulstat;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Collection<SaleModel> sales;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientModel that = (ClientModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(bulstat, that.bulstat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, bulstat);
    }

    public Collection<SaleModel> getSales() {
        return sales;
    }

    public void setSales(Collection<SaleModel> salesById) {
        this.sales = salesById;
    }
}
