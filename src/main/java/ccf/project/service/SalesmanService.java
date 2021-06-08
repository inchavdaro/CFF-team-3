package ccf.project.service;

import ccf.project.domain.SalesmanModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SalesmanService {

    SalesmanModel createSalesman(SalesmanModel salesman);

    Optional<SalesmanModel> getById(int id);

    Optional<SalesmanModel> getByUserId(int userId);

    Page<SalesmanModel> getAll();

    Page<SalesmanModel> getAll(int page, int size);
}
