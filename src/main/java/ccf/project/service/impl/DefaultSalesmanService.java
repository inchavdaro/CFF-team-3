package ccf.project.service.impl;

import ccf.project.domain.SalesmanModel;
import ccf.project.repository.SalesmanRepository;
import ccf.project.service.SalesmanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultSalesmanService implements SalesmanService {

    private final SalesmanRepository salesmanRepository;

    public DefaultSalesmanService(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @Override
    public SalesmanModel createSalesman(SalesmanModel salesman) {
        return salesmanRepository.save(salesman);
    }

    @Override
    public Optional<SalesmanModel> getById(int id) {
        return salesmanRepository.findById(id);
    }

    @Override
    public Optional<SalesmanModel> getByUserId(int userId) {
        return salesmanRepository.findByUser_Id(userId);
    }

    @Override
    public Page<SalesmanModel> getAll() {
        return salesmanRepository.findAll(Pageable.unpaged());
    }

    @Override
    public Page<SalesmanModel> getAll(int page, int size) {
        return salesmanRepository.findAll(PageRequest.of(page, size));
    }
}
