import React, { Component } from 'react';
import { Button, TextField, Typography, Paper} from '@material-ui/core';

class Login extends Component {
    constructor(props){
        super(props);

        this.state = {
            email: "",
            password: "",
            loginResult: {},
            submitted: false,
            timer: false,
            output: ""
        }
    }

    handleChange = event => {
        this.setState({[event.target.id]: event.target.value})
    }

    loginRequest(){
        fetch('http://159.89.188.124:8080/employees/login', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email: this.state.email,
                password: this.state.password
            }),
        }) 
        .then(res => res.json())
        .then(result => {this.setState({loginResult: result, submitted: true, output: this.returnOutput(result)}); this.openWindow()})
        .catch(err => this.setState({loginResult: err, submitted: true}))
    }

    returnOutput(obj){
        if(obj.message === "Unable to acquire JDBC Connection; nested exception is org.hibernate.exception.GenericJDBCException: Unable to acquire JDBC Connection"){
            return 0;
        }
        else if(obj.status === 500){
            return 1;
        }
        else if(obj.email !== ""){
            localStorage.setItem('role', obj.role);
            return 2;
        }
    }

    returnOut(){
        if(this.state.timer){
            if(this.state.output === 0){
                return <Typography color="error">Server is Down</Typography>
            }
            else if(this.state.output === 1){
                return <Typography color="error">Invalid Credentials</Typography>
            }
            else if(this.state.output === 2){
                window.location.replace('/');
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
        console.log(this.state)
        
        return (
            <Paper style={{margin: 'auto', width: 400, height: 400, textAlign: 'center', marginTop: 200}}>
                <div > 
                <TextField
                    style={{marginTop: 100}}
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
                <Button disabled={this.state.email === "" || this.state.password === ""}
                        variant="contained"
                        color="primary"
                        onClick={() => this.loginRequest()}
                    >
                    Login
                </Button>
                {this.returnOut()}
                </div>
            </Paper>
        );
    }
}

export default Login;