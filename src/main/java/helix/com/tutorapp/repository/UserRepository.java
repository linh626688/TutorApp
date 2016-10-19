package helix.com.tutorapp.repository;

import helix.com.tutorapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String userName );

    User findById(Long id);

    User findByToken(String token);
}
