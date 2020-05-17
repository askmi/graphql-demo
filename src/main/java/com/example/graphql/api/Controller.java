package com.example.graphql.api;

import com.example.graphql.model.User;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

//  private final GraphQL graphQL;

  @PostMapping("/query")
  public User query(@RequestBody String query){
    return new User();
//    ExecutionResult execute = graphQL.execute(query);
//    return ResponseEntity.ok(execute);
  }

}
