package com.teamdev.dropbox.services;

import com.teamdev.dropbox.entity.User;
import com.teamdev.dropbox.repository.UserRepository;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.LoginCredentials;
import com.teamdev.dropbox.util.HashingUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final static String secret = "secretkey";

    @Autowired
    UserRepository userRepository;

    @Override
    public AuthenticationToken login(LoginCredentials loginCredentials) throws Exception {
        final User user = userRepository.getByEmail(loginCredentials.email);

        validateCredentials(user, loginCredentials);

        final String token = Jwts.builder()
                .setClaims(createClaims(user))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return new AuthenticationToken(token);
    }

    @Override
    public boolean isTokenValid(AuthenticationToken token) throws Exception {
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.token)
                .getBody();

        final String userId = body.getId();
        final String passwordHash = String.valueOf(body.get("hash"));
        final User user = userRepository.getById(userId);
        return user.getPasswordHash().equals(passwordHash);
    }

    private void validateCredentials(User user, LoginCredentials loginCredentials) throws Exception {
        final String originalHash = user.getPasswordHash();
        final String currentHash = HashingUtil.createHash(loginCredentials.password, user.getPasswordSalt());
        if (!originalHash.equals(currentHash)) {
            throw new Exception("Invalid email or password have been provided");
        }
    }

    private Claims createClaims(User user) {
        final Claims claims = Jwts.claims()
                .setSubject(user.getEmail())
                .setId(user.getId());

        claims.put("hash", user.getPasswordHash());
        return claims;
    }
}
