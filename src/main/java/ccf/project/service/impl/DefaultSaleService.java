package ccf.project.service.impl;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import ccf.project.repository.SaleRepository;
import ccf.project.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class DefaultSaleService implements SaleService {

    SaleRepository saleRepository;

    @Autowired
    public DefaultSaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public Page<SaleModel> findByClient(ClientModel client, Pageable pageable) {
        return saleRepository.findByClientByClientId(client, pageable);
    }

    @Override
    public Page<SaleModel> findByDate(Timestamp date, Pageable pageable) {
        return saleRepository.findByDate(date, pageable);
    }

    @Override
    public Optional<SaleModel> findById(int id) {
        return saleRepository.findById(id);
    }

    @Override
    public SaleModel save(SaleModel sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Long deleteById(int id) {
        return saleRepository.deleteById(id);
    }

    @Override
    public Long deleteByClient(ClientModel clientModel) {
        return saleRepository.deleteByClientByClientId(clientModel);
    }
}
