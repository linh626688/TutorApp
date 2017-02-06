package helix.com.tutorapp.model.repository;



import helix.com.tutorapp.model.entity.OAuthAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OAuthAccessTokenRepository extends CrudRepository<OAuthAccessToken, String> {
    OAuthAccessToken findByAccessToken(String accessToken);

    OAuthAccessToken findByUserProviderUserId(String userId);
}
