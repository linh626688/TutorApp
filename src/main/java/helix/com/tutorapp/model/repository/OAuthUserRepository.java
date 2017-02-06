package helix.com.tutorapp.model.repository;


import helix.com.tutorapp.model.entity.OAuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthUserRepository extends CrudRepository<OAuthUser, String> {
    OAuthUser findByProviderUserId(String userId);

    OAuthUser findByEmail(String email);
}
