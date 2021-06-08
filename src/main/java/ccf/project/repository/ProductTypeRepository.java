package ccf.project.repository;

import ccf.project.domain.ProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeModel, Long> {

    Optional<ProductTypeModel> findByType(String type);

    List<ProductTypeModel> findAll();

    List<ProductTypeModel> deleteByType(String type);
}
