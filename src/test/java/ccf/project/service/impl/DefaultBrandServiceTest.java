package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class DefaultBrandServiceTest
{
    @Autowired
    DefaultBrandService brandService;

    @Test
    public void testSaveAndFindBrand(){

        BrandModel toBeInserted = new BrandModel();
        BrandModel toBeInserted2 = new BrandModel();
        BrandModel toBeInserted3 = new BrandModel();
        toBeInserted.setBrand("ASUS1");
        toBeInserted2.setBrand("ASUS2");
        toBeInserted3.setBrand("ASUS3");
        brandService.insert(toBeInserted);
        brandService.insert(toBeInserted2);
        //brandService.insert(toBeInserted3);

        //BrandModel queryResult = brandService.findByBrand("ASUS");
        //Assertions.assertNotEquals(null, queryResult);
    }
}
