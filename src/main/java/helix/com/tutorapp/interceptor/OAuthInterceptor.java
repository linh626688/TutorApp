package helix.com.tutorapp.interceptor;

import helix.com.tutorapp.constant.Const;
import helix.com.tutorapp.exception.ExceptionMessage;
import helix.com.tutorapp.exception.ServiceException;
import helix.com.tutorapp.model.repository.OAuthAccessTokenRepository;
import helix.com.tutorapp.model.repository.OAuthUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DangThanhLinh on 30/12/2016.
 */
@Component
public class OAuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private OAuthAccessTokenRepository oAuthAccessTokenRepository;

    @Autowired
    private OAuthUserRepository oAuthUserRepository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true; // Don't process non-handler method
        }

        HandlerMethod method = (HandlerMethod) handler;

        if (method.getMethodAnnotation(NoAuthenticationCheck.class) != null) {
            return true; // Bypass all services that is annotated with @NoAuthenticationCheck
        }

        String tokenOAuthInHeader = request.getHeader(Const.AUTH_TOKEN_HEADER);

        if (tokenOAuthInHeader == null) {
            throw new ServiceException(ExceptionMessage.INVALID_TOKEN);
        }
        throw new ServiceException(ExceptionMessage.CONTACT_NOT_FOUND_EXCEPTION);
    }
}

