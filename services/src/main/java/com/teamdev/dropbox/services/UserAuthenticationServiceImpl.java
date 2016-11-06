package com.teamdev.dropbox.services;

import com.teamdev.dropbox.entity.User;
import com.teamdev.dropbox.repository.UserRepository;
import com.teamdev.dropbox.serviceobjects.AuthenticatedUser;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.LoginCredentials;
import com.teamdev.dropbox.util.HashingUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    public static final int THIRTY_MINUTES = 1000 * 60 * 30;
    private final static String secret = "secretkey";

    @Autowired
    UserRepository userRepository;

    @Override
    public AuthenticationToken login(LoginCredentials loginCredentials) throws Exception {
        final User user = userRepository.getByEmail(loginCredentials.email.getValue());

        validateCredentials(user, loginCredentials);

        return generateToken(createClaims(user));
    }

    @Override
    public AuthenticatedUser retrieveUser(AuthenticationToken token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.token)
                .getBody();

        final Long userId = Long.valueOf(claims.getId());
        final String email = claims.getSubject();
        final Date expiration = claims.getExpiration();
        final User user = userRepository.getById(userId);
        if (user != null && email.equals(user.getEmail()) && expiration.getTime() > System.currentTimeMillis()) {
            claims.setExpiration(nextExpirationDate());
            final AuthenticationToken nextToken = generateToken(claims);
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
            return new AuthenticatedUser(userId, user.getName(), nextToken.token, authorityList);
        } else {
            return null;
        }
    }

    private void validateCredentials(User user, LoginCredentials loginCredentials) throws Exception {
        final String originalHash = user.getPasswordHash();
        final String currentHash = HashingUtil.createHash(loginCredentials.password, user.getPasswordSalt());
        if (!originalHash.equals(currentHash)) {
            throw new Exception("Invalid email or password have been provided");
        }
    }

    private AuthenticationToken generateToken(Claims claims) {
        final String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return new AuthenticationToken(token);
    }

    private Claims createClaims(User user) {
        final Claims claims = Jwts.claims()
                .setSubject(user.getEmail())
                .setId(user.getId().toString())
                .setExpiration(nextExpirationDate());

        return claims;
    }

    private Date nextExpirationDate() {
        return new Date(System.currentTimeMillis() + THIRTY_MINUTES);
    }
}
