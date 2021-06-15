package ccf.project.service;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    ClientService clientService;
    @Autowired
    SaleService saleService;


    @BeforeEach
    private void testSetup() {
        ClientModel client1 = new ClientModel();
        ClientModel client2 = new ClientModel();
        ClientModel client3 = new ClientModel();
        ClientModel client4 = new ClientModel();
        client1.setBulstat("bulstat1");
        client2.setBulstat("bulstat2");
        client3.setBulstat("bulstat3");
        client4.setBulstat("bulstat4");


        Collection<SaleModel> sales = new ArrayList<>();

        SaleModel sale1 = new SaleModel();
        sale1.setSalePrice(10.0);
        sale1.setClient(client1);
        sales.add(sale1);
        //saleService.save(sale1);
        SaleModel sale2 = new SaleModel();
        sale2.setSalePrice(11.0);
        sale1.setClient(client1);
        sales.add(sale2);
        //saleService.save(sale2);
        SaleModel sale3 = new SaleModel();
        sale3.setSalePrice(12.0);
        sale1.setClient(client1);
        sales.add(sale3);
        //saleService.save(sale3);
        SaleModel sale4 = new SaleModel();
        sale4.setSalePrice(13.0);
        sale1.setClient(client1);
        sales.add(sale4);
        //saleService.save(sale4);

        client1.setSales(sales);

        clientService.insert(client1);
        clientService.insert(client2);
        clientService.insert(client3);
        clientService.insert(client4);
    }

    @Test
    @Transactional
    public void testSaveAndFindClient() {

        Page<ClientModel> clientsPage = clientService.getPageOfClients(0, 4);
        List<ClientModel> clientsList = clientsPage.getContent();
        assertEquals(4, clientsList.size());
        assertEquals("bulstat1", clientsList.get(0).getBulstat());
        assertEquals("bulstat2", clientsList.get(1).getBulstat());
        assertEquals("bulstat3", clientsList.get(2).getBulstat());
        assertEquals("bulstat4", clientsList.get(3).getBulstat());
    }

    @Test
    @Transactional
    public void testSaveAndDeleteClient() {

        clientService.deleteByBulstat("bulstat1");
        clientService.deleteByBulstat("bulstat2");
        clientService.deleteByBulstat("bulstat3");
        clientService.deleteByBulstat("bulstat4");

        Page<ClientModel> clientsPage = clientService.getPageOfClients(0, 4);
        assertTrue(clientsPage.isEmpty());
    }

//    @Test
//    @Transactional
//    public void testGetPageOfSales(){
//        Page<SaleModel> sales = clientService.getPageOfSales("bulstat1", 0, 4);
//        List<SaleModel> salesList = sales.getContent();
//        assertEquals(4, salesList.size());
//        assertEquals(10.0, salesList.get(0).getSalePrice());
//        assertEquals(11.0, salesList.get(1).getSalePrice());
//        assertEquals(12.0, salesList.get(2).getSalePrice());
//        assertEquals(13.0, salesList.get(3).getSalePrice());
//    }


}

