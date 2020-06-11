import React, { useState } from 'react';
import { Input, Button, Alert } from 'reactstrap';
import { useMutation } from '@apollo/react-hooks';
import gql from 'graphql-tag';

import { Query } from 'generated/graphql';

import { GET_TODOS } from 'ui/modules/todos/components/todo-list';

import classes from './add-todo.module.scss';

const ADD_TODO = gql`
  mutation AddTodo($name: String!, $created: Instant!) {
    addTodo(todo: { name: $name, created: $created }) {
      id
      name
      completed
      created
    }
  }
`;

export const AddTodo = () => {
  const [todo, setTodo] = useState('');
  const [addTodo, { error, loading }] = useMutation(ADD_TODO, {
    update(cache, { data: { addTodo } }) {
      if (!addTodo) {
        return null;
      }
      const variables = { skip: 0, first: 10 };

      const response = cache.readQuery<{ todos: Query['todos'] }>({
        query: GET_TODOS,
        variables,
      });

      const todos = response?.todos || [];

      cache.writeQuery({
        query: GET_TODOS,
        data: { todos: [addTodo, ...todos] },
        variables,
      });
    },
  });

  if (error) {
    return <Alert color='danger'>{JSON.stringify(error)}</Alert>;
  }

  return (
    <form
      className='d-flex'
      onSubmit={(event: React.FormEvent) => {
        event.preventDefault();

        addTodo({
          variables: {
            name: todo,
            created: Date.now(),
          },
        });

        setTodo('');
      }}>
      <Input
        disabled={loading}
        className={classes.addTodoInput}
        name='todo'
        placeholder='Enter todo'
        value={todo}
        onChange={(event) => setTodo(event.target.value)}
      />
      <Button
        disabled={todo.length === 0 || loading}
        className={classes.addTodoButton}
        color='primary'>
        Add
      </Button>
    </form>
  );
};
