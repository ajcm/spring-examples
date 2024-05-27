## Spring MVC JPA JWT

- Example with MVC interface and DaoAuthenticationProvider
- Custom AuthenticationProvider (CustomAuthenticationProvider)
- Domain classes AuthUserDetails/AuthGrantedAuthorityRepository
- MessageJdbcService with JDBC Template
- H2 Console

## Rest interface
User two filters JwtGeneratorFilter to generate token.
JwtValidationFilter validated JWT Token if present.
JWT Token: (Authorization: Bearer token)

### /jwt/login
Creates a login by sending a Http Basic Authentication request.
Handled by JwtGeneratorFilter.


### /jwt/token
Void method to check jwt credentials

### /jwt/info
Retrieves user session

## Login with HTTP Basic Authorization

Header Basic
username + password

Authorization: Basic xxxxx

Response example:
Authorization Bearer eyJhbGciOiJI.....

Requires:

    http.httpBasic(Customizer.withDefaults());

## Login with JWT

Bearer Token
jwt

Authorization: Bearer xxxxx

#