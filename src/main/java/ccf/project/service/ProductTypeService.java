package ccf.project.service;

import ccf.project.domain.ProductTypeModel;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    /**
     * Retrieves Product Type from the db by name if present
     *
     * @param name - to be searched by
     * @return Optional ProductTypeModel
     */
    Optional<ProductTypeModel> getTypeByName(String name);

    /**
     * Deletes Product Type from the db by name if such present
     *
     * @param name - to be searched by
     * @return Optional ProductTypeModel
     */
    Optional<ProductTypeModel> deleteByName(String name);

    /**
     * Creates ProductType model with given name and persists it in the db
     * @param name of the new type
     *
     * @return Already persisted ProductTypeModel
     */
    ProductTypeModel insertType(String name);

    /**
     * Gets all persisted Product Types from the db in a list
     *
     * @return List of ProductTypeModel
     */
    List<ProductTypeModel> getAllTypes();

    /**
     * Persists one or more Product Types from a csv file
     *
     * @param inputStream the InputStream of the file
     * @return List of already persisted ProductTypeModel
     */
    List<ProductTypeModel> insertFile(InputStream inputStream);
}
