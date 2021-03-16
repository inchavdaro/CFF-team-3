package ccf.project.repository;

import ccf.project.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, UserModel>
{
    UserModel findByUsername(String name);


}
