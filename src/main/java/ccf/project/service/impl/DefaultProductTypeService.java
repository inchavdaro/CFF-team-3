package ccf.project.service.impl;

import ccf.project.domain.ProductTypeModel;
import ccf.project.repository.ProductTypeRepository;
import ccf.project.service.CsvImportService;
import ccf.project.service.ProductTypeService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultProductTypeService implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    private final CsvImportService csvImportService;

    public DefaultProductTypeService(ProductTypeRepository productTypeRepository, CsvImportService csvImportService) {
        this.productTypeRepository = productTypeRepository;
        this.csvImportService = csvImportService;
    }

    @Override
    public Optional<ProductTypeModel> getTypeByName(String name){
        return productTypeRepository.findByType(name);
    }

    @Override
    public Optional<ProductTypeModel> deleteByName(String name) { //Returns one object or null because of unique in table
            return productTypeRepository.deleteByType(name).stream().findAny();
    }

    @Override
    public ProductTypeModel insertType(String name) {
        ProductTypeModel pm = new ProductTypeModel();
        pm.setType(name);
        return productTypeRepository.save(pm);
    }

    @Override
    public List<ProductTypeModel> getAllTypes() {
        return   productTypeRepository.findAll();
    }

    @Override
    public List<ProductTypeModel> insertFile(InputStream inputStream) {
        List<ProductTypeModel> productTypeModels = csvImportService.loadObjectList(ProductTypeModel.class, inputStream);

        return productTypeRepository.saveAll(productTypeModels);
    }


}
