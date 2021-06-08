package ccf.project.service.impl;

import ccf.project.domain.ProductTypeModel;
import ccf.project.repository.ProductTypeRepository;
import ccf.project.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultProductTypeService implements ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public Optional<ProductTypeModel> getTypeByName(String name){
        return productTypeRepository.findByType(name);
    }

    @Override
    public Optional<ProductTypeModel> deleteByName(String name) { //Returns one object or null because of unique in table
            return productTypeRepository.deleteByType(name).stream().findAny();
    }

    @Override
    public ProductTypeModel insertType(ProductTypeModel type) {
        return productTypeRepository.save(type);
    }

    @Override
    public List<ProductTypeModel> getAllTypes() {
        return   productTypeRepository.findAll();
    }


}
