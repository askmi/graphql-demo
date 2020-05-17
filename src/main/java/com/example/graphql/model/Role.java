package com.example.graphql.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Role {

  private String id;
  private String name;
  private List<User> users;

}