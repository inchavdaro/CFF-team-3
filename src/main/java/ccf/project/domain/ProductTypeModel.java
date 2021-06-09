package ccf.project.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product_type")
public class ProductTypeModel {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String type;

    @OneToMany(mappedBy = "productType")
    private Collection<ProductModel> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTypeModel that = (ProductTypeModel) o;

        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }


    public Collection<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductModel> productsById) {
        this.products = productsById;
    }
}
