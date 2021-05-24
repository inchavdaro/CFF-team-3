package ccf.project.service;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    ClientService clientService;

    @Test
    @Transactional
    public void testSaveAndFindClient(){

        testSetup();

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
    public void testSaveAndDeleteClient(){

        testSetup();

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
//        testSetup();
//        Page<SaleModel> sales = clientService.getPageOfSales("bulstat1", 0, 4);
//        List<SaleModel> salesList = sales.getContent();
//        assertEquals(4, salesList.size());
//        assertEquals(10.0, salesList.get(0).getSalePrice());
//        assertEquals(11.0, salesList.get(1).getSalePrice());
//        assertEquals(12.0, salesList.get(2).getSalePrice());
//        assertEquals(13.0, salesList.get(3).getSalePrice());
//    }






    private void testSetup(){
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
        sales.add(sale1);
        SaleModel sale2 = new SaleModel();
        sale1.setSalePrice(11.0);
        sales.add(sale2);
        SaleModel sale3 = new SaleModel();
        sale1.setSalePrice(12.0);
        sales.add(sale3);
        SaleModel sale4 = new SaleModel();
        sale1.setSalePrice(13.0);
        sales.add(sale4);

        client1.setSales(sales);

        clientService.save(client1);
        clientService.save(client2);
        clientService.save(client3);
        clientService.save(client4);
    }
}

