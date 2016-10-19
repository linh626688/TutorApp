package helix.com.tutorapp.repository;

import helix.com.tutorapp.model.Tutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by helix on 10/14/2016.
 */
public interface TutorRepository extends CrudRepository<Tutor, Long> {
    Tutor findById(Long postId);
}