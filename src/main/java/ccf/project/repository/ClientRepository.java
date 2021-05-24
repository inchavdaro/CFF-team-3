package ccf.project.repository;

import ccf.project.domain.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    Page<ClientModel> findAll(Pageable pageable);

    Optional<ClientModel> findById(int id);

    Optional<ClientModel> findByBulstat(String bulstat);

    ClientModel save(ClientModel client);

    Long deleteById(int id);

    Long deleteByBulstat(String bulstat);
}
