package ccf.project.repository;

import ccf.project.domain.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandModel, Long>
{
    BrandModel findByBrand(String brand);
    Long deleteByBrand(String brand);
}
