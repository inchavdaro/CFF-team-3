package ccf.project.service;

import ccf.project.domain.ProductTypeModel;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    Optional<ProductTypeModel> getTypeByName(String name);

    Optional<ProductTypeModel> deleteByName(String name);

    ProductTypeModel insertType(ProductTypeModel type);

    List<ProductTypeModel> getAllTypes();
}
