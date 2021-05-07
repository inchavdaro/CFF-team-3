package ccf.project.service;

import ccf.project.domain.ProductTypeModel;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    ProductTypeModel getTypeByName(String name);

    Optional<ProductTypeModel> deleteByName(String name);

    ProductTypeModel insertType(String type);

    List<ProductTypeModel> getAllTypes();
}
