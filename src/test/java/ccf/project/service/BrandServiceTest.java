package ccf.project.service;

import ccf.project.domain.BrandModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
public class BrandServiceTest
{
    @Autowired
    BrandService brandService;

    @Test
    @Transactional
    public void testInsertAndFindBrand(){

        BrandModel toBeInserted = new BrandModel();
        BrandModel toBeInserted2 = new BrandModel();
        BrandModel toBeInserted3 = new BrandModel();
        toBeInserted.setName("ASUS1");
        toBeInserted2.setName("ASUS2");
        toBeInserted3.setName("ASUS3");
        brandService.insert(toBeInserted);
        brandService.insert(toBeInserted2);
        brandService.insert(toBeInserted3);

        Optional<BrandModel> queryResult = brandService.findByName("ASUS1");
        Optional<BrandModel> queryResult2 = brandService.findByName("ASUS2");
        Optional<BrandModel> queryResult3 = brandService.findByName("ASUS3");

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
        brandService.insert(toBeInserted);
        brandService.insert(toBeInserted2);
        brandService.insert(toBeInserted3);

        Optional<BrandModel> queryResult = brandService.findByName("ASUS1");
        Optional<BrandModel> queryResult2 = brandService.findByName("ASUS2");
        Optional<BrandModel> queryResult3 = brandService.findByName("ASUS3");


        Assertions.assertEquals(toBeInserted, queryResult.get());
        Assertions.assertEquals(toBeInserted2, queryResult2.get());
        Assertions.assertEquals(toBeInserted3, queryResult3.get());

        brandService.deleteByName("ASUS1");
        brandService.deleteByName("ASUS2");
        brandService.deleteByName("ASUS3");

        Optional<BrandModel> queryResultAfterDelete = brandService.findByName("ASUS1");
        Optional<BrandModel> queryResult2AfterDelete = brandService.findByName("ASUS2");
        Optional<BrandModel> queryResult3AfterDelete = brandService.findByName("ASUS3");


        Assertions.assertTrue(queryResultAfterDelete.isEmpty());
        Assertions.assertTrue(queryResult2AfterDelete.isEmpty());
        Assertions.assertTrue(queryResult3AfterDelete.isEmpty());

    }


}
