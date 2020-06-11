import React, { useState } from 'react';
import { Alert, Spinner, Button } from 'reactstrap';
import { useQuery } from '@apollo/react-hooks';
import gql from 'graphql-tag';

import { Todo, Query } from 'generated/graphql';
import { TodoItem } from 'ui/modules/todos/components/todo-item';

import classes from './todo-list.module.scss';

export const GET_TODOS = gql`
  query getTodos($skip: Long!) {
    todos(skip: $skip, first: 10) {
      id
      name
      completed
    }
  }
`;

export const TodoList = () => {
  const [showLoadMoreButton, setShowLoadMoreButton] = useState(true);
  const { data, loading, error, fetchMore, networkStatus } = useQuery<{
    todos: Query['todos'];
  }>(GET_TODOS, {
    variables: { skip: 0 },
    notifyOnNetworkStatusChange: true,
    fetchPolicy: 'cache-and-network',
  });

  if (loading && networkStatus !== 3) {
    return (
      <div className='d-flex justify-content-center mt-3'>
        <Spinner type='grow' size='sm' className='mr-1' />
        <Spinner type='grow' size='sm' className='mr-1' />
        <Spinner type='grow' size='sm' />
      </div>
    );
  }

  if (error) {
    return (
      <Alert color='danger' className='mt-3'>
        {JSON.stringify(error)}
      </Alert>
    );
  }

  return (
    <ul className={classes.todosList}>
      {data?.todos?.map((todo) => (
        <TodoItem key={todo?.id!} todo={todo!} />
      ))}
      {showLoadMoreButton && (
        <Button
          disabled={loading}
          color='primary'
          className='w-100 mt-2'
          onClick={() => {
            fetchMore({
              variables: {
                skip: data?.todos?.length ?? 0,
              },
              updateQuery: (prev, { fetchMoreResult }) => {
                if (!fetchMoreResult || fetchMoreResult.todos?.length === 0) {
                  setShowLoadMoreButton(false);
                  return prev;
                }

                setShowLoadMoreButton(true);

                return {
                  ...prev,
                  todos: [
                    ...(prev.todos as Todo[]),
                    ...(fetchMoreResult.todos as Todo[]),
                  ],
                };
              },
            });
          }}>
          {loading ? 'Loading ...' : 'Load More'}
        </Button>
      )}
    </ul>
  );
};
