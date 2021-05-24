package ccf.project.service.impl;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import ccf.project.repository.ClientRepository;
import ccf.project.repository.SaleRepository;
import ccf.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultClientService implements ClientService {


    ClientRepository clientRepository;
    SaleRepository saleRepository;

    @Autowired
    public DefaultClientService(ClientRepository clientRepository, SaleRepository saleRepository) {
        this.clientRepository = clientRepository;
        this.saleRepository = saleRepository;
    }


    @Override
    public Page<ClientModel> getPageOfClients(int pageNumber, int clientsPerPage) {
        return clientRepository.findAll(PageRequest.of(pageNumber, clientsPerPage));
    }

    @Override
    public Page<SaleModel> getPageOfSales(String bulstat, int pageNumber, int salesPerPage) {
        Optional<ClientModel> client = findByBulstat(bulstat);
        if(client.isPresent()) {

            return saleRepository.findByClientByClientId(client.get(), PageRequest.of(pageNumber, salesPerPage));
        }
        return Page.empty();
    }



    @Override
    public Optional<ClientModel> findById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<ClientModel> findByBulstat(String bulstat) {
        return clientRepository.findByBulstat(bulstat);
    }

    @Override
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    @Override
    public Long deleteById(int id) {
        return clientRepository.deleteById(id);
    }

    @Override
    public Long deleteByBulstat(String bulstat) {
        return clientRepository.deleteByBulstat(bulstat);
    }
}
