# cb-auth2-simple
An simple demo project to test out Oauth2 using spring.

### Client 1 - hallmark

`.withClient("hallmark")` <br /> 
&nbsp;&nbsp;&nbsp;    `.authorizedGrantTypes("client_credentials", "password")` <br /> 
&nbsp;&nbsp;&nbsp;    `.authorities("ROLE_CLIENT")` <br /> 
&nbsp;&nbsp;&nbsp;    `.scopes("read", "write", "trust")` <br /> 
&nbsp;&nbsp;&nbsp;    `.resourceIds("cboauth2/secret")` <br /> 
&nbsp;&nbsp;&nbsp;    `.secret("secret_password");` <br /> 

##### Get Access token - Client Credentials with hallmark/secret_password

Oauth2 token url - ``http://localhost:11001/oauth/token?grant_type=client_credentials``<br />
Header (Base64 encoded - hallmark:secret_password) - ``Authorization: Basic aGFsbG1hcms6c2VjcmV0X3Bhc3N3b3Jk``

``curl -v -X POST http://localhost:11001/oauth/token -H "Authorization: Basic aGFsbG1hcms6c2VjcmV0X3Bhc3N3b3Jk" -d "grant_type=client_credentials"``

##### Response with access token

``{``<br /> 
&nbsp;&nbsp;&nbsp;    ``"access_token": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiY2JvYXV0aDIvc2VjcmV0Il0sInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImV4cCI6MTQ3MzMyMDcwNywiYXV0aG9yaXRpZXMiOlsiUk9MRV9DTElFTlQiXSwianRpIjoiYjgwYjJmNWUtMWJjYi00NGY2LWI4N2EtMmIzM2NlODNhYTc4IiwiY2xpZW50X2lkIjoiaGFsbG1hcmsifQ.mVhvq5gubo-CllgwpGE4ig-1hJyyiUfC1e9_XVAOe18",``<br /> 
&nbsp;&nbsp;&nbsp;    ``"token_type": "bearer",``<br /> 
&nbsp;&nbsp;&nbsp;    ``"expires_in": 119,``<br /> 
&nbsp;&nbsp;&nbsp;    ``"scope": "read\"  \"write\"  \"trust",``<br /> 
&nbsp;&nbsp;&nbsp;    ``"jti": "84c6b41f-5a47-47e8-b9fb-dc5197e32735"``<br /> 
``}``

``curl -v -X POST http://localhost:11001/hello -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiY2JvYXV0aDIvc2VjcmV0Il0sInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImV4cCI6MTQ3MzMyMDcwNywiYXV0aG9yaXRpZXMiOlsiUk9MRV9DTElFTlQiXSwianRpIjoiYjgwYjJmNWUtMWJjYi00NGY2LWI4N2EtMmIzM2NlODNhYTc4IiwiY2xpZW50X2lkIjoiaGFsbG1hcmsifQ.mVhvq5gubo-CllgwpGE4ig-1hJyyiUfC1e9_XVAOe18" -d "name=User"``

``curl -v -X GET http://localhost:11001/oauthserver/user -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiY2JvYXV0aDIvc2VjcmV0Il0sInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImV4cCI6MTQ3MzMyMDcwNywiYXV0aG9yaXRpZXMiOlsiUk9MRV9DTElFTlQiXSwianRpIjoiYjgwYjJmNWUtMWJjYi00NGY2LWI4N2EtMmIzM2NlODNhYTc4IiwiY2xpZW50X2lkIjoiaGFsbG1hcmsifQ.mVhvq5gubo-CllgwpGE4ig-1hJyyiUfC1e9_XVAOe18"``
