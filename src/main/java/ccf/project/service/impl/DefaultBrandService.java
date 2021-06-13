package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.repository.BrandRepository;
import ccf.project.service.BrandService;
import ccf.project.service.CsvImportService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    @Cacheable("Brands")
    public Optional<BrandModel> getByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    @CacheEvict(value = "Brands", key = "brand")
    public Long deleteByName(String brand) {
        return brandRepository.deleteByName(brand);
    }

    @Override
    public BrandModel insertBrand(BrandModel brandModel) {
        return brandRepository.save(brandModel);
    }

    @Override
    public List<BrandModel> insertFile(InputStream file) {
        List<BrandModel> brandModels = csvImportService.loadObjectList(BrandModel.class, file);

        return brandRepository.saveAll(brandModels);
    }


}
