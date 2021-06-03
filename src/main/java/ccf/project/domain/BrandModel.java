package ccf.project.domain;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "brand")
public class BrandModel {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @Column(length = 30, unique = true)
    private String name;

    @OneToMany(mappedBy = "brandByBrandId")
    private Collection<ProductModel> productsById;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandModel that = (BrandModel) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<ProductModel> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductModel> productsById) {
        this.productsById = productsById;
    }
}
