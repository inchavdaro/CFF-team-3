package ccf.project.service;

import ccf.project.domain.SaleModel;
import ccf.project.domain.SalesmanModel;
import ccf.project.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.sql.Timestamp;

@SpringBootTest
public class SaleServiceTest {
    private SaleService saleService;
    private ClientRepository clientRepository;

    @BeforeEach
    private void setup(){

        SaleModel sale1 = new SaleModel();
        SaleModel sale2 = new SaleModel();

        sale1.setSalePrice(1.0);
        sale2.setSalePrice(2.0);

        sale1.setDate(Timestamp.valueOf("2021-06-05 10:10:10.0"));
        sale2.setDate(Timestamp.valueOf("2007-06-04 10:10:10.0"));

        saleService.insert(sale1);
        saleService.insert(sale2);
    }

    @Test
    public void givenCorrectSalesInDatabase_whenQuery_displayedCorrectly(){
        Page<SaleModel> salePage = saleService.getByDate(Timestamp.valueOf("2021-06-05 10:10:10.0"), PageRequest.of(0, 1));
        Assertions.assertEquals(1.0, salePage.getContent().get(0));
    }

}
