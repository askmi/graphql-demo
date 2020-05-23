package com.example.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.List;

@Value
@With
@Builder
@RequiredArgsConstructor(onConstructor_={@PersistenceConstructor})
public final class User {

  String id;

  String username;

  String email;

  @Getter(onMethod = @__( {@JsonIgnore, @GraphQLIgnore} ))
  String password;

  String role;

  List<String> users;

}
