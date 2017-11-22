import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Link } from 'react-router-dom';

import './index.css';

/* Main components */
import Menu from './common/Menu';
import App from './App';
import Login from './Login';
import Register from './Register';
import Dashboard from './Dashboard';
import Home from './Home';

/* Render + router */
ReactDOM.render (
    <BrowserRouter>
        <div className="etfdocs">
            <Menu />
            <Route exact path="/" component={App}/>
            <Route path="/login" component={Login}/>
            <Route path="/register" component={Register}/>
            <Route path="/dashboard" component={Dashboard}/>
            <Route path="/home" component={Home}/>
        </div>
    </BrowserRouter>,
    document.getElementById('root')
);
