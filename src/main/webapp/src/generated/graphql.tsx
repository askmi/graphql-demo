import gql from 'graphql-tag';
import * as ApolloReactCommon from '@apollo/react-common';
import * as ApolloReactHooks from '@apollo/react-hooks';
export type Maybe<T> = T | null;
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  /** Built-in scalar for map-like structures */
  Map_String_StringScalar: any;
  /** Built-in scalar representing an instant in time */
  Instant: any;
  /** Unrepresentable type */
  UNREPRESENTABLE: any;
  /** Long type */
  Long: any;
};

export type Todo = {
  __typename?: 'Todo';
  completed?: Maybe<Scalars['Boolean']>;
  created?: Maybe<Scalars['Instant']>;
  id?: Maybe<Scalars['String']>;
  name?: Maybe<Scalars['String']>;
  user?: Maybe<Scalars['String']>;
};


export type User = {
  __typename?: 'User';
  email?: Maybe<Scalars['String']>;
  id?: Maybe<Scalars['String']>;
  role?: Maybe<Role>;
  username?: Maybe<Scalars['String']>;
  users?: Maybe<Array<Maybe<Scalars['String']>>>;
};

/** Query root */
export type Query = {
  __typename?: 'Query';
  todoById?: Maybe<Todo>;
  roles?: Maybe<Array<Maybe<Role>>>;
  userById?: Maybe<User>;
  todos?: Maybe<Array<Maybe<Todo>>>;
  users?: Maybe<Array<Maybe<User>>>;
};


/** Query root */
export type QueryTodoByIdArgs = {
  id?: Maybe<Scalars['String']>;
};


/** Query root */
export type QueryUserByIdArgs = {
  id?: Maybe<Scalars['String']>;
};


/** Query root */
export type QueryTodosArgs = {
  skip: Scalars['Long'];
  first?: Maybe<Scalars['Int']>;
};


export type TodoInput = {
  user?: Maybe<Scalars['String']>;
  name?: Maybe<Scalars['String']>;
  completed?: Maybe<Scalars['Boolean']>;
  id?: Maybe<Scalars['String']>;
  created?: Maybe<Scalars['Instant']>;
};

export enum Role {
  Admin = 'ADMIN',
  User = 'USER'
}



/** Mutation root */
export type Mutation = {
  __typename?: 'Mutation';
  completeTodo?: Maybe<Todo>;
  removeTodo?: Maybe<Scalars['Map_String_StringScalar']>;
  addTodo?: Maybe<Todo>;
};


/** Mutation root */
export type MutationCompleteTodoArgs = {
  id?: Maybe<Scalars['String']>;
};


/** Mutation root */
export type MutationRemoveTodoArgs = {
  id?: Maybe<Scalars['String']>;
};


/** Mutation root */
export type MutationAddTodoArgs = {
  todo?: Maybe<TodoInput>;
};

export type AddTodoMutationVariables = {
  name: Scalars['String'];
};


export type AddTodoMutation = (
  { __typename?: 'Mutation' }
  & { addTodo?: Maybe<(
    { __typename?: 'Todo' }
    & Pick<Todo, 'id' | 'name' | 'completed'>
  )> }
);

export type RemoveTodoMutationVariables = {
  id: Scalars['String'];
};


export type RemoveTodoMutation = (
  { __typename?: 'Mutation' }
  & Pick<Mutation, 'removeTodo'>
);

export type CompleteTodoMutationVariables = {
  id: Scalars['String'];
};


export type CompleteTodoMutation = (
  { __typename?: 'Mutation' }
  & { completeTodo?: Maybe<(
    { __typename?: 'Todo' }
    & Pick<Todo, 'id' | 'name' | 'completed'>
  )> }
);

export type GetTodosQueryVariables = {
  skip: Scalars['Long'];
};


export type GetTodosQuery = (
  { __typename?: 'Query' }
  & { todos?: Maybe<Array<Maybe<(
    { __typename?: 'Todo' }
    & Pick<Todo, 'id' | 'name' | 'completed'>
  )>>> }
);


export const AddTodoDocument = gql`
    mutation AddTodo($name: String!) {
  addTodo(todo: {name: $name}) {
    id
    name
    completed
  }
}
    `;
export type AddTodoMutationFn = ApolloReactCommon.MutationFunction<AddTodoMutation, AddTodoMutationVariables>;

/**
 * __useAddTodoMutation__
 *
 * To run a mutation, you first call `useAddTodoMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useAddTodoMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [addTodoMutation, { data, loading, error }] = useAddTodoMutation({
 *   variables: {
 *      name: // value for 'name'
 *   },
 * });
 */
export function useAddTodoMutation(baseOptions?: ApolloReactHooks.MutationHookOptions<AddTodoMutation, AddTodoMutationVariables>) {
        return ApolloReactHooks.useMutation<AddTodoMutation, AddTodoMutationVariables>(AddTodoDocument, baseOptions);
      }
export type AddTodoMutationHookResult = ReturnType<typeof useAddTodoMutation>;
export type AddTodoMutationResult = ApolloReactCommon.MutationResult<AddTodoMutation>;
export type AddTodoMutationOptions = ApolloReactCommon.BaseMutationOptions<AddTodoMutation, AddTodoMutationVariables>;
export const RemoveTodoDocument = gql`
    mutation RemoveTodo($id: String!) {
  removeTodo(id: $id)
}
    `;
export type RemoveTodoMutationFn = ApolloReactCommon.MutationFunction<RemoveTodoMutation, RemoveTodoMutationVariables>;

/**
 * __useRemoveTodoMutation__
 *
 * To run a mutation, you first call `useRemoveTodoMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useRemoveTodoMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [removeTodoMutation, { data, loading, error }] = useRemoveTodoMutation({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useRemoveTodoMutation(baseOptions?: ApolloReactHooks.MutationHookOptions<RemoveTodoMutation, RemoveTodoMutationVariables>) {
        return ApolloReactHooks.useMutation<RemoveTodoMutation, RemoveTodoMutationVariables>(RemoveTodoDocument, baseOptions);
      }
export type RemoveTodoMutationHookResult = ReturnType<typeof useRemoveTodoMutation>;
export type RemoveTodoMutationResult = ApolloReactCommon.MutationResult<RemoveTodoMutation>;
export type RemoveTodoMutationOptions = ApolloReactCommon.BaseMutationOptions<RemoveTodoMutation, RemoveTodoMutationVariables>;
export const CompleteTodoDocument = gql`
    mutation CompleteTodo($id: String!) {
  completeTodo(id: $id) {
    id
    name
    completed
  }
}
    `;
export type CompleteTodoMutationFn = ApolloReactCommon.MutationFunction<CompleteTodoMutation, CompleteTodoMutationVariables>;

/**
 * __useCompleteTodoMutation__
 *
 * To run a mutation, you first call `useCompleteTodoMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCompleteTodoMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [completeTodoMutation, { data, loading, error }] = useCompleteTodoMutation({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useCompleteTodoMutation(baseOptions?: ApolloReactHooks.MutationHookOptions<CompleteTodoMutation, CompleteTodoMutationVariables>) {
        return ApolloReactHooks.useMutation<CompleteTodoMutation, CompleteTodoMutationVariables>(CompleteTodoDocument, baseOptions);
      }
export type CompleteTodoMutationHookResult = ReturnType<typeof useCompleteTodoMutation>;
export type CompleteTodoMutationResult = ApolloReactCommon.MutationResult<CompleteTodoMutation>;
export type CompleteTodoMutationOptions = ApolloReactCommon.BaseMutationOptions<CompleteTodoMutation, CompleteTodoMutationVariables>;
export const GetTodosDocument = gql`
    query getTodos($skip: Long!) {
  todos(skip: $skip, first: 10) {
    id
    name
    completed
  }
}
    `;

/**
 * __useGetTodosQuery__
 *
 * To run a query within a React component, call `useGetTodosQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetTodosQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetTodosQuery({
 *   variables: {
 *      skip: // value for 'skip'
 *   },
 * });
 */
export function useGetTodosQuery(baseOptions?: ApolloReactHooks.QueryHookOptions<GetTodosQuery, GetTodosQueryVariables>) {
        return ApolloReactHooks.useQuery<GetTodosQuery, GetTodosQueryVariables>(GetTodosDocument, baseOptions);
      }
export function useGetTodosLazyQuery(baseOptions?: ApolloReactHooks.LazyQueryHookOptions<GetTodosQuery, GetTodosQueryVariables>) {
          return ApolloReactHooks.useLazyQuery<GetTodosQuery, GetTodosQueryVariables>(GetTodosDocument, baseOptions);
        }
export type GetTodosQueryHookResult = ReturnType<typeof useGetTodosQuery>;
export type GetTodosLazyQueryHookResult = ReturnType<typeof useGetTodosLazyQuery>;
export type GetTodosQueryResult = ApolloReactCommon.QueryResult<GetTodosQuery, GetTodosQueryVariables>;