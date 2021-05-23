package ccf.project.repository;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    List<SaleModel> findByClientByClientId(ClientModel client, Pageable pageable);
}
