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
     * @param clientId
     * @param pageable
     * @return page of sales
     */
    Page<SaleModel> getByClient(int clientId, Pageable pageable);

    /**
     * returns page of sales with given date
     * @param date
     * @param pageable
     * @return page of sales
     */
    Page<SaleModel> getByDate(Timestamp date, Pageable pageable);

    //change int to Long?

    /**
     * returns sale with given id
     * @param id
     * @return
     */
    Optional<SaleModel> getById(int id);

    /**
     * saves a sale to the database
     * @param sale
     * @return
     */
    SaleModel insert(SaleModel sale);

    /**
     * deletes a sale with given id
     * @param id
     * @return number of clients deleted
     */
    Long deleteById(int id);

    /**
     * delete a client
     * @param clientModel
     * @return number of clients deleted
     */
    Long deleteByClient(ClientModel clientModel);
}
