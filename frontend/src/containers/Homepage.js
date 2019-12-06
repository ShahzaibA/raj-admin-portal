import React, { Component } from 'react';
import NavigationBar from '../components/NavigationBar';

class Homepage extends Component {
    render() {
        return (
            <div>
                <NavigationBar/>
                <h1>Homepage</h1>
            </div>
        );
    }
}

export default Homepage;