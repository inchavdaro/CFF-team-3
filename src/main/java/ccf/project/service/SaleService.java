package ccf.project.service;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.Optional;

public interface SaleService {
    /**
     * returns page of sales of given client
     *
     * @param clientId the client Id to be searched by
     * @param pageable the page config
     * @return page of sales
     */
    Page<SaleModel> getByClient(int clientId, Pageable pageable);

    /**
     * returns page of sales with given date
     *
     * @param date     the date to be searched by
     * @param pageable the page config
     * @return page of sales
     */
    Page<SaleModel> getByDate(Timestamp date, Pageable pageable);

    //change int to Long?

    /**
     * returns sale with given id
     *
     * @param id the id to be seached by
     * @return Optional SaleModel
     */
    Optional<SaleModel> getById(int id);

    /**
     * saves a sale to the database
     *
     * @param sale the sale to be inserted
     * @return the already inserted Sale
     */
    SaleModel insert(SaleModel sale);

    /**
     * deletes a sale with given id
     *
     * @param id the id to be searched by
     * @return number of sales deleted
     */
    Long deleteById(int id);

    /**
     * delete a client
     *
     * @param clientModel the client to be seached by
     * @return number of sales deleted
     */
    Long deleteByClient(ClientModel clientModel);
}
