package ccf.project.repository;

import ccf.project.domain.UserModel;
import ccf.project.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String name);

    Integer deleteByEmail(String username);

    List<UserModel> findByRole(UserRole role);
}
