package com.example.graphql.repository;

import com.example.graphql.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {}
