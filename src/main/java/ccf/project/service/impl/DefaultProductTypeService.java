package ccf.project.service.impl;

import ccf.project.domain.ProductTypeModel;
import ccf.project.repository.ProductTypeRepository;
import ccf.project.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductTypeService implements ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public ProductTypeModel getTypeByName(String name){
        return productTypeRepository.findByType(name);
    }

    @Override
    public ProductTypeModel deleteByName(String name) {
        return productTypeRepository.deleteByType(name);
    }

    @Override
    public ProductTypeModel insertType(String type) {
        ProductTypeModel pm = new ProductTypeModel();
        pm.setType(type);
        return productTypeRepository.save(pm);
    }

    @Override
    public List<ProductTypeModel> getAllTypes() {
        return   productTypeRepository.findAll();
    }


}
