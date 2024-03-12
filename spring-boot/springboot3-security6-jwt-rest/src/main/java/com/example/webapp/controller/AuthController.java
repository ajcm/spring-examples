package com.example.webapp.controller;

import com.example.webapp.dto.JwtResponse;
import com.example.webapp.dto.LoginRequest;
import com.example.webapp.security.SecurityConstants;
import com.example.webapp.security.SecurityService;
import com.example.webapp.security.TokenService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static Logger LOG = LoggerFactory.getLogger(AuthController.class);


//  @Autowired
//  AuthenticationManager authenticationManager;

//  @Autowired
//  UserRepository userRepository;
//
//  @Autowired
//  RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    private SecurityService securityService;


    @Autowired
    AuthenticationManager authenticationManager;


    @CrossOrigin
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

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


    @GetMapping("identity")
    public String getIdentity() {

        var optionalAuthUserDetails = securityService.getAuthUserDetails();

        if (optionalAuthUserDetails.isPresent()) {
            return optionalAuthUserDetails.get().getName();
        }

        var auth = securityService.getAuthentication();

        return auth.getName();
    }

//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }
//
//    // Create new user's account
//    User user = new User(signUpRequest.getUsername(),
//               signUpRequest.getEmail(),
//               encoder.encode(signUpRequest.getPassword()));
//
//    Set<String> strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
//
//    userRepository.save(user);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//  }


}
