package teamg.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamg.spring.boot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
