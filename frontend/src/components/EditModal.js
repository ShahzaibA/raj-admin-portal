import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';


export default function EditModal(props) {
    const [role, setRole] = React.useState('');
    const handleChange = event => {
        setRole(event.target.value);
      };
    const cancelClose = () => {
        props.handleClose();
        setRole('');
    }
    const updateClose = (userID, newrole) => {
        props.updateRole(userID, newrole);
        props.handleClose();
        setRole('');
    }
  return (
      <Dialog
        open={props.open}
        onClose={props.handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"Change Role For " + props.user.name}</DialogTitle>
        <DialogContent>
          <TextField select label="Role"
          style={{width: 160}}
          value={role}
          onChange={handleChange}
        >
            {props.roleData.map(option => 
            <MenuItem value={option.role}>{option.role}</MenuItem>
            )}
          
        </TextField>
        </DialogContent>
        <DialogActions>
          <Button onClick={cancelClose} color="primary">
            Cancel
          </Button>
          <Button disabled={role === ''} onClick={() => updateClose(props.user.employee_id, role)} color="primary" autoFocus>
            Save Changes
          </Button>
        </DialogActions>
      </Dialog>
  );
}