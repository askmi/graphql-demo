import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Row } from 'reactstrap';

import grapqlDiagram from 'assets/graphql-diagram.svg';

export const HomePage = () => {
  return (
    <Container fluid>
      <Row noGutters className='d-flex flex-column align-items-center'>
        <img
          src={grapqlDiagram}
          alt='GraphQL Apollo Diagram'
          style={{
            maxWidth: '100%',
            height: 'auto',
          }}
        />
        <Link to='/todos' className='mt-4'>
          Go to the Todos Page
        </Link>
      </Row>
    </Container>
  );
};
