package com.example.graphql;

import com.example.graphql.model.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static java.lang.System.out;

@SpringBootApplication
public class GraphqlApplication {
  
  
  public static void main(String[] args) {
    SpringApplication.run(GraphqlApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(MongoOperations mongoOperations){
    return args -> {
      mongoOperations.dropCollection(User.class);
      mongoOperations.insertAll(List.of(
          User.builder().username("user").password("user").role("USER").build(),
          User.builder().username("user1").password("user").role("USER").build(),
          User.builder().username("user3").password("user").role("USER").build(),
          User.builder().username("user4").password("user").role("USER").build(),
          User.builder().username("user5").password("user").role("USER").build(),
          User.builder().username("user6").password("user").role("USER").build(),
          User.builder().username("user7").password("user").role("USER").build(),
          User.builder().username("user8").password("user").role("USER").build(),
          User.builder().username("user9").password("user").role("USER").build(),
          User.builder().username("user10").password("user").role("USER").build(),
          User.builder().username("admin").password("admin").role("ADMIN").build()
      ));
      mongoOperations.findAll(User.class).forEach(out::println);
    };
  }
  
}

//  @Value("classpath:schema.graphqls")
//  final Resource resource;
//  final MongoOperations mongoOperations;

//  @Bean
//  public GraphQLResolver<User> graphQLResolver(){
//    return new GraphQLResolver<User>(){
//      public List<Role> getRoles(User user) {
//        return mongoOperations.findAll(Role.class);
//      }
//    };
//  }
//
//  @Bean
//  public GraphQLMutationResolver graphQLMutationResolver(){
//    return new GraphQLMutationResolver(){
//      public User save(String username,String password){
//        return mongoOperations.insert(User.builder().username(username).password(password));
//      }
//    };
//  }
//
//  @Bean
//  public GraphQLQueryResolver graphQLQueryResolver(){
//    return new GraphQLQueryResolver() {
//      public List<User> users() {
//        return mongoOperations.findAll(User.class);
//      }
//      public User user(String username) {
//        return mongoOperations.findOne(Query.query(Criteria.where("username").is(username)), User.class);
//      }
//    };
//  }

  //  @Bean
//  public GraphQL graphQL() throws IOException {
//    var typeDefinitionRegistry = new SchemaParser().parse(new InputStreamReader(resource.getInputStream()));
//    var r = RuntimeWiring.newRuntimeWiring()
//            .type("Query", builder -> builder
//                            .dataFetcher("users", env -> mongoOperations.findAll(User.class))
//                            .dataFetcher("user", env -> User.builder().username(env.getArgument("username")))
////            * See http://graphql.org/learn/schema/#object-types-and-fields for more details on the concept.
////            .typeResolver(env -> System.err.println("TYPE RESOLVER: " + env))
//            )
//            .build();
//
//    var graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, r);
//    return GraphQL.newGraphQL(graphQLSchema).build();
//  }


//  @Bean
//  public ServletRegistrationBean servletRegistrationBean() {
//    GraphQLSchema schema  = SchemaParser.newParser().resolvers(new Query(),new Mutation()).file("schema.graphqls").build().makeExecutableSchema();
//    ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
//    GraphQLServlet servlet = new SimpleGraphQLServlet(schema, executionStrategy);
//    ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/graphql");
//    return bean;
//  }
