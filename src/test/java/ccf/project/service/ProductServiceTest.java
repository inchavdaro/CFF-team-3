package ccf.project.service;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ProductModel;
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
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    @Transactional
    void givenCsvFileWithProductsWhenInsertedThenGetCorrectResult() throws IOException {
        File file = new ClassPathResource("products.csv").getFile();

        List<ProductModel> productModels = productService.insertFile(new FileInputStream(file), false);

        Assertions.assertEquals(5, productModels.size());
        for (ProductModel productModel : productModels) {
            Assertions.assertTrue(productModel.getId() != 0);
        }
    }
}
