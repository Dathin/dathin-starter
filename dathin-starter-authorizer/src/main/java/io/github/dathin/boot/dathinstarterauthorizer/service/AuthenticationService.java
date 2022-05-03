package io.github.dathin.boot.dathinstarterauthorizer.service;

import io.github.dathin.boot.dathinstarterauthorizer.model.exception.UnauthorizedException;
import io.github.dathin.boot.dathinstarterauthorizer.model.user.UserToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationService {

    public Optional<UserToken> getAuthenticatedUser() {
        var userToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userToken instanceof UserToken) {
            return Optional.of((UserToken) userToken);
        }
        return Optional.empty();
    }

    public UserToken getAuthenticatedUserOrThrow() {
        return getAuthenticatedUser().orElseThrow(UnauthorizedException::new);
    }

    public void setAuthenticatedUser(UserToken userToken) {
        Authentication authentication = new PreAuthenticatedAuthenticationToken(userToken, null,
                Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
