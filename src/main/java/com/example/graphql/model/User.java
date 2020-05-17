package com.example.graphql.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

  private String id;
  private String username;
  private String password;
  private List<Role> roles;
  private List<String> users;


}