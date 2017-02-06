package helix.com.tutorapp.model.repository;

import helix.com.tutorapp.model.entity.Parent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by helix on 10/14/2016.
 */
public interface ParentRepository extends CrudRepository<Parent, Long> {
    Parent findById(Long postId);
}
