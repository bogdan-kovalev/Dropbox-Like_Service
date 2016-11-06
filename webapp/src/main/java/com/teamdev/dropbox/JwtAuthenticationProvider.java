package com.teamdev.dropbox;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        try {
            final UserDTO userDTO = userAuthenticationService.retrieveUser(new AuthenticationToken(token));
            if (userDTO == null) {
                throw new JwtTokenMalformedException("JWT token is not valid");
            }
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
            return new AuthenticatedUser(userDTO.id, userDTO.name, token, authorityList);
        } catch (Exception e) {
            throw new JwtTokenMalformedException(e.getMessage());
        }
    }

}
