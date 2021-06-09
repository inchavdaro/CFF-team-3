package ccf.project.service;

import ccf.project.domain.BrandModel;

import java.util.Optional;

public interface BrandService {
    Optional<BrandModel> findByName(String brand);
    Long deleteByName(String brand);
    BrandModel save(BrandModel brandModel);
}
