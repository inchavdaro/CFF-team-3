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
        productTypeRepository.save(pm);
        Assertions.assertEquals(productTypeRepository.findByType("videocard3"), pm);
    }

    @Test
    @Transactional
    void deleteFromDatabase(){
        List<ProductTypeModel> list = productTypeRepository.deleteByType("videocard");
        Assertions.assertEquals(productTypeRepository.findByType("videocard"), null);
        Assertions.assertEquals(2, list.size());
    }
}
