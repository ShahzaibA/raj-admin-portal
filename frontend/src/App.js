import React from 'react';
import { Route } from "react-router-dom";

import Login from './containers/Login';
import Homepage from './containers/Homepage';
import Registration from './containers/Registration';
import SuperAdmin from './containers/SuperAdmin';
import LinksPage from './containers/LinksPage';
import Links from './containers/Links';
import RoleList from './containers/RoleList';

function App() {
  return (
      <React.Fragment>
          <Route path="/" exact component={Homepage}/>
          <Route path="/login" exact component={Login}/>
          <Route path="/register" exact component={Registration}/>
          <Route path="/admin" exact component={SuperAdmin}/>
          <Route path="/redirect" exact component={LinksPage}/>
          <Route path="/links" exact component={Links}/>
          <Route path="/roles" exact component={RoleList}/>
      </React.Fragment>
  );
}

export default App;
