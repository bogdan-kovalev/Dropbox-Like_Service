package com.teamdev.dropbox.services;

import com.teamdev.dropbox.entity.User;
import com.teamdev.dropbox.repository.UserRepository;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.UserLoginInfo;
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
    public AuthenticationToken login(UserLoginInfo loginInfo) throws Exception {
        final User user = userRepository.getByEmail(loginInfo.email);
        final Claims claims = Jwts.claims().setSubject(user.getEmail());

        final String originalHash = user.getPasswordHash();
        final String currentHash = HashingUtil.createHash(loginInfo.password, user.getPasswordSalt());

        if (!originalHash.equals(currentHash)) {
            return null;
        }

        claims.setId(user.getId());
        claims.put("hash", originalHash);

        final String token = Jwts.builder()
                .setClaims(claims)
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
}
