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
    width: 500,
    overflowX: 'auto',
    margin: 'auto',
    marginTop: 20
  },
  table: {
    minWidth: 350,
  },
});

export default function LinksTable(props) {
  const classes = useStyles();
  
  return (
    <Paper className={classes.root}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Role</TableCell>
            <TableCell align="center">Change Role</TableCell>
            <TableCell align="center">Delete</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map(row => (
            <TableRow key={row.link_id}>
              <TableCell component="th" scope="row">
                {row.role}
              </TableCell>
              <TableCell align="center"><Button disabled={row.role_id === 8 || row.role_id === 36} variant="contained" color="primary" onClick={() => props.handleOpen(row)}>EDIT</Button></TableCell>
              <TableCell align="center"><Button disabled={row.role_id === 8 || row.role_id === 36} variant="contained" color="secondary" onClick={() => props.deleteRole(row.role_id)}>Delete</Button></TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
}