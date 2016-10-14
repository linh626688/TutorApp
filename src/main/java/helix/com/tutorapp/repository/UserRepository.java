package helix.com.tutorapp.repository;

import helix.com.tutorapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    User findByToken(String token);

    User findById(Long id);

//    List<User> findByClassroom(ClassRoom classRoom);//hic
}
