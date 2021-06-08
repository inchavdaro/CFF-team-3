package ccf.project.service;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ClientService {

    /**
     * returns a page of clients
     * @param pageNumber
     * @param clientsPerPage
     * @return page of clients
     */
    Page<ClientModel> getPageOfClients(int pageNumber, int clientsPerPage);

 //   Page<SaleModel> getPageOfSales(String bulstat, int pageNumber, int salesPerPage);

    /**
     * returns a client model with given id
     * @param id
     * @return optional client
     */
    Optional<ClientModel> getById(int id);

    /**
     * returns a client with given bulstat
     * @param bulstat
     * @return optional client
     */
    Optional<ClientModel> getByBulstat(String bulstat);

    /**
     * returns all clients
     * @return page of clients
     */
    Page<ClientModel> getAll();

    /**
     * insert a client into the database
     * @param clientModel
     * @return
     */
    ClientModel insert(ClientModel clientModel);

    /**
     * delete a client with given id
     * @param id
     * @return number of entries deleted
     */
    Long deleteById(int id);

    /**
     * delete a client with given bulstat
     * @param bulstat
     * @return number of entries deleted
     */
    Long deleteByBulstat(String bulstat);

}
