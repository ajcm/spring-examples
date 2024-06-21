package com.example.webapp;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtUtils {

    public static String getJwtHeader(HttpServletRequest request) {

        var authorization = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);

        if (StringUtils.isNotBlank(authorization) && StringUtils.startsWithIgnoreCase(authorization, SecurityConstants.AUTHORIZATION_BEARER_HEADER)) {
            return StringUtils.stripToEmpty(StringUtils.removeStart(authorization, SecurityConstants.AUTHORIZATION_BEARER_HEADER));
        }

        return StringUtils.EMPTY;
    }


    public static void validateJwt(String jwt) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build();

            Jwt<JwsHeader, Claims> token = jwtParser.parseClaimsJws(jwt);
            Claims claims = token.getBody();
            String username = String.valueOf(claims.get("username"));
            String authorities = (String) claims.get("authorities");

            Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Token received!");
        }
    }

    public static void generateJwtToken(HttpServletResponse response, Authentication authentication) {


        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder().setIssuer("Eazy Bank").setSubject("JWT Token")
                .claim("username", authentication.getName())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key).compact();

        response.setHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.AUTHORIZATION_BEARER_HEADER + " " + jwt);
    }


    private static String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

}
