package ccf.project.service;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.Optional;

public interface SaleService {
    Page<SaleModel> findByClient(int clientId, Pageable pageable);
    Page<SaleModel> findByDate(Timestamp date, Pageable pageable);

    //change int to Long?
    Optional<SaleModel> findById(int id);

    SaleModel save(SaleModel sale);
    Long deleteById(int id);
    Long deleteByClient(ClientModel clientModel);
}
