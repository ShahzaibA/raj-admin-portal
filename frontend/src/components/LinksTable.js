import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles({
  root: {
    width: 700,
    overflowX: 'auto',
    margin: 'auto',
    marginTop: 20
  },
  table: {
    minWidth: 650,
  },
});

export default function LinksTable(props) {
  const classes = useStyles();
  
  return (
    <Paper className={classes.root}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Link</TableCell>
            <TableCell align="right">Role</TableCell>
            <TableCell align="center">Change Role</TableCell>
            <TableCell align="center">Delete</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map(row => (
            <TableRow key={row.link_id}>
              <TableCell component="th" scope="row">
                {row.link}
              </TableCell>
              <TableCell align="right">{row.role}</TableCell>
              <TableCell align="center"><Button variant="contained" color="primary" onClick={() => props.handleOpen(row)}>EDIT</Button></TableCell>
              <TableCell align="center"><Button variant="contained" color="secondary" onClick={() => props.deleteLink(row.link_id)}>Delete</Button></TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
}