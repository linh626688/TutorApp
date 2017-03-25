package helix.com.tutorapp.model.repository;

import helix.com.tutorapp.model.entity.PostByParent;
import helix.com.tutorapp.model.entity.PostByTutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by helix on 10/14/2016.
 */
public interface PostTutorRepository extends CrudRepository<PostByTutor, Long> {
    PostByTutor findById(Long postId);

    PostByTutor findByIdAndTutorId(Long postId, Long tutorId);

    List<PostByTutor> findByTutorId(Long tutorId);
}
