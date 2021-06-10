package ccf.project.repository;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;


@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {

    Page<SaleModel> getByClient_Id(Integer id, Pageable pageable);
    Page<SaleModel> getByDate(Timestamp date, Pageable pageable);
    Optional<SaleModel> getById(Integer id);
    Long deleteById(int id);
    Long deleteByClient(ClientModel clientModel);
}
