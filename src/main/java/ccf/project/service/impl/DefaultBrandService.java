package ccf.project.service.impl;

import ccf.project.domain.BrandModel;
import ccf.project.domain.UserModel;
import ccf.project.repository.BrandRepository;
import ccf.project.repository.UserRepository;
import ccf.project.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultBrandService implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Optional<BrandModel> findByName(String brand)
    {
        return brandRepository.findByName(brand);
    }

    @Override
    public Long deleteByName(String brand)
    {
        return brandRepository.deleteByName(brand);
    }

    @Override
    public BrandModel save(BrandModel brandModel){
        return brandRepository.save(brandModel);
    }



}
