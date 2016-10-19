package helix.com.tutorapp.repository;

import helix.com.tutorapp.model.Parent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by helix on 10/14/2016.
 */
public interface ParentRepository extends CrudRepository<Parent, Long> {
    Parent findById(Long postId);
}
