package ccf.project.service;

import ccf.project.domain.BrandModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface BrandService {

    /**
     * Retries a brand from the db by name if such is present
     *
     * @param name - to be searched by
     * @return Optional BrandModel
     */
    Optional<BrandModel> getByName(String name);

    /**
     * Deletes brand from the db by name if such is present
     * @param name - to be searched by
     * @return number of rows affected
     */
    Long deleteByName(String name);

    /**
     * Persist a Brand in the db
     * @param brandModel the BrandModel to be persisted
     *
     * @return the already persistedModel
     */
    BrandModel insertBrand(BrandModel brandModel);

    /**
     * Persists one or more Brands from a csv file
     *
     * @param file the InputStream of the file
     * @return List of already persisted BrandModels
     */
    List<BrandModel> insertFile(InputStream file);

}
