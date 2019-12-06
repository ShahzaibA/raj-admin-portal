import React, { Component } from 'react';
import RoleTable from '../components/RoleTable';
import NavigtionBar from '../components/NavigationBar';
import RoleModal from '../components/RoleModal';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";

class RoleList extends Component {
    constructor(props){
        super(props);
        this.state = {
            roleData: [],
            selectedRole: {},
            openModal: false,
            newLink: '',
        }
    }
    componentDidMount(){
        this.fetchRoles();
    }
    handleChangeName = event => {
        this.setState({newLink: event.target.value})
    }
    fetchRoles(){
        fetch('http://159.89.188.124:8080/roles', {
            method: "GET"
        })
        .then(res => res.json())
        .then(result => this.setState({roleData: result}))
    }
    deleteRole = (roleID) => {
        fetch('http://159.89.188.124:8080/roles/'+roleID, {
            method: 'DELETE'
        })
        .then(() => this.fetchRoles())
    }
    createRole = () =>{
        fetch('http://159.89.188.124:8080/roles', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                role: this.state.newLink
            }),
        })
        .then(() => this.setState({newLink: ''}))
        .then(() => this.fetchRoles())
    }
    updateRole = (roleID, newrole) =>{
        fetch('http://159.89.188.124:8080/roles/'+roleID, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                role: newrole
            }),
        }) 
        .then(() => this.fetchRoles())
    }
    openModal = (newRole) => {
        this.setState({selectedRole: newRole})
        this.setState({openModal: true})
    }
    closeModal = () => {
        this.setState({openModal: false})
    }
    returnOut(){
        if(this.state.openModal){
            return <RoleModal updateRole={this.updateRole} selectedRole={this.state.selectedRole} open={this.state.openModal} handleClose={this.closeModal}/>
        }
        else{
            return <div/>
        }
    }
    render() {
        console.log(this.state.selectedRole)
        return (
            <div>
                <NavigtionBar/>
                <div style={{textAlign: "center"}}>
            <TextField
                    id="linkName"
                    label="Role Title"
                    value={this.state.newLink}
                    onChange={this.handleChangeName}
                />
        <Button onClick={() => this.createRole()} disabled={this.state.newLink === '' || this.state.newRole === ''} variant="contained" color="primary" style={{marginTop: 6, marginLeft: 15}}>Create</Button>
        </div>
                <RoleTable handleOpen={this.openModal} deleteRole={this.deleteRole} data={this.state.roleData}/>
                {this.returnOut()}
            </div>
        );
    }
}

export default RoleList;