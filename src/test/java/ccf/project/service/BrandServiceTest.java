package ccf.project.service;

import ccf.project.domain.BrandModel;
import ccf.project.service.BrandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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

        BrandModel queryResult = brandService.findByBrand("ASUS1");
        BrandModel queryResult2 = brandService.findByBrand("ASUS2");
        BrandModel queryResult3 = brandService.findByBrand("ASUS3");
        Assertions.assertNotEquals(null, queryResult);
        Assertions.assertNotEquals(null, queryResult2);
        Assertions.assertNotEquals(null, queryResult3);

        Assertions.assertEquals(toBeInserted, queryResult);
        Assertions.assertEquals(toBeInserted2, queryResult2);
        Assertions.assertEquals(toBeInserted3, queryResult3);
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

        BrandModel queryResult = brandService.findByBrand("ASUS1");
        BrandModel queryResult2 = brandService.findByBrand("ASUS2");
        BrandModel queryResult3 = brandService.findByBrand("ASUS3");

        Assertions.assertEquals(toBeInserted, queryResult);
        Assertions.assertEquals(toBeInserted2, queryResult2);
        Assertions.assertEquals(toBeInserted3, queryResult3);

        brandService.deleteByBrand("ASUS1");
        brandService.deleteByBrand("ASUS2");
        brandService.deleteByBrand("ASUS3");

        BrandModel queryResultAfterDelete = brandService.findByBrand("ASUS1");
        BrandModel queryResult2AfterDelete = brandService.findByBrand("ASUS2");
        BrandModel queryResult3AfterDelete = brandService.findByBrand("ASUS3");


        Assertions.assertEquals(null, queryResultAfterDelete);
        Assertions.assertEquals(null, queryResult2AfterDelete);
        Assertions.assertEquals(null, queryResult3AfterDelete);
    }


}
