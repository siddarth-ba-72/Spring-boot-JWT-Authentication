# Spring Boot Authentication Using JWT

## Basics

- When we add the spring boot security dependency, every endpoint in the application is automatically secured
- Spring security generates a username (`user`) and password (generated)
- We can override the default username and password from the `application.yml` / `application.properties` file
```
spring:
  security:
    user:
      name: username
      password: password
```

- We can also create an In Memory user
  - Create a configuration file and create a bean of `UserDetailsService`
  - Two additional beans : `PasswordEncoder` and `AuthenticationManager`
- `User` is the in memory user that spring security provides, it implements `UserDetailsService` interface
```
@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("username")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
```
- `Principal` represents the object of the logged-in user

## JWT Authentication

- JWT : Json Web Token
- Overall : Each user is assigned a token, and the user needs to verify the token to access the apis/application
- JWT has three parts : x.y.z
  - x : Represents algorithm and token type (Header)
  - y : Represents the payload
  - z : Represents verify signature
- This token is assigned to a client

- Workflow:
  - `POST/ authenticate` http call with `username` and `password` from the client
  - Server validates the `username` and `password` and generates and returns the `JWT` secret key in the response
  - Client stores the JWT secret key
  - Now if any calls happens, client will send the JWT token in the Request Headers
  - Server will validate the JWT secret key and send the response

## Steps to implement JWT token

- Make sure `spring-boot-starter-security` is implemented
- Create a class that implements `AuthenticationEntryPoint` interface
- Example : `com.jwt.auth.security.JwtAuthenticationEntryPoint`
- Method of this class is called whenever an exception is thrown due to unauthenticated access - `commence` method is called
- Create a class, which contains methods related to perform operations with JWT token
- Example : `com.jwt.auth.security.JwtHelper`
  - A variable to define the duration of the JWT token
  - Jwt secret key
  - Method to get username from the token
  - Method to get expiration date from the token
  - Method to get the claim from the token
  - Method to get all claims from the token
  - Method to check if the token is expired
  - Method to generate token
  - Method to get the sign key
  - Method to validate the token
- Create a class that extends `OncePerRequestFilter` and override method and write logic to check the token that is coming in the header whenever an api/endpoint is called
- Example : `com.jwt.auth.security.JwtAuthenticationFilter`
- We have to implement 5 important logic
  - Get token from the request headers
  - Validate token
  - Get username from the token
  - Load user associated with this token
  - Set authentication
- `SecurityContextHolder` has all the security context of the application, for example it has the context about the authentication
- We always check this class for the authentication