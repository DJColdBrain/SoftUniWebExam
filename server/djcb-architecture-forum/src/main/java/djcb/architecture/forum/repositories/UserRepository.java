package djcb.architecture.forum.repositories;

import djcb.architecture.forum.beans.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, BigDecimal> {

    User findByUsername(String username);

}
