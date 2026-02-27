package com.marcoGullich.pmanager.infrastructure.security;

import com.marcoGullich.pmanager.infrastructure.config.AppConfigProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServices {

    private final AppConfigProperties props;

    private final static String AUTH_TOKEN_HEADER_NAME = "x-api-key";

    public AuthenticationServices(AppConfigProperties props) {
        this.props = props;
    }

    public Authentication getAuthentication(HttpServletRequest request){
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if(!Objects.equals(apiKey, props.getSecurity().getApiKey())) {
            throw new BadCredentialsException("Invalid API Key: " + apiKey);
        }
        return new ApiKeyAuthenticationToken(apiKey);
    }
}
