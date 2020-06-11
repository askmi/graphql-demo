package com.example.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@With
@Builder
@RequiredArgsConstructor(onConstructor_={@PersistenceConstructor})
public final class User {

  @GraphQLNonNull
  String id;

  @GraphQLNonNull
  String username;

  @GraphQLNonNull
  String email;

  @GraphQLIgnore
  @Getter(onMethod = @__(@JsonIgnore))
  String password;

  Role role;

  List<@NotNull String> users;

}
