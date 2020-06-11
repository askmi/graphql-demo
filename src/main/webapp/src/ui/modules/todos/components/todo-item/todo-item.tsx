import React from 'react';
import { CustomInput, Button } from 'reactstrap';
import gql from 'graphql-tag';
import { useMutation } from '@apollo/react-hooks';
import cn from 'classnames';

import { Todo, Query } from 'generated/graphql';

import { GET_TODOS } from 'ui/modules/todos/components/todo-list';

import classes from './todo-item.module.scss';

const REMOVE_TODO = gql`
  mutation RemoveTodo($id: String!) {
    removeTodo(id: $id)
  }
`;

const COMPLETE_TODO = gql`
  mutation CompleteTodo($id: String!) {
    completeTodo(id: $id) {
      id
      name
      completed
    }
  }
`;

type Props = {
  todo: Todo;
};

export const TodoItem: React.FC<Props> = ({ todo }) => {
  const [removeTodo, { loading }] = useMutation(REMOVE_TODO, {
    update(cache, { data: { removeTodo } }) {
      if (!removeTodo) {
        return null;
      }

      const variables = { skip: 0, first: 10 };

      const response = cache.readQuery<{
        todos: Query['todos'];
      }>({
        query: GET_TODOS,
        variables,
      });

      const todos = response?.todos
        ? response?.todos?.filter((todo) => todo!.id !== removeTodo.id)
        : [];

      cache.writeQuery({
        query: GET_TODOS,
        data: { todos },
        variables,
      });
    },
  });
  const [completeTodo] = useMutation(COMPLETE_TODO);

  return (
    <li
      className={cn(
        classes.todoItem,
        todo.completed && classes.todoItemCompleted
      )}>
      <CustomInput
        id={todo.id!}
        disabled={todo.completed ?? false}
        type='checkbox'
        checked={todo.completed ?? false}
        onChange={() => {
          completeTodo({
            variables: { id: todo.id },
            optimisticResponse: {
              __typename: 'Mutation',
              completeTodo: {
                id: todo.id,
                name: todo.name,
                __typename: 'Todo',
                completed: !todo.completed,
              },
            },
          });
        }}
      />
      <div className={classes.todoItemSecondColumn}>
        <span>{todo.name}</span>
        {todo.completed && (
          <Button
            aria-label='remove todo'
            disabled={loading}
            color='danger'
            onClick={() => {
              removeTodo({
                variables: { id: todo.id },
              });
            }}>
            X
          </Button>
        )}
      </div>
    </li>
  );
};
