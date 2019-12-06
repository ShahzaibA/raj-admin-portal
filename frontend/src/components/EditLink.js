import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';


export default function EditLink(props) {
    const [role, setRole] = React.useState(props.selectedRole.role);
    const [linkName, changeLink] = React.useState(props.selectedRole.link);
    console.log(props.selectedRole.role)
    const handleChange = event => {
        setRole(event.target.value);
      };
      const handleChangeName = event => {
        changeLink(event.target.value);
      };
    const cancelClose = () => {
        props.handleClose();
    }
    const updateClose = (linkID, newname, newrole) => {
        props.updateRole(linkID, newname, newrole);
        props.handleClose();
    }
  return (
      <Dialog
        open={props.open}
        onClose={props.handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"Edit " + props.selectedRole.link}</DialogTitle>
        <DialogContent>
        <TextField
                    id="linkname"
                    label="Link"
                    value={linkName}
                    onChange={handleChangeName}
                /><br/>
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
          <Button onClick={() => updateClose(props.selectedRole.link_id, linkName, role)} color="primary" autoFocus>
            Save Changes
          </Button>
        </DialogActions>
      </Dialog>
  );
}