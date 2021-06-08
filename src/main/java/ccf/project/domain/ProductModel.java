package ccf.project.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @Column(length = 50)
    private String model;

    private String description;

    @NotNull
    private double price;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private BrandModel brand;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private ProductTypeModel productType;

    @OneToMany(mappedBy = "product")
    private Collection<SaleModel> sales;

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return id == that.id   && Double.compare(that.price, price) == 0 && Objects.equals(model, that.model) && Objects.equals(description, that.description) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, description, price, quantity);
    }


    public BrandModel getBrand() {
        return brand;
    }

    public void setBrand(BrandModel brandByBrandId) {
        this.brand = brandByBrandId;
    }


    public ProductTypeModel getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeModel productTypeByTypeId) {
        this.productType = productTypeByTypeId;
    }

    public Collection<SaleModel> getSales() {
        return sales;
    }

    public void setSales(Collection<SaleModel> salesById) {
        this.sales = salesById;
    }
}
