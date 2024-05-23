

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

