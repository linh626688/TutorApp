package helix.com.tutorapp.model.repository;

import helix.com.tutorapp.model.entity.Messsage;
import helix.com.tutorapp.model.entity.Tutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by naophang113 on 5/8/2017.
 */
@Repository
public interface MesssageRepository extends CrudRepository<Messsage, Long> {
    Messsage findById(Long postId);

    List<Messsage> findByTutorId(Long tutorId);

}
