package ccf.project.service;

import ccf.project.domain.BrandModel;
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
import java.util.Optional;

@SpringBootTest
public class BrandServiceTest
{
    @Autowired
    BrandService brandService;

    @Test
    @Transactional
    public void testSaveAndFindBrand(){

        BrandModel toBeInserted = new BrandModel();
        BrandModel toBeInserted2 = new BrandModel();
        BrandModel toBeInserted3 = new BrandModel();
        toBeInserted.setName("ASUS1");
        toBeInserted2.setName("ASUS2");
        toBeInserted3.setName("ASUS3");
        brandService.insertBrand(toBeInserted);
        brandService.insertBrand(toBeInserted2);
        brandService.insertBrand(toBeInserted3);

        Optional<BrandModel> queryResult = brandService.getByName("ASUS1");
        Optional<BrandModel> queryResult2 = brandService.getByName("ASUS2");
        Optional<BrandModel> queryResult3 = brandService.getByName("ASUS3");

        Assertions.assertTrue(queryResult.isPresent());
        Assertions.assertTrue(queryResult2.isPresent());
        Assertions.assertTrue(queryResult3.isPresent());


        Assertions.assertEquals(toBeInserted, queryResult.get());
        Assertions.assertEquals(toBeInserted2, queryResult2.get());
        Assertions.assertEquals(toBeInserted3, queryResult3.get());
    }
    @Test
    @Transactional
    public void testInsertAndDeleteBrand(){

        BrandModel toBeInserted = new BrandModel();
        BrandModel toBeInserted2 = new BrandModel();
        BrandModel toBeInserted3 = new BrandModel();
        toBeInserted.setName("ASUS1");
        toBeInserted2.setName("ASUS2");
        toBeInserted3.setName("ASUS3");
        brandService.insertBrand(toBeInserted);
        brandService.insertBrand(toBeInserted2);
        brandService.insertBrand(toBeInserted3);

        Optional<BrandModel> queryResult = brandService.getByName("ASUS1");
        Optional<BrandModel> queryResult2 = brandService.getByName("ASUS2");
        Optional<BrandModel> queryResult3 = brandService.getByName("ASUS3");


        Assertions.assertEquals(toBeInserted, queryResult.get());
        Assertions.assertEquals(toBeInserted2, queryResult2.get());
        Assertions.assertEquals(toBeInserted3, queryResult3.get());

        brandService.deleteByName("ASUS1");
        brandService.deleteByName("ASUS2");
        brandService.deleteByName("ASUS3");

        Optional<BrandModel> queryResultAfterDelete = brandService.getByName("ASUS1");
        Optional<BrandModel> queryResult2AfterDelete = brandService.getByName("ASUS2");
        Optional<BrandModel> queryResult3AfterDelete = brandService.getByName("ASUS3");


        Assertions.assertTrue(queryResultAfterDelete.isEmpty());
        Assertions.assertTrue(queryResult2AfterDelete.isEmpty());
        Assertions.assertTrue(queryResult3AfterDelete.isEmpty());

    }

    @Test
    @Transactional
    void givenCsvFileWithBrandsWhenInsertedThenGetCorrectResult() throws IOException {
        File file = new ClassPathResource("brands.csv").getFile();

        List<BrandModel> brandModels = brandService.insertFile(new FileInputStream(file));

        Assertions.assertEquals(5,brandModels.size());
        for (BrandModel brandModel : brandModels) {
            Assertions.assertTrue(brandModel.getId() != 0);
        }
    }


}
