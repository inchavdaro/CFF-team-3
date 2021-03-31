package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.domain.UserModel;
import ccf.project.repository.BrandRepository;
import ccf.project.repository.UserRepository;
import ccf.project.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultBrandService implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public BrandModel findByBrand(String brand)
    {
        return brandRepository.findByBrand(brand);
    }

    @Override
    public Long deleteByBrand(String brand)
    {
        return brandRepository.deleteByBrand(brand);
    }

    @Override
    public void insert(BrandModel brandModel){
        brandRepository.save(brandModel);
    }



}
