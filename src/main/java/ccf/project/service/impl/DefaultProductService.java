package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ProductModel;
import ccf.project.domain.ProductTypeModel;
import ccf.project.domain.dtos.ProductData;
import ccf.project.repository.ProductRepository;
import ccf.project.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service

public class DefaultProductService implements ProductService {

  private Logger logger = LoggerFactory.getLogger(DefaultProductService.class);

  private final ProductRepository productRepository;

  private final CsvImportService csvImportService;

  private final BrandService brandService;

  private final ProductTypeService productTypeService;

  private final EmailService emailService;

  public DefaultProductService(ProductRepository productRepository, CsvImportService csvImportService,
                               BrandService brandService, ProductTypeService productTypeService, EmailService emailService) {
    this.productRepository = productRepository;
    this.csvImportService = csvImportService;
    this.brandService = brandService;
    this.productTypeService = productTypeService;
    this.emailService = emailService;
  }

  @Override
  @Caching(evict = {
          @CacheEvict(value = "ProductsByBrand", key = "#brand"),
          @CacheEvict(value = "ProductsByType", key = "#type")
  })
  public ProductModel insertProduct(String model, String type, String brand, String description, double price) {
    ProductModel toInsert = new ProductModel();
    toInsert.setModel(model);
    toInsert.setProductType(productTypeService.getTypeByName(type).stream().findFirst().orElse(null));
    toInsert.setBrand(brandService.getByName(brand).stream().findFirst().orElse(null));
    toInsert.setDescription(description);
    toInsert.setPrice(price);

    return emailService.sendEmailsForProductUpdate(productRepository.save(toInsert));
  }

  @Override
  @Caching(evict = {
          @CacheEvict(value = "ProductsByBrand", key = "#product.brand"),
          @CacheEvict(value = "ProductsByType", key = "#product.productType")
  })
  public ProductModel insertProduct(ProductModel product) {
    return emailService.sendEmailsForProductUpdate(productRepository.save(product));
  }

  @Override
  @Caching(evict = {
          @CacheEvict(value = "ProductsByBrand", allEntries = true),
          @CacheEvict(value = "ProductsByType", allEntries = true)
  })
  public ProductModel updateProduct(ProductModel product) {
    return emailService.sendEmailsForProductUpdate(productRepository.save(product));
  }

  @Override
  public List<ProductModel> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  @Cacheable("ProductsByBrand")
  public List<ProductModel> getProductsByBrand(String brand) {
    BrandModel brandModel = brandService.getByName(brand).orElseThrow(() -> {
      logger.error("No brand with name " + brand + " !");
      return new IllegalArgumentException("No brand with name " + brand + " !");
    });
    return productRepository.findByBrand(brandModel);
  }


  @Override
  @Cacheable("ProductsByType")
  public List<ProductModel> getProductsByType(String type) {
    ProductTypeModel productTypeModel = productTypeService.getTypeByName(type).orElseThrow(() -> {
      logger.error("No type with name " + type + " !");
      return new IllegalArgumentException("No type with name " + type + " !");
    });
    return productRepository.findProductModelByProductType(productTypeModel);
  }

  @Override
  public List<ProductModel> getProductsByBrandAndType(String brand, String type) {
    BrandModel brandModel = brandService.getByName(brand).orElseThrow(() -> {
      logger.error("No brand with name " + brand + " !");
      return new IllegalArgumentException("No brand with name " + brand + " !");
    });

    ProductTypeModel productTypeModel = productTypeService.getTypeByName(type).orElseThrow(() -> {
      logger.error("No type with name " + type + " !");
      return new IllegalArgumentException("No type with name " + type + " !");
    });

    return productRepository.findByBrandAndProductType(brandModel, productTypeModel);
  }

  @Override
  public List<ProductModel> getProductsBelowPrice(double price) {
    return productRepository.findByPriceLessThanEqual(price);
  }

  @Override
  public List<ProductModel> getProductsAbovePrice(double price) {
    return productRepository.findByPriceGreaterThanEqual(price);
  }

  @Override
  @Cacheable("Products")
  public Optional<ProductModel> getProductByModel(String model) {
    return productRepository.findByModel(model);
  }

  @Override
  @CacheEvict(value = "Products", key = "#model")
  public List<ProductModel> deleteProductByModel(String model) {
    return productRepository.deleteByModel(model);
  }

  @Override
  public List<ProductModel> deleteProductsByBrand(String brand) {
    BrandModel brandModel = brandService.getByName(brand).orElseThrow(() -> {
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

  @Override
  public List<ProductModel> insertFile(InputStream inputStream, boolean strict) {
    List<ProductData> productDataList = csvImportService.loadObjectList(ProductData.class, inputStream);

    List<ProductModel> productModels = new LinkedList<>();

    for (ProductData productData : productDataList) {
      ProductModel product = new ProductModel();
      populateProductModel(productData, product);
      populateProductType(strict, productData, product);
      populateBrand(strict, productData, product);
      productModels.add(product);
    }

    return productRepository.saveAll(productModels);
  }

  private void populateBrand(boolean strict, ProductData productData, ProductModel product) {
    Optional<BrandModel> brandOpt = brandService.getByName(productData.getBrand());
    if (brandOpt.isPresent()) {
      product.setBrand(brandOpt.get());
    } else if (strict) {
      String message = "Error occurred while inserting Product: brand not existing and mode set to strict";
      logger.error(message);
      throw new IllegalArgumentException(message);
    } else {
      BrandModel brandModel = new BrandModel();
      productData.setBrand(brandModel.getName());
      product.setBrand(brandService.insertBrand(brandModel));
    }
  }

  private void populateProductType(boolean strict, ProductData productData, ProductModel product) {
    Optional<ProductTypeModel> typeOpt = productTypeService.getTypeByName(productData.getProductType());
    if (typeOpt.isPresent()) {
      product.setProductType(typeOpt.get());
    } else if (strict) {
      String message = "Error occurred while inserting Product: product type not existing and mode set to strict";
      logger.error(message);
      throw new IllegalArgumentException(message);
    } else {
      ProductTypeModel productTypeModel = new ProductTypeModel();
      productTypeModel.setType(productData.getProductType());
      product.setProductType(productTypeService.insertType(productTypeModel));
    }
  }

  private void populateProductModel(ProductData productData, ProductModel product) {
    product.setModel(productData.getModel());
    product.setDescription(productData.getDescription());
    product.setPrice(productData.getPrice());
    product.setQuantity(productData.getQuantity());
  }
}
