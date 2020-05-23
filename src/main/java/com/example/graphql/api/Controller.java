package com.example.graphql.api;

import com.example.graphql.model.User;
import com.example.graphql.repository.UserRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final GraphQL graphQL;
  private final UserRepository userRepository;

//  @PostMapping("/query")
//  public ResponseEntity query(@RequestBody String query){
//    ExecutionResult execute = graphQL.execute(query);
//    return ResponseEntity.ok(execute);
//  }


    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }

}
