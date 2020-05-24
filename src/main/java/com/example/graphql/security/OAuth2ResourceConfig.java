//package com.example.graphql.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManagerResolver;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
//import org.springframework.security.oauth2.server.resource.authentication.*;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
//import org.springframework.security.web.savedrequest.NullRequestCache;
//
//import java.time.Instant;
//import java.util.Map;
//
///***
// * See {@link OAuth2ResourceServerConfigurer}
// *
// * See {@link BearerTokenAuthenticationFilter}
// * See {@link BearerTokenResolver}
// * See {@link BearerTokenAuthenticationToken}
// *
// * See {@link AuthenticationManagerResolver}
// * See {@link ProviderManager}
// * See {@link JwtAuthenticationProvider}
// *
// * See {@link JwtDecoder}
// * See {@link Jwt}
// * See {@link JwtAuthenticationConverter}
// * See {@link JwtGrantedAuthoritiesConverter}
// * See {@link JwtAuthenticationToken}
// *
// *
// * See {@link JwtBearerTokenAuthenticationConverter}
// * See {@link OAuth2AccessToken}
// * See {@link JwtAuthenticationToken}
// * See {@link OAuth2AuthenticatedPrincipal}
// */
//@Profile("secure")
//@Configuration
//public class OAuth2ResourceConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return token -> new Jwt(token,
//                Instant.now(),
//                Instant.MAX,
//                Map.of("alg","HS256","typ","JWT"),
//                Map.of("name","user","scope","API"));
////        return new NimbusJwtDecoder(new DefaultJWTProcessor<>());
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().disable()
//                .authorizeRequests(auth -> auth
//                        .antMatchers("/graphql**").authenticated())
//                .requestCache(cache -> cache.requestCache(new NullRequestCache()))
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//    }
//}
