package com.example.graphql.model;

import io.leangen.graphql.annotations.GraphQLInputField;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.PersistenceConstructor;

@Value
@With
@Builder
@RequiredArgsConstructor(onConstructor_={@PersistenceConstructor})
public final class Todo {

    String id;

    String name;

    String user;

    @GraphQLInputField(defaultValue = "false")
    boolean completed;

}
