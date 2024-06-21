package com.example.webapp.filter;

import com.example.webapp.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtGeneratorFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtGeneratorFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var sc = SecurityContextHolder
                .getContextHolderStrategy();

        String jwt = JwtUtils.getJwtHeader(request);

        if (StringUtils.isNotBlank(jwt)) {
            LOG.info("Validate JWT: " + jwt);
            JwtUtils.validateJwt(jwt);

            //check for current auth
        } else if (sc.getContext() != null && sc.getContext().getAuthentication() != null) {
            LOG.info("Generate JWT: " + jwt);
            var authentication = sc.getContext().getAuthentication();
            JwtUtils.generateJwtToken(response, authentication);
        }

        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var matcher = new AntPathRequestMatcher("/jwt/**");
        return !matcher.matcher(request).isMatch();
    }


}
