import React, { Component } from 'react';
import NavigationBar from '../components/NavigationBar';
import LinksTable from '../components/LinksTable';
import EditLink from '../components/EditLink';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";

class Links extends Component {
    constructor(props){
        super(props);
        this.state = {
            linksData: [],
            roleList: [],
            openModal: false,
            selectedRole: {},
            newLink: '',
            newRole: ''
        }
    }
    componentDidMount() {
        this.fetchLinkData();
        this.fetchRoles();
    }
    
    deleteLink = (linkID) => {
        fetch('http://159.89.188.124:8080/links/'+linkID, {
            method: 'DELETE'
        })
        .then(() => this.fetchLinkData())
    }
    fetchRoles(){
        fetch('http://159.89.188.124:8080/roles', {
            method: "GET"
        })
        .then(res => res.json())
        .then(result => this.setState({roleList: result}))
    }

    fetchLinkData(){
        fetch('http://159.89.188.124:8080/links', {
            method: 'GET'
        })
        .then(res => res.json())
        .then(result => this.setState({linksData: result}))
    }
    updateRole = (linkID, newLink, newrole) =>{
        fetch('http://159.89.188.124:8080/links/'+linkID, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                link: newLink,
                role: newrole
            }),
        }) 
        .then(() => this.fetchLinkData())
    }
    createLink = () =>{
        fetch('http://159.89.188.124:8080/links', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                link: this.state.newLink,
                role: this.state.newRole
            }),
        }) 
        .then(() => this.setState({newLink: '', newRole: ''}))
        .then(() => this.fetchLinkData())
    }
    openModal = (newRole) => {
        this.setState({selectedRole: newRole, openModal: true})
    }
    closeModal = () => {
        this.setState({openModal: false})
    }
    returnOut(){
        if(this.state.openModal){
            return <EditLink roleData={this.state.roleList} updateRole={this.updateRole} selectedRole={this.state.selectedRole} open={this.state.openModal} handleClose={this.closeModal}/>
        }
        else{
            return <div/>
        }
    }
    handleChangeName = event => {
        this.setState({newLink: event.target.value})
    }
    handleChangeRole = event => {
        this.setState({newRole: event.target.value})
    }
    render() { 
        return (
            <div>
            <NavigationBar/>
        <div style={{textAlign: "center"}}>
            
            <div>
            <TextField
                    id="linkName"
                    label="Link Name"
                    value={this.state.newLink}
                    onChange={this.handleChangeName}
                />
          <TextField select label="Role"
          style={{width: 160, marginLeft: 15}}
          value={this.state.newRole}
          onChange={this.handleChangeRole}
        >
            {this.state.roleList.map(option => 
            <MenuItem value={option.role}>{option.role}</MenuItem>
            )}
        </TextField>
        <Button onClick={() => this.createLink()} disabled={this.state.newLink === '' || this.state.newRole === ''} variant="contained" color="primary" style={{marginTop: 6, marginLeft: 15}}>Create</Button>
        </div>
            <LinksTable handleOpen={this.openModal} deleteLink={this.deleteLink} data={this.state.linksData} />
            {this.returnOut()}
        </div>
        </div>
        );
    }
}
 
export default Links;