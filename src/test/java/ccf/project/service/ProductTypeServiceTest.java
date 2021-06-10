package ccf.project.service;

import ccf.project.domain.ProductTypeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class ProductTypeServiceTest {

    @Autowired
    ProductTypeService productTypeService;

    @Test
    @Transactional
    void givenCsvFileWithBrandsWhenInsertedThenGetCorrectResult() throws IOException {
        File file = new ClassPathResource("product_types.csv").getFile();

        List<ProductTypeModel> productTypeModels = productTypeService.insertFile(new FileInputStream(file));

        Assertions.assertEquals(5, productTypeModels.size());
        for (ProductTypeModel productTypeModel : productTypeModels) {
            Assertions.assertTrue(productTypeModel.getId() != 0);
        }
    }
}
