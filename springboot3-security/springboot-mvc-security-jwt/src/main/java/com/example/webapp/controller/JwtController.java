package com.example.webapp.controller;


import com.example.webapp.model.AuthUserDetails;
import com.example.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt/")
public class JwtController {

    @Autowired
    private SecurityService securityService;


    @GetMapping("id")
    public ResponseEntity<String> getId() {

        String principal = "";
        //token is set in header by filter
        var object = securityService.getAuthentication().getPrincipal();

        if (object instanceof String) {
            principal = (String) object;

        } else if (object instanceof AuthUserDetails) {
            principal = ((AuthUserDetails) object).getUsername();

        } else {
            principal = object.toString();
        }

        return ResponseEntity.ok(principal);
    }

    @GetMapping("token")
    public ResponseEntity<Void> getToken() {
        //token is set in header by filter
        Authentication authentication = securityService.getAuthentication();

        if (authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.noContent().build();
    }


    @Secured("ROLE_USER")
    @GetMapping("info")
    public ResponseEntity<AuthUserDetails> getUserDetails() {
        var optionalAuthUserDetails = securityService.getAuthUserDetails();

        return ResponseEntity.of(optionalAuthUserDetails);
    }

    @GetMapping("ping")
    public String ping() {
        return "ping";
    }

}
