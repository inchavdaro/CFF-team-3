package ccf.project.service;

import ccf.project.domain.ProductModel;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public interface ProductService {

    /**
     * Persists one or more Products from a csv file. if in strict mode and product type or brand
     * are not present in db error will be thrown
     *
     * @param inputStream the InputStream of the file
     * @param strict if operation is strict or not
     * @return List of already persisted BrandModels
     */
    List<ProductModel> insertFile(InputStream inputStream, boolean strict);
}
