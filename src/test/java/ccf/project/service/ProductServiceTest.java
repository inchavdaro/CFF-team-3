package ccf.project.service;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ProductModel;
import ccf.project.domain.ProductTypeModel;
import ccf.project.repository.BrandRepository;
import ccf.project.repository.ProductTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    ProductService productService;

    private BrandModel setupBrand1,setupBrand2,setupBrand3;
    private ProductTypeModel setupType1,setupType2;
    private ProductModel product1,product2,product3,product4;
    @BeforeEach
    void setup(){
        setupBrand1 = new BrandModel();
        setupBrand1.setName("Asus");
        setupBrand1=brandRepository.save(setupBrand1);

        setupBrand2 = new BrandModel();
        setupBrand2.setName("MSI");
        setupBrand2=brandRepository.save(setupBrand2);

        setupBrand3 = new BrandModel();
        setupBrand3.setName("AMD");
        setupBrand3=brandRepository.save(setupBrand3);

        setupType1 = new ProductTypeModel();
        setupType1.setType("videocard");
        setupType1=productTypeRepository.save(setupType1);

        setupType2 = new ProductTypeModel();
        setupType2.setType("processor");
        setupType2=productTypeRepository.save(setupType2);

        product1 = new ProductModel();
        product1.setModel("bestASUS4704GB");
        product1.setProductType(setupType1);
        product1.setBrand(setupBrand2);
        product1.setPrice(200);
        product1.setDescription("The best model");
        product1.setQuantity(100L);

        product2 = new ProductModel();
        product2.setModel("bestASUS4808GB");
        product2.setProductType(setupType1);
        product2.setBrand(setupBrand1);
        product2.setPrice(211);
        product2.setDescription("The best 480 model");
        product2.setQuantity(101L);

        product3 = new ProductModel();
        product3.setModel("RYZEN3");
        product3.setProductType(setupType2);
        product3.setBrand(setupBrand3);
        product3.setPrice(211);
        product3.setDescription("The not best proc");
        product3.setQuantity(101L);

        product4 = new ProductModel();
        product4.setModel("RYZEN5");
        product4.setProductType(setupType2);
        product4.setBrand(setupBrand3);
        product4.setPrice(211);
        product4.setDescription("The best proc");
        product4.setQuantity(101L);

        productService.insertProduct(product1);
        productService.insertProduct(product2);
        productService.insertProduct(product3);
        productService.insertProduct(product4);
    }


    @Test
    @Transactional
    public void testInsertAndFindProduct(){


        Assertions.assertEquals(productService.getProductByModel("bestASUS4704GB").get(),product1);
        Assertions.assertEquals(productService.getProductByModel("bestASUS4808GB").get(),product2);



    }
    @Test
    @Transactional
    public void testInsertAndDeleteProduct(){


        Assertions.assertEquals(productService.deleteProductByModel("bestASUS4704GB"), List.of(product1));
        Assertions.assertEquals(productService.deleteProductByModel("bestASUS4808GB"),List.of(product2));
        Assertions.assertEquals(productService.getProductByModel("bestASUS4704GB"), Optional.empty());
        Assertions.assertEquals(productService.getProductByModel("bestASUS4808GB"), Optional.empty());

    }

    @Test
    @Transactional
    public void testInsertAndDeleteProductsByBrand(){
        Assertions.assertEquals(productService.deleteProductsByBrand("Asus"),List.of(product2));
        Assertions.assertEquals(productService.deleteProductsByBrand("MSI"),List.of(product1));
        Assertions.assertEquals(productService.deleteProductsByBrand("Asus"), List.of());
        Assertions.assertEquals(productService.getProductsByBrand("MSI"), List.of());
        Assertions.assertEquals(productService.getProductsByBrand("AMD").size(), 2);
    }

    @Test
    @Transactional
    public void testInsertAndDeleteProductsByType(){
        Assertions.assertEquals(productService.deleteProductsByType("videocard"),List.of(product1,product2));
        Assertions.assertEquals(productService.deleteProductsByType("processor"),List.of(product3,product4));
    }

}
