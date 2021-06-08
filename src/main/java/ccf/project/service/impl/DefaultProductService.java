package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ProductModel;
import ccf.project.domain.ProductTypeModel;
import ccf.project.repository.ProductRepository;
import ccf.project.service.BrandService;
import ccf.project.service.ProductService;
import ccf.project.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DefaultProductService implements ProductService {


    private ProductRepository productRepository;
    private ProductTypeService productTypeService;
    private BrandService brandService;

    private Logger logger = LoggerFactory.getLogger(DefaultProductService.class);

    public DefaultProductService(ProductRepository productRepository, ProductTypeService productTypeService, BrandService brandService) {
        this.productRepository = productRepository;
        this.productTypeService = productTypeService;
        this.brandService = brandService;
    }

    @Override
    public ProductModel insertProduct(String model,String type,String brand,String description,double price) {
        ProductModel toInsert = new ProductModel();
        toInsert.setModel(model);
        toInsert.setProductType(productTypeService.getTypeByName(type).stream().findFirst().orElse(null));
        toInsert.setBrand(brandService.findByName(brand).stream().findFirst().orElse(null));
        toInsert.setDescription(description);
        toInsert.setPrice(price);
        return productRepository.save(toInsert);
    }

    @Override
    public ProductModel insertProduct(ProductModel product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductModel> getProductsByBrand(String brand) {
        BrandModel brandModel = brandService.findByName(brand).orElseThrow(() -> {
            logger.error("No brand with name " + brand + " !");
            return new IllegalArgumentException("No brand with name " + brand + " !");
        });
        return productRepository.findByBrand(brandModel);
    }


    @Override
    public List<ProductModel> getProductsByType(String type) {
        ProductTypeModel productTypeModel = productTypeService.getTypeByName(type).orElseThrow(() -> {
            logger.error("No type with name " + type + " !");
            return new IllegalArgumentException("No type with name " + type + " !");
        });
        return   productRepository.findProductModelByProductType(productTypeModel);
    }

    @Override
    public List<ProductModel> getProductsByBrandAndType(String brand, String type) {
        BrandModel brandModel = brandService.findByName(brand).orElseThrow(() -> {
            logger.error("No brand with name " + brand + " !");
            return new IllegalArgumentException("No brand with name " + brand + " !");
        });

        ProductTypeModel productTypeModel = productTypeService.getTypeByName(type).orElseThrow(() -> {
            logger.error("No type with name " + type + " !");
            return new IllegalArgumentException("No type with name " + type + " !");
        });

        return productRepository.findByBrandAndProductType(brandModel,productTypeModel);
    }

    @Override
    public List<ProductModel> getProductsBelowPrice(double price) {
        return productRepository.findByPriceLessThanEqual(price);
    }

    @Override
    public List<ProductModel> getProductsAbovePrice(double price) { return productRepository.findByPriceGreaterThanEqual(price); }

    @Override
    public Optional<ProductModel> getProductByModel(String model) { return productRepository.findByModel(model); }

    @Override
    public List<ProductModel> deleteProductByModel(String model) {
        return productRepository.deleteByModel(model);
    }

    @Override
    public List<ProductModel> deleteProductsByBrand(String brand) {
        BrandModel brandModel = brandService.findByName(brand).orElseThrow(() -> {
            logger.error("No brand with name " + brand + " !");
            return new IllegalArgumentException("No brand with name " + brand + " !");
        });
        return productRepository.deleteByBrand(brandModel);
    }

    @Override
    public List<ProductModel> deleteProductsByType(String type) {
        ProductTypeModel productTypeModel = productTypeService.getTypeByName(type).orElseThrow(() -> {
            logger.error("No type with name " + type + " !");
            return new IllegalArgumentException("No type with name " + type + " !");
        });
        return productRepository.deleteByProductType(productTypeModel);
    }


}
