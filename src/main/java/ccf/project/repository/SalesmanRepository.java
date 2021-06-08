package ccf.project.repository;

import ccf.project.domain.SalesmanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesmanRepository extends JpaRepository<SalesmanModel, Integer> {

    Optional<SalesmanModel> findByUser_Id(Integer userId);
}
