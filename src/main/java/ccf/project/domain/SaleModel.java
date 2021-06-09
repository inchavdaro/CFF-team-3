package ccf.project.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sale")
public class SaleModel {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private Timestamp date;

    @NotNull
    private Double salePrice;

    @NotNull
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "salesman_id", referencedColumnName = "id", nullable = false)
    private SalesmanModel salesman;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientModel client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleModel saleModel = (SaleModel) o;
        return id == saleModel.id  && Objects.equals(date, saleModel.date) && Objects.equals(salePrice, saleModel.salePrice) && Objects.equals(quantity, saleModel.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, salePrice, quantity);
    }

    public SalesmanModel getSalesmanBySalesmanId() {
        return salesman;
    }

    public void setSalesmanBySalesmanId(SalesmanModel salesmanBySalesmanId) {
        this.salesman = salesmanBySalesmanId;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel productByProductId) {
        this.product = productByProductId;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }
}
