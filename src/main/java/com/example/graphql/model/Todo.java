package com.example.graphql.model;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@With
@Builder
@Value
@RequiredArgsConstructor(onConstructor_={@PersistenceConstructor})
public final class Todo {

    String id;

    @NotBlank
    String name;

    String user;

    @GraphQLInputField(defaultValue = "false")
    Boolean completed;

    @NotNull
    Instant created;

}
