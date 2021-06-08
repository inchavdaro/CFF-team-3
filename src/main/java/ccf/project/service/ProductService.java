package ccf.project.service;

import ccf.project.domain.ProductModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    ProductModel insertProduct(String model,String type,String brand,String description,double price );
    ProductModel insertProduct(ProductModel product);

    List<ProductModel> getAllProducts();
    List<ProductModel> getProductsByBrand(String name);
    List<ProductModel> getProductsByType(String name);
    List<ProductModel> getProductsByBrandAndType(String brand, String type);
    List<ProductModel> getProductsBelowPrice(double price);
    List<ProductModel> getProductsAbovePrice(double price);
    Optional<ProductModel> getProductByModel(String name);

    List<ProductModel> deleteProductByModel(String name);
    List<ProductModel> deleteProductsByBrand(String name);
    List<ProductModel> deleteProductsByType(String name);
}
