package ccf.project.service;

import ccf.project.domain.SalesmanModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SalesmanService {

    /**
     * Persists new Salesman in the db
     *
     * @param salesman the SalesmanModel to be persisted
     * @return the already persisted SalesmanModel
     */
    SalesmanModel insertSalesman(SalesmanModel salesman);

    /**
     * Retrieves a Salesman from the db by id
     *
     * @param id the id to be searched by
     * @return Optional SalesmanModel
     */
    Optional<SalesmanModel> getById(int id);

    /**
     * Retrieves a Salesman from the db by UserId
     *
     * @param userId the user id to be searched by
     * @return Optional SalesmanModel
     */
    Optional<SalesmanModel> getByUserId(int userId);

    /**
     * Gets all persisted Salesmen in wrapped in Page
     *
     * @return Page of SalesmanModel
     */
    Page<SalesmanModel> getAll();

    /**
     * Gets a page of Salesmen persisted in the db
     *
     * @param page page number 0...n
     * @param size page size 1...n
     * @return Page of SalesmanModel
     */
    Page<SalesmanModel> getAll(int page, int size);
}
