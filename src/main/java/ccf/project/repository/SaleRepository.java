package ccf.project.repository;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    Collection<SaleModel> findByClient(ClientModel client, Pageable pageable);
}
