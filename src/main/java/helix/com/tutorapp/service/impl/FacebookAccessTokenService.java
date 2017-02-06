package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.api.FacebookAccessToken;
import helix.com.tutorapp.api.FacebookUser;
import helix.com.tutorapp.constant.FacebookAPIConstant;
import helix.com.tutorapp.dto.AccessTokenDTO;
import helix.com.tutorapp.dto.AccessTokenValidationResultDTO;
import helix.com.tutorapp.dto.OAuthUserDTO;
import helix.com.tutorapp.model.enums.TokenValidationStatus;
import helix.com.tutorapp.service.IAccessTokenService;
import helix.com.tutorapp.model.entity.OAuthAccessToken;
import helix.com.tutorapp.model.entity.OAuthUser;
import helix.com.tutorapp.model.repository.OAuthAccessTokenRepository;
import helix.com.tutorapp.model.repository.OAuthUserRepository;
import ma.glasnost.orika.MapperFacade;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;


@Service
@Transactional(rollbackFor = Exception.class)
public class FacebookAccessTokenService implements IAccessTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MapperFacade mapper;
    @Autowired
    private OAuthUserRepository oAuthUserRepository;
    @Autowired
    private OAuthAccessTokenRepository oAuthAccessTokenRepository;

    @Value("${social.facebook.app-id}")
    private String appId;

    @Value("${social.facebook.app-secret}")
    private String appSecret;

    @Override
    public AccessTokenValidationResultDTO exchange(AccessTokenDTO shortLivedToken) {

        FacebookAccessToken longLivedToken = exchangeForLongLivedToken(shortLivedToken);
        FacebookUser facebookUser = fetchUserProfile(longLivedToken.getAccessToken());

        OAuthUser user = saveUser(facebookUser);
        OAuthAccessToken accessToken = saveAccessToken(longLivedToken, user);

        return generateResponseAccessToken(accessToken, user);
    }

    private AccessTokenValidationResultDTO generateResponseAccessToken(OAuthAccessToken accessToken, OAuthUser user) {

        AccessTokenDTO responseToken = new AccessTokenDTO();
        responseToken.setToken(accessToken.getAccessToken());
        responseToken.setUser(mapper.map(user, OAuthUserDTO.class));

        AccessTokenValidationResultDTO validationResult = new AccessTokenValidationResultDTO();
        validationResult.setValidationStatus(TokenValidationStatus.VALID);
        validationResult.setAccessToken(responseToken);

        return validationResult;
    }

    private OAuthAccessToken saveAccessToken(FacebookAccessToken longLivedToken, OAuthUser user) {
        OAuthAccessToken accessToken = new OAuthAccessToken();
        accessToken.setAccessToken(longLivedToken.getAccessToken());
        Date expiredDate = DateTime.now().plusSeconds(longLivedToken.getExpiresIn()).toDate();
        accessToken.setExpiredDate(expiredDate);
        accessToken.setUser(user);
        oAuthAccessTokenRepository.save(accessToken);
        return accessToken;
    }

    private OAuthUser saveUser(FacebookUser facebookUser) {
        OAuthUser user = new OAuthUser();
        user.setProviderUserId(facebookUser.getId());
        user.setName(facebookUser.getName());
        user.setEmail(facebookUser.getEmail());
        user.setProfileUrl(facebookUser.getLink());
        user.setAvatarUrl(facebookUser.getPicture().getData().getUrl());
        oAuthUserRepository.save(user);
        return user;
    }

    private FacebookUser fetchUserProfile(String accessToken) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("fields", "id,name,email,link,picture{url}");
        params.put("access_token", accessToken);

        return restTemplate.getForObject(
                FacebookAPIConstant.FB_USER_PROFILE_URL_TEMPLATE,
                FacebookUser.class,
                params);
    }

    private FacebookAccessToken exchangeForLongLivedToken(AccessTokenDTO shortLivedToken) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", appId);
        params.put("client_secret", appSecret);
        params.put("fb_exchange_token", shortLivedToken.getToken());

        return restTemplate.getForObject(
                FacebookAPIConstant.FB_EXCHANGE_TOKEN_URL_TEMPLATE,
                FacebookAccessToken.class,
                params);
    }
}
