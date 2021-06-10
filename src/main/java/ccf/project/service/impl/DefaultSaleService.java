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
    public Page<SaleModel> getByClient(int clientId, Pageable pageable) {
        return saleRepository.getByClient_Id(clientId, pageable);
    }

    @Override
    public Page<SaleModel> getByDate(Timestamp date, Pageable pageable) {
        return saleRepository.getByDate(date, pageable);
    }

    @Override
    public Optional<SaleModel> getById(int id) {
        return saleRepository.getById(id);
    }

    @Override
    public SaleModel insert(SaleModel sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Long deleteById(int id) {
        return saleRepository.deleteById(id);
    }

    @Override
    public Long deleteByClient(ClientModel clientModel) {
        return saleRepository.deleteByClient(clientModel);
    }
}
