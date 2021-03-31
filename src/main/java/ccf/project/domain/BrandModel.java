package ccf.project.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "brand", schema = "test1")
public class BrandModel {

    @Id
    @GeneratedValue
    private long id;
    private String brand;
    private Collection<ProductModel> productsById;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, brand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandModel that = (BrandModel) o;
        return id == that.id && Objects.equals(brand, that.brand);
    }

    @OneToMany(mappedBy = "brandByBrandId")
    public Collection<ProductModel> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductModel> productsById) {
        this.productsById = productsById;
    }
}
