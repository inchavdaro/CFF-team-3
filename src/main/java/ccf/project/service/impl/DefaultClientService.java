package ccf.project.service.impl;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import ccf.project.repository.ClientRepository;
import ccf.project.repository.SaleRepository;
import ccf.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<ClientModel> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<SaleModel> getSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
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
