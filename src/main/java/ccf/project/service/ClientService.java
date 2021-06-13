package ccf.project.service;

import ccf.project.domain.ClientModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClientService {

    /**
     * returns a page of clients
     *
     * @param pageNumber     the number of the page 0...n
     * @param clientsPerPage the size of the page 0...n
     * @return page of clients
     */
    Page<ClientModel> getPageOfClients(int pageNumber, int clientsPerPage);

    //   Page<SaleModel> getPageOfSales(String bulstat, int pageNumber, int salesPerPage);

    /**
     * returns a client model with given id
     *
     * @param id to be searched by
     * @return optional client
     */
    Optional<ClientModel> getById(int id);

    /**
     * returns a client with given bulstat
     *
     * @param bulstat to be searched by
     * @return optional client
     */
    Optional<ClientModel> getByBulstat(String bulstat);

    /**
     * returns all clients
     *
     * @return page of clients
     */
    Page<ClientModel> getAll();

    /**
     * insert a client into the database
     *
     * @param clientModel the client to be saved
     * @return the already saved client
     */
    ClientModel insert(ClientModel clientModel);

    /**
     * delete a client with given id
     *
     * @param id the id to be searched by
     * @return number of entries deleted
     */
    Long deleteById(int id);

    /**
     * delete a client with given bulstat
     *
     * @param bulstat the bulstat to be searched by
     * @return number of entries deleted
     */
    Long deleteByBulstat(String bulstat);

}
