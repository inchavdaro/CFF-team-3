package ccf.project.service;

import ccf.project.domain.ProductTypeModel;
import java.util.List;

public interface ProductTypeService {

    ProductTypeModel getTypeByName(String name);

    ProductTypeModel deleteByName(String name);

    ProductTypeModel insertType(String type);

    List<ProductTypeModel> getAllTypes();
}
