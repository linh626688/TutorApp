package helix.com.tutorapp.controller;

import helix.com.tutorapp.dto.AccessTokenDTO;
import helix.com.tutorapp.dto.AccessTokenValidationResultDTO;
import helix.com.tutorapp.service.impl.FacebookAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/oauth/facebook/access-token")
public class FacebookAccessTokenController {

    @Autowired
    private FacebookAccessTokenService tokenService;

    @PostMapping("/exchange")
    public AccessTokenValidationResultDTO exchange(@RequestBody AccessTokenDTO accessToken) {
        return tokenService.exchange(accessToken);
    }
}
