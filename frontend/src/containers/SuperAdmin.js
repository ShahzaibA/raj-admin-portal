import React, { Component } from 'react';
import UserTable from '../components/UserTable';
import EditModal from '../components/EditModal';
import NavigationBar from '../components/NavigationBar';

class SuperAdmin extends Component {
    constructor(props){
        super(props);
        this.state = {
            data: [],
            roleList: [],
            openModal: false,
            userSelected: {}
        }
    }

    componentDidMount(){
        this.fetchUsers();
        this.fetchRoles();
    }

    fetchUsers(){
        fetch('http://159.89.188.124:8080/employees', {
            method: "GET"
        })
        .then(res => res.json())
        .then(result => this.setState({data: result}))
    }

    fetchRoles(){
        fetch('http://159.89.188.124:8080/roles', {
            method: "GET"
        })
        .then(res => res.json())
        .then(result => this.setState({roleList: result}))
    }

    updateRole = (userID, newrole) =>{
        fetch('http://159.89.188.124:8080/employees/'+userID+'/role', {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                role: newrole
            }),
        }) 
        .then(res => res.json())
        .then(() => this.fetchUsers())
    }

    openModal = (user) => {
        this.setState({userSelected: user, openModal: true})
    }
    closeModal = () => {
        this.setState({openModal: false})
    }
    render() {
        console.log(this.state)
        return (
            <div>
                <NavigationBar/>
                <UserTable data={this.state.data} handleOpen={this.openModal}/>
                <EditModal roleData={this.state.roleList} updateRole={this.updateRole} user={this.state.userSelected} open={this.state.openModal} handleClose={this.closeModal}/>
            </div>
        );
    }
}

export default SuperAdmin;