package helix.com.tutorapp.repository;

import helix.com.tutorapp.model.PostByTutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by helix on 10/14/2016.
 */
public interface PostTutorRepository extends CrudRepository<PostByTutor, Long> {
    PostByTutor findById(Long postId);
}
