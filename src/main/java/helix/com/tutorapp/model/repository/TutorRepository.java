package helix.com.tutorapp.model.repository;

import helix.com.tutorapp.model.entity.Tutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by helix on 10/14/2016.
 */
public interface TutorRepository extends CrudRepository<Tutor, Long> {
    Tutor findById(Long postId);
}