## Spring MVC JPA JWT
- example with Mvc interface and DaoAuthenticationProvider
- custom CustomAuthenticationProvider (CustomAuthenticationProvider)
- AuthUserDetails/AuthGrantedAuthorityRepository
- MessageJdbcService with JDBC Template
- H2 Console




### Rest interface
Test with rest
ex:
http://localhost:8080/rest/auth/id

## Login with HTTP Basic Authorization
Header Basic 
username + password

Authorization: Basic xxxxx

Response example:
Authorization	Bearer eyJhbGciOiJI.....

Requires:

    http.httpBasic(Customizer.withDefaults());


## Login with JWT
Bearer Token
jwt

Authorization: Bearer xxxxx

#