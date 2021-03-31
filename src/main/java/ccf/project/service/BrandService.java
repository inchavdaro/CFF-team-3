package ccf.project.service;

import ccf.project.domain.BrandModel;

public interface BrandService {
    BrandModel findByBrand(String brand);
    Long deleteByBrand(String brand);
    public void insert(BrandModel brandModel);
}
