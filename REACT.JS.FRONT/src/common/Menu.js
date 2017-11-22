import React, { Component } from 'react';
import { authenticated } from '../_services/Sessions';

class Menu extends Component {
    render () {
        if(sessionStorage.getItem('userData') !== null) 
        {
            return (
                <nav id="mainNav" className="navbar">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <a className="navbar-brand mainNav-heading" href="/">ETF<blue>Docs</blue></a>
                        </div>

                        <ul className="nav navbar-nav navbar-right mainNav-links">
                            <li><a href="/dashboard">Dashboard</a></li>
                            <li><a href="javascript:sessionStorage.clear();location.reload();">Logout</a></li>
                        </ul>
                    </div>
                </nav>
            );
        }
        else
        {
            return (
                <nav id="mainNav" className="navbar">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <a className="navbar-brand mainNav-heading" href="/">ETF<blue>Docs</blue></a>
                        </div>

                        <ul className="nav navbar-nav navbar-right mainNav-links">
                            <li><a href="/login">Sign in</a></li>
                            <li><a href="/register">Register</a></li>
                        </ul>
                    </div>
                </nav>
            );
        }
    }
}

export default Menu;