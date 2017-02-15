package helix.com.tutorapp.model.repository;

import helix.com.tutorapp.model.entity.PostByParent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by helix on 10/14/2016.
 */
@Repository
public interface PostParentRepository extends CrudRepository<PostByParent, Long> {

    PostByParent findById(Long postId);
    List<PostByParent> findByParentId(Long parentId);

}