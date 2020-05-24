package com.example.graphql.resolver;

import com.example.graphql.model.Todo;
import com.example.graphql.repository.TodoRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;


@RequiredArgsConstructor
@GraphQLApi
@Component
public class MutationResolver {

    private final TodoRepository todoRepository;

    @GraphQLMutation
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @GraphQLMutation
    public Map<String,String> removeTodo(String id) {
        todoRepository.deleteById(id);
        return Map.of("id", id);
    }

    @GraphQLMutation
    public Todo completeTodo(String id) {
        return todoRepository.findById(id)
                .map(todo -> todo.withCompleted(!todo.isCompleted()))
                .map(todoRepository::save)
                .get();
    }

}
