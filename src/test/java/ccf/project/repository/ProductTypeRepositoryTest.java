package ccf.project.repository;

import ccf.project.domain.ProductTypeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        Assertions.assertEquals(productTypeRepository.findByType("videocard3"), pm);
        Assertions.assertEquals(productTypeRepository.findByType("videocard5"), pm2);
    }

    @Test
    @Transactional
    void deleteFromDatabase(){
        ProductTypeModel pm = new ProductTypeModel();
        pm.setType("videocard");
        productTypeRepository.save(pm);
        ProductTypeModel list = productTypeRepository.deleteByType("videocard");
        Assertions.assertEquals(productTypeRepository.findByType("videocard"), null);
    }
}
