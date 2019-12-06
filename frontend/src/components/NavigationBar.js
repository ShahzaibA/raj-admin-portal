import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import SideBar from './SideBar';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));

export default function ButtonAppBar(props) {
  const classes = useStyles();
    let isLogged = localStorage.getItem('role') !== null;
    let button, sidecomp;
    if (isLogged){
        sidecomp = <SideBar/>
        button = <Button onClick={() => {localStorage.removeItem('role'); window.location.replace('/')}} color="inherit">Log Out</Button>
    }
    else{
        sidecomp = <div/>
        button = <div><Button href="/login" color="inherit">Login</Button><Button href="/register" color="inherit">Register</Button></div>
    }
  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          {sidecomp}
          <Typography variant="h6" className={classes.title}>
            Portal
          </Typography>
          {button}
        </Toolbar>
      </AppBar>
    </div>
  );
}