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
Creates a new session

### /jwt/token

Void method to check jwt credentials.

### /jwt/info

Retrieves user details.

## JWT Generations

Uses a BasicAuthentication request to perform auth with
username and password.
Header: Authorization: Basic xxxxx

## JWT

Paths: /jwt/**
Header: Authorization: Bearer Token

