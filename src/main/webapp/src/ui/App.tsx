import React from 'react';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';

import { HomePage } from 'ui/pages/home';
import { TodosPage } from 'ui/pages/todos';

import classes from './App.module.scss';

function App() {
  return (
    <Router>
      <main className={classes.main}>
        <Switch>
          <Route exact path='/' component={HomePage} />
          <Route path='/todos' component={TodosPage} />
        </Switch>
      </main>
    </Router>
  );
}

export default App;
