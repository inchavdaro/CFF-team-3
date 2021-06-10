package ccf.project.repository;

import ccf.project.domain.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandModel, Integer> {
    Optional<BrandModel> findByName(String brand);

    Long deleteByName(String brand);
}
