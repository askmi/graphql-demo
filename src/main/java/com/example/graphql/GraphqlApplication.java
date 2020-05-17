package com.example.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Role;
import com.example.graphql.model.User;
import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.err;

@RequiredArgsConstructor
@SpringBootApplication
public class GraphqlApplication {

  @Value("classpath:schema.graphqls")
  final Resource resource;
  final MongoOperations mongoOperations;


  @Bean
  public GraphQLResolver<User> graphQLResolver(){
    return new GraphQLResolver<User>(){
      public List<Role> getRoles(User user) {
        return mongoOperations.findAll(Role.class);
      }
    };
  }

  @Bean
  public GraphQLMutationResolver graphQLMutationResolver(){
    return new GraphQLMutationResolver(){
      public User save(String username,String password){
        return mongoOperations.insert(new User().setUsername(username).setPassword(password));
      }
    };
  }

  @Bean
  public GraphQLQueryResolver graphQLQueryResolver(){
    return new GraphQLQueryResolver() {
      public List<User> users() {
        return mongoOperations.findAll(User.class);
      }
      public User user(String username) {
        return mongoOperations.findOne(Query.query(Criteria.where("username").is(username)), User.class);
      }
    };
  }

//  @Bean
  public GraphQL graphQL() throws IOException {
    var typeDefinitionRegistry = new SchemaParser().parse(new InputStreamReader(resource.getInputStream()));
    var r = RuntimeWiring.newRuntimeWiring()
        .type("Query", builder -> builder
            .dataFetcher("users", env -> mongoOperations.findAll(User.class))
            .dataFetcher("user", env -> new User().setUsername(env.getArgument("username")))
//            * See http://graphql.org/learn/schema/#object-types-and-fields for more details on the concept.
//            .typeResolver(env -> System.err.println("TYPE RESOLVER: " + env))
        )
        .build();

    var graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, r);
    return GraphQL.newGraphQL(graphQLSchema).build();
  }


  public static void main(String[] args) {
    SpringApplication.run(GraphqlApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(){
    return args -> {
      mongoOperations.dropCollection(User.class);
      mongoOperations.dropCollection(Role.class);
      mongoOperations.insertAll(List.of(
          new User().setUsername("user").setPassword("user"),
          new User().setUsername("user1").setPassword("user"),
          new User().setUsername("user3").setPassword("user"),
          new User().setUsername("user4").setPassword("user"),
          new User().setUsername("user5").setPassword("user"),
          new User().setUsername("user6").setPassword("user"),
          new User().setUsername("user7").setPassword("user"),
          new User().setUsername("user8").setPassword("user"),
          new User().setUsername("user9").setPassword("user"),
          new User().setUsername("user10").setPassword("user"),
          new User().setUsername("admin").setPassword("admin")
      ));
      mongoOperations.insertAll(List.of(
          new Role().setName("USER"),
          new Role().setName("ADMIN")
      ));
      mongoOperations.findAll(User.class).forEach(err::println);
      mongoOperations.findAll(Role.class).forEach(err::println);

    };
  }
}

//  @Bean
//  public ServletRegistrationBean servletRegistrationBean() {
//    GraphQLSchema schema  = SchemaParser.newParser().resolvers(new Query(),new Mutation()).file("schema.graphqls").build().makeExecutableSchema();
//    ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
//    GraphQLServlet servlet = new SimpleGraphQLServlet(schema, executionStrategy);
//    ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/graphql");
//    return bean;
//  }
