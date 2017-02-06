package helix.com.tutorapp.service;


import helix.com.tutorapp.dto.AccessTokenDTO;
import helix.com.tutorapp.dto.AccessTokenValidationResultDTO;

import java.io.IOException;

/**
 * Created by DangThanhLinh on 27/12/2016.
 */
public interface IAccessTokenService {
    AccessTokenValidationResultDTO exchange(AccessTokenDTO accessToken) throws IOException;
}
