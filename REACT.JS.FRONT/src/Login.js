import React, { Component } from 'react';
import { mustNotAuthenticate } from './_services/Sessions';

import './App.css';

import Menu from './common/Menu';
import Footer from './common/Footer';

import Form from './login/Form';

class Login extends Component {
    constructor() {
        super(); 
    }

    componentWillMount() {
        mustNotAuthenticate();
    }

    render () {
        return (
        <div>
            <Form />
            <Footer />
        </div>
        );
    }
};

export default Login;