package ccf.project.repository;

import ccf.project.domain.ProductTypeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ProductTypeRepositoryTest {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Test
    @Transactional
    void insertIntoDatabase(){
        ProductTypeModel pm = new ProductTypeModel();
        pm.setType("videocard3");
        ProductTypeModel pm2 = new ProductTypeModel();
        pm2.setType("videocard5");
        productTypeRepository.save(pm);
        productTypeRepository.save(pm2);
        Assertions.assertEquals(productTypeRepository.findByType("videocard3").get(), pm);
        Assertions.assertEquals(productTypeRepository.findByType("videocard5").get(), pm2);
    }

    @Test
    @Transactional
    void deleteFromDatabase(){
        ProductTypeModel pm = new ProductTypeModel();
        pm.setType("videocard");
        productTypeRepository.save(pm);
        productTypeRepository.deleteByType("videocard");
        Assertions.assertEquals(productTypeRepository.findByType("videocard"), Optional.empty());
    }
}
