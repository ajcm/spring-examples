package com.example.webapp.controller;

import com.example.webapp.dto.JwtResponse;
import com.example.webapp.dto.LoginRequest;
import com.example.webapp.dto.SignupRequest;
import com.example.webapp.model.AuthGrantedAuthority;
import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.repository.AuthAuthorityRepository;
import com.example.webapp.repository.AuthUserRepository;
import com.example.webapp.security.SecurityService;
import com.example.webapp.security.TokenService;
import com.example.webapp.service.JpaUserDetailsManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    private static Logger LOG = LoggerFactory.getLogger(AuthorizationController.class);


    @Autowired
    JpaUserDetailsManager jpaUserDetailsManager;

    @Autowired
    AuthUserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthAuthorityRepository authAuthorityRepository;

    @CrossOrigin
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            var optionalAuthUserDetails = securityService.getAuthUserDetails();

            if (optionalAuthUserDetails.isEmpty()) {
                throw new BadCredentialsException("Cannot find username.");
            }

            JwtResponse jwtResponse = tokenService.getJwtResponse(optionalAuthUserDetails.get());

            return ResponseEntity.ok(jwtResponse);


        } catch (BadCredentialsException ex) {
            LOG.info("Bad credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

    }


    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody SignupRequest request) {


        //
        var optionalAuthUserDetails = userRepository.findByEmail(request.getEmail());

        if (optionalAuthUserDetails.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already exists");
        }

        UUID uuid = UUID.randomUUID();

        AuthUserDetails userDetails = new AuthUserDetails();

        userDetails.setName(request.getName());
        userDetails.setEmail(request.getEmail());
        userDetails.setPassword(jpaUserDetailsManager.encryptPassword(request.getPassword()));
        userDetails.setEnabled(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);

        AuthGrantedAuthority userGrant = new AuthGrantedAuthority(AuthGrantedAuthority.ROLE_USER, userDetails);

        userDetails.setAuthorities(Set.of(userGrant));

        authUserRepository.save(userDetails);
        authAuthorityRepository.saveAll(Set.of(userGrant));

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
