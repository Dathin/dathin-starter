package io.github.dathin.boot.dathinstarterauthorizer.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.dathin.dathinsession.client.UserClient;
import io.github.dathin.boot.dathinstarterauthorizer.model.exception.CustomAuthenticationException;
import io.github.dathin.boot.dathinstarterauthorizer.model.user.UserToken;
import io.github.dathin.boot.dathinstarterauthorizer.service.AuthenticationService;
import io.github.dathin.boot.dathinstartermodel.exception.GenericException;
import org.springframework.http.HttpHeaders;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class SecurityFilter extends DathinSecurityFilter {


    private final SecurityFilterExceptionHandler securityFilterExceptionHandler;

    private final AuthenticationService authenticationService;

    private final ObjectMapper objectMapper;

    private final UserClient userClient;

    public SecurityFilter(SecurityFilterExceptionHandler securityFilterExceptionHandler, AuthenticationService authenticationService, ObjectMapper objectMapper, UserClient userClient) {
        this.securityFilterExceptionHandler = securityFilterExceptionHandler;
        this.authenticationService = authenticationService;
        this.objectMapper = objectMapper;
        this.userClient = userClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException {
        var token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (token != null) {
                userClient.validateUser();
                setUserFromToken(token);
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (GenericException ex) {
            securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse,
                    new CustomAuthenticationException(ex.getError()));
        } catch (Exception ex) {
            securityFilterExceptionHandler.commence(httpServletRequest, httpServletResponse,
                    new CustomAuthenticationException("Unexpected Error"));
        }
    }

    private void setUserFromToken(String token) throws JsonProcessingException {
        var userToken = getUserFromToken(token);
        authenticationService.setAuthenticatedUser(userToken);
    }

    private UserToken getUserFromToken(String token) throws JsonProcessingException {

        var tokenPayloadBase64 = token.split("\\.")[1];
        var tokenPayload = new String(Base64.getDecoder().decode(tokenPayloadBase64));
        var objectNode = objectMapper.readValue(tokenPayload, ObjectNode.class);
        var userToken = new UserToken();
        userToken.setId(objectNode.get("userId").asInt());
        return userToken;
    }


}
