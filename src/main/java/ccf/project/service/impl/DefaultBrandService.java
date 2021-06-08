package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.domain.UserModel;
import ccf.project.repository.BrandRepository;
import ccf.project.repository.UserRepository;
import ccf.project.service.BrandService;
import ccf.project.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBrandService implements BrandService {

    private final BrandRepository brandRepository;

    private final CsvImportService csvImportService;

    public DefaultBrandService(BrandRepository brandRepository, CsvImportService csvImportService) {
        this.brandRepository = brandRepository;
        this.csvImportService = csvImportService;
    }

    @Override
    public Optional<BrandModel> getByName(String name)
    {
        return brandRepository.findByName(name);
    }

    @Override
    public Long deleteByName(String brand)
    {
        return brandRepository.deleteByName(brand);
    }

    @Override
    public BrandModel insertBrand(BrandModel brandModel){
        return brandRepository.save(brandModel);
    }

    @Override
    public List<BrandModel> insertFile(InputStream file) {
        List<BrandModel> brandModels = csvImportService.loadObjectList(BrandModel.class, file);

        return brandRepository.saveAll(brandModels);
    }


}
