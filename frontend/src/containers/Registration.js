import React, { Component } from 'react';
import { Button, TextField, Typography, Paper} from '@material-ui/core';

class Registration extends Component {
    constructor(props){
        super(props);
        this.state = {
            name: "",
            email: "",
            password: "",
            output: {}
        }
    }

    handleChange = event => {
        this.setState({[event.target.id]: event.target.value})
    }

    fetchRegistration() {
        fetch('http://159.89.188.124:8080/employees', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: this.state.name,
                email: this.state.email,
                password: this.state.password
            }),
        }) 
        .then(res => res.json())
        .then(result => {this.setState({output: this.returnOutput(result)}); this.openWindow()})
        .catch(err => this.setState({output: err}))
    }

    returnOutput(obj){
        if(obj.status === 500){
            return 0;
        }
        else{
            return 1;
        }
    }

    returnOut(){
        if(this.state.timer){
            if(this.state.output === 0){
                return <Typography color="error">Email already in use</Typography>
            }
            else if(this.state.output === 1){
                window.location.replace('/login');
            }
            else{
                return <Typography color="error">Something Went Wrong!</Typography>
            }
        }
    }

    openWindow(){
        this.setState({timer: true});
        setTimeout(() => {
            this.setState({timer: false});
            this.setState({output: ""});
        }, 2000);  
    }

    render() {
        console.log(this.state.output)
        return (
            <Paper style={{margin: 'auto', width: 400, height: 400, textAlign: 'center', marginTop: 200}}>
                <div>
                    <Typography variant="h5">Registration</Typography>
                <TextField
                    style={{marginTop: 40}}
                    id="name"
                    label="Full Name"
                    type="name"
                    autoComplete="current-name"
                    value={this.state.name}
                    onChange={e => this.handleChange(e)}
                />
                <br/>
                <TextField
                    style={{marginTop: 20}}
                    id="email"
                    label="Email"
                    type="email"
                    autoComplete="current-email"
                    value={this.state.email}
                    onChange={e => this.handleChange(e)}
                />
                <br/>
                <TextField
                    style={{marginTop: 20, marginBottom: 30}}
                    id="password"
                    label="Password"
                    type="password"
                    autoComplete="current-password"
                    value={this.state.password}
                    onChange={e => this.handleChange(e)}
                />
                <br/>
                <Button disabled={this.state.email === "" || this.state.password === "" || this.state.name === ""}
                        variant="contained"
                        color="primary"
                        onClick={() => this.fetchRegistration()}
                    >
                    Register
                </Button>
                {this.returnOut()}
                </div>
            </Paper>
        );
    }
}

export default Registration;