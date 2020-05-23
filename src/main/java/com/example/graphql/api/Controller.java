package com.example.graphql.api;

import com.example.graphql.model.User;
import com.example.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/me")
    public Authentication me(@CurrentSecurityContext(expression = "authentication.principal") JwtAuthenticationToken authentication) {
        return authentication;
    }

}

/*
  private final GraphQL graphQL;

  @PostMapping("/query")
  public ResponseEntity query(@RequestBody String query){
    ExecutionResult execute = graphQL.execute(query);
    return ResponseEntity.ok(execute);
  }*/
