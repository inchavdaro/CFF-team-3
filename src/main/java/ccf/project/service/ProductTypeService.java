package ccf.project.service;

import ccf.project.domain.ProductTypeModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductTypeService {

    ProductTypeModel getTypeByName(String name);

    @Transactional
    List<ProductTypeModel> deleteByName(String name);

    ProductTypeModel insertType(String type);

    List<ProductTypeModel> getAllTypes();
}
