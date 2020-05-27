package com.example.graphql.resolver;

import com.example.graphql.model.Role;
import com.example.graphql.model.Todo;
import com.example.graphql.model.User;
import com.example.graphql.repository.TodoRepository;
import com.example.graphql.repository.UserRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RequiredArgsConstructor
@GraphQLApi
@Component
public class QueryResolver {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final MongoOperations mongoOperations;

    @GraphQLQuery
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GraphQLQuery
    public User getUserById(final String id) {
        return userRepository.findById(id).get();
    }

    @GraphQLQuery
    public List<Todo> getTodos(@GraphQLArgument(name = "first", defaultValue = "10") final int first,
                                final long skip) {
        Query query = Query.query(new Criteria())
                .skip(skip)
                .limit(first)
                .with(Sort.by(DESC, "created"));
        return mongoOperations.find(query, Todo.class);
    }

    @GraphQLQuery
    public Todo getTodoById(final String id) {
        return todoRepository.findById(id).get();
    }

    @GraphQLQuery
    public List<Role> getRoles() {
        return List.of(Role.values());
    }

}
