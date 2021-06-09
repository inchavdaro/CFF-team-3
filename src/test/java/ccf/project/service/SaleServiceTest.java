package ccf.project.service;

import ccf.project.domain.*;
import ccf.project.domain.enums.UserRole;
import ccf.project.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@SpringBootTest
public class SaleServiceTest {
    private SaleService saleService;
    private ClientRepository clientRepository;
    private BrandRepository brandRepository;
    private SalesmanRepository salesmanRepository;
    private ProductTypeRepository productTypeRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public SaleServiceTest(SaleService saleService, ClientRepository clientRepository, BrandRepository brandRepository, SalesmanRepository salesmanRepository, ProductTypeRepository productTypeRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.saleService = saleService;
        this.clientRepository = clientRepository;
        this.brandRepository = brandRepository;
        this.salesmanRepository = salesmanRepository;
        this.productTypeRepository = productTypeRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @BeforeEach
    private void setup(){

        SaleModel sale = new SaleModel();

        BrandModel setupBrand = new BrandModel();
        setupBrand.setName("ASUS");
        setupBrand = brandRepository.save(setupBrand);
        ProductTypeModel setupType = new ProductTypeModel();
        setupType.setType("videocard");
        setupType = productTypeRepository.save(setupType);
        ProductModel productModel = new ProductModel();
        productModel.setBrand(setupBrand);
        productModel.setProductType(setupType);
        productModel = productRepository.save(productModel);

        sale.setProduct(productModel);

        UserModel u1 = new UserModel();
        u1.setUsername("test");
        u1.setPass("test");
        u1.setRole(UserRole.SALESMAN);
        //UserModel u2 = userRepository.save(u1);

        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setFullname("Test Test");
        salesmanModel.setEmail("test@test.com");
        salesmanModel.setUser(u1);
        SalesmanModel created = salesmanRepository.save(salesmanModel);
        sale.setSalesmanBySalesmanId(created);

        ClientModel clientModel = new ClientModel();
        clientModel = clientRepository.save(clientModel);
        sale.setClient(clientModel);

        sale.setSalePrice(1.0);
        sale.setDate(Timestamp.valueOf("2021-06-05 10:10:10.0"));
        saleService.insert(sale);

    }

    @Test
    @Transactional
    public void givenCorrectSalesInDatabase_whenQuery_displayedCorrectly(){
        Page<SaleModel> salePage = saleService.getByDate(Timestamp.valueOf("2021-06-05 10:10:10.0"), PageRequest.of(0, 1));
        Assertions.assertEquals(1.0, salePage.getContent().get(0).getSalePrice());
        Assertions.assertEquals("videocard", salePage.getContent().get(0).getProduct().getProductType().getType());
        Assertions.assertEquals("ASUS", salePage.getContent().get(0).getProduct().getBrand().getName());
    }

}
