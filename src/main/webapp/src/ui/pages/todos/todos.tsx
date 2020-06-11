import React from 'react';
import { Container, Row } from 'reactstrap';

import { TodoList } from 'ui/modules/todos/components/todo-list';
import { AddTodo } from 'ui/modules/todos/components/add-todo';

import classes from './todos.module.scss';

export const TodosPage = () => {
  return (
    <Container fluid className='h-100 mt-3 mb-3'>
      <Row noGutters className='d-flex flex-column align-items-center'>
        <div className={classes.todos}>
          <AddTodo />
          <TodoList />
        </div>
      </Row>
    </Container>
  );
};
