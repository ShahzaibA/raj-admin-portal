import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';


export default function EditModal(props) {
    const [role, setRole] = React.useState(props.selectedRole.role);
    const handleChange = event => {
        setRole(event.target.value);
      };
    const cancelClose = () => {
        props.handleClose();
    }
    const updateClose = (roleID, newrole) => {
        props.updateRole(roleID, newrole);
        props.handleClose();
    }
  return (
      <Dialog
        open={props.open}
        onClose={props.handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"Change Role"}</DialogTitle>
        <DialogContent>
        <TextField
                    id="rolename"
                    label="Role Title"
                    value={role}
                    onChange={handleChange}
                />
        </DialogContent>
        <DialogActions>
          <Button onClick={cancelClose} color="primary">
            Cancel
          </Button>
          <Button disabled={role === ''} onClick={() => updateClose(props.selectedRole.role_id, role)} color="primary" autoFocus>
            Save Changes
          </Button>
        </DialogActions>
      </Dialog>
  );
}