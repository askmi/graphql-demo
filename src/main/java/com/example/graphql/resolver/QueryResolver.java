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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
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
    public User getUserById(final String id) {
        return userRepository.findById(id).get();
    }

    @GraphQLQuery
    public Slice<Todo> getTodos(@GraphQLArgument(name = "first", defaultValue = "10") final int first,
                                final int skip) {
        final Page<Todo> page = todoRepository.findAll(PageRequest.of(skip / first, first));
        return new SliceImpl<>(page.getContent(), page.getPageable(), page.hasNext());
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
