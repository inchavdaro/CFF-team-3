package ccf.project.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sale", schema = "test1")
public class SaleModel {
    private int id;
//    private int salesmanId;
//    private int productId;
//    private int clientId;
    private Timestamp date;
    private Double salePrice;
    private Integer quantity;
    private SalesmanModel salesmanBySalesmanId;
    private ProductModel productByProductId;
    private ClientModel clientByClientId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "salesman_id")
//    public int getSalesmanId() {
//        return salesmanId;
//    }
//
//    public void setSalesmanId(int salesmanId) {
//        this.salesmanId = salesmanId;
//    }
//
//    @Basic
//    @Column(name = "product_id")
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    @Basic
//    @Column(name = "client_id")
//    public int getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(int clientId) {
//        this.clientId = clientId;
//    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "sale_price")
    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    @Basic
    @Column(name = "quantity")
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

    @ManyToOne
    @JoinColumn(name = "salesman_id", referencedColumnName = "id", nullable = false)
    public SalesmanModel getSalesmanBySalesmanId() {
        return salesmanBySalesmanId;
    }

    public void setSalesmanBySalesmanId(SalesmanModel salesmanBySalesmanId) {
        this.salesmanBySalesmanId = salesmanBySalesmanId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public ProductModel getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductModel productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public ClientModel getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientModel clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
