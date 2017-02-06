package helix.com.tutorapp.model.repository;

import helix.com.tutorapp.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    User findById(Long id);

    User findByToken(String token);
}
