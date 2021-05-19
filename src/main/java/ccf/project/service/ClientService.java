package ccf.project.service;

import ccf.project.domain.BrandModel;
import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {

    Page<ClientModel> findAll(Pageable pageable);

    Page<SaleModel> getSales(Pageable pageable);

    Optional<ClientModel> findById(int id);

    Optional<ClientModel> findByBulstat(String bulstat);

    ClientModel save(ClientModel clientModel);

    Long deleteById(int id);

    Long deleteByBulstat(String bulstat);

}
