package helix.com.tutorapp.repository;

import helix.com.tutorapp.model.PostByParent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by helix on 10/14/2016.
 */
@Repository
public interface PostParentRepository extends CrudRepository<PostByParent, Long> {

    PostByParent findById(Long postId);
}