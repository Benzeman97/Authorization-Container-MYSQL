# Authorization-Container-MYSQL
implemented authorization server with mysql

Endpoints

http://127.0.0.1:9091/oauth/token - to access token

http://127.0.0.1:9091/oauth/check_token?token=<access-token> - to validate the token
  
  
http://127.0.0.1:9091/oauth/token - to get new access token
  
grant_type : refresh_token
refresh_token : <refresh_token>  
