package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ProductModel;
import ccf.project.domain.ProductTypeModel;
import ccf.project.domain.dtos.ProductData;
import ccf.project.repository.ProductRepository;
import ccf.project.service.BrandService;
import ccf.project.service.CsvImportService;
import ccf.project.service.ProductService;
import ccf.project.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public DefaultProductService(ProductRepository productRepository, CsvImportService csvImportService, BrandService brandService, ProductTypeService productTypeService) {
        this.productRepository = productRepository;
        this.csvImportService = csvImportService;
        this.brandService = brandService;
        this.productTypeService = productTypeService;
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
            product.setBrandByBrandId(brandOpt.get());
        } else if (strict) {
            String message = "Error occurred while inserting Product: brand not existing and mode set to strict";
            logger.error(message);
            throw new IllegalArgumentException(message);
        } else {
            BrandModel brandModel = new BrandModel();
            productData.setBrand(brandModel.getName());
            product.setBrandByBrandId(brandService.insertBrand(brandModel));
        }
    }

    private void populateProductType(boolean strict, ProductData productData, ProductModel product) {
        Optional<ProductTypeModel> typeOpt = productTypeService.getTypeByName(productData.getProductType());
        if (typeOpt.isPresent()) {
            product.setProductTypeByTypeId(typeOpt.get());
        } else if (strict) {
            String message = "Error occurred while inserting Product: product type not existing and mode set to strict";
            logger.error(message);
            throw new IllegalArgumentException(message);
        } else {
            ProductTypeModel productTypeModel = productTypeService.insertType(productData.getProductType());
            product.setProductTypeByTypeId(productTypeModel);
        }
    }

    private void populateProductModel(ProductData productData, ProductModel product) {
        product.setModel(productData.getModel());
        product.setDescription(productData.getDescription());
        product.setPrice(productData.getPrice());
        product.setQuantity(productData.getQuantity());
    }
}
