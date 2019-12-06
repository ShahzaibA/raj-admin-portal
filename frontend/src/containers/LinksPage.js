import React, { Component } from 'react';
import NavigationBar from '../components/NavigationBar';

class LinksPage extends Component {
    render() {
        return (
            <div>
                <NavigationBar/>
                <h1>{localStorage.getItem('link')}</h1>
            </div>
        );
    }
}

export default LinksPage;