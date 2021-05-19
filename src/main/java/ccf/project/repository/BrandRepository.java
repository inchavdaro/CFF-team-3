package ccf.project.repository;

import ccf.project.domain.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandModel, Long>
{
    Optional<BrandModel> findByName(String brand);

    Long deleteByName(String brand);

    BrandModel save(BrandModel brandModel);
}
