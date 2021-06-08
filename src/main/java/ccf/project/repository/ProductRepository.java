package ccf.project.repository;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ProductModel;
import ccf.project.domain.ProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    Optional<ProductModel> findByModel(String model);
    List<ProductModel> findAll();
    List<ProductModel> findProductModelByProductType(ProductTypeModel type);
    List<ProductModel> findByBrand(BrandModel brand);
    List<ProductModel> findByBrandAndProductType( BrandModel brand, ProductTypeModel type);
    List<ProductModel> findByPriceLessThanEqual(Double price);
    List<ProductModel> findByPriceGreaterThanEqual(Double price);


    List<ProductModel> deleteByBrand(BrandModel type);
    List<ProductModel> deleteByProductType(ProductTypeModel type);
   List<ProductModel> deleteByModel(String type);

}

