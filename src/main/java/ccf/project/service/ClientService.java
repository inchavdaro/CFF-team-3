package ccf.project.service;

import ccf.project.domain.ClientModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClientService {

    Page<ClientModel> getPageOfClients(int pageNumber, int clientsPerPage);

 //   Page<SaleModel> getPageOfSales(String bulstat, int pageNumber, int salesPerPage);

    Optional<ClientModel> findById(int id);

    Optional<ClientModel> findByBulstat(String bulstat);

    ClientModel save(ClientModel clientModel);

    Long deleteById(int id);

    Long deleteByBulstat(String bulstat);

}
