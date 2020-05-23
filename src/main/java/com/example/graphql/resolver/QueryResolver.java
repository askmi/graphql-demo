package com.example.graphql.resolver;

import com.example.graphql.model.Todo;
import com.example.graphql.model.User;
import com.example.graphql.repository.TodoRepository;
import com.example.graphql.repository.UserRepository;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@GraphQLApi
@Component
public class QueryResolver {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @GraphQLQuery
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GraphQLQuery
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    @GraphQLQuery
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @GraphQLQuery
    public Todo getTodoById(String id) {
        return todoRepository.findById(id).get();
    }

}
