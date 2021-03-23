package ccf.project.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "test1")
public class ProductModel {
    private int id;
    //private int brandId;
    private String model;
    //private int typeId;
    private String description;
    private double price;
    private Long quantity;
    private BrandModel brandByBrandId;
    private ProductTypeModel productTypeByTypeId;
    private Collection<SaleModel> salesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "brand_id")
//    public int getBrandId() {
//        return brandId;
//    }

//    public void setBrandId(int brandId) {
//        this.brandId = brandId;
//    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

//    @Basic
//    @Column(name = "type_id")
//    public int getTypeId() {
//        return typeId;
//    }
//
//    public void setTypeId(int typeId) {
//        this.typeId = typeId;
//    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity")
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

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    public BrandModel getBrandByBrandId() {
        return brandByBrandId;
    }

    public void setBrandByBrandId(BrandModel brandByBrandId) {
        this.brandByBrandId = brandByBrandId;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public ProductTypeModel getProductTypeByTypeId() {
        return productTypeByTypeId;
    }

    public void setProductTypeByTypeId(ProductTypeModel productTypeByTypeId) {
        this.productTypeByTypeId = productTypeByTypeId;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<SaleModel> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<SaleModel> salesById) {
        this.salesById = salesById;
    }
}
