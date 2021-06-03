package ccf.project.repository;

import ccf.project.domain.ProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeModel, Integer> {

    ProductTypeModel findByType(String type);

    List<ProductTypeModel> findAll();

    List<ProductTypeModel> deleteByType(String type);
}
