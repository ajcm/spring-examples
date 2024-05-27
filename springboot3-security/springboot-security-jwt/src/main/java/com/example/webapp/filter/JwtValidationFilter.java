package com.example.webapp.filter;

import com.example.webapp.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class JwtValidationFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtValidationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJwtHeader(request);

        if (StringUtils.isNotBlank(jwt)) {

            LOG.info("Validate JWT: " + jwt);

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

        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var matcher = new AntPathRequestMatcher("/jwt/**");
        return  matcher.matcher(request).isMatch();
    }

    private String getJwtHeader(HttpServletRequest request) {

        var authorization = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);

        if (StringUtils.isNotBlank(authorization) && StringUtils.startsWithIgnoreCase(authorization, SecurityConstants.AUTHORIZATION_BEARER_HEADER)) {
            return StringUtils.stripToEmpty(StringUtils.removeStart(authorization, SecurityConstants.AUTHORIZATION_BEARER_HEADER));
        }

        return StringUtils.EMPTY;

    }

}
