import React, { Component } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';

const protocol = window.location.protocol;
const hostname = window.location.hostname;

class Form extends Component {
     // State constructor
    constructor () {
        super();
        this.state = {
            email: '',
            password: ''
        };

        this.login = this.login.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    //Login
    login (event) {
        event.preventDefault();

        if(this.state.email && this.state.password) {
            if(this.state.email && this.state.password) {
                axios.post(protocol+'//'+hostname+':8080/login', {
                    email: this.state.email,
                    password: this.state.password
                })
                .then(this.handleSuccess.bind(this))
                .catch(this.handleError.bind(this));
            }
        }
    }

    handleSuccess(response) {
        sessionStorage.setItem("token", response.headers.authorization);
        sessionStorage.setItem("role", response.headers.role);

        let userinfo = jwtDecode(sessionStorage.getItem('token'));
        let email = userinfo.sub;

        let data = null;

        axios.get(protocol+'//'+hostname+':8080/account/getbyemail?email='+email, { })
        .then(function(res) {
            console.log(res.data.id);
            sessionStorage.setItem('id', res.data.id);
        }.bind(this))   
        .catch(function(error) {
            console.log(error.response);
        }.bind(this));

        window.location = '/home';
    }

    handleError(error) {
        console.log(error);
    }

    onChange(e) {
        this.setState({[e.target.name]:e.target.value});
    }

    render () {
        return (
            <section id="cover" className="cover-fix">
                <div className="container">
                    <div className="col-md-6 col-md-offset-3 col-sm-12 col-xs-12">
                        <div className="login-screen">
                            <img className="cover-logo" src={require('../img/cover-logo.png')} />
                        </div>
                        <form action="" method="post">
                            <div className="login-form">
                                <div className="form-group">
                                    <input type="email" className="form-control login-form__input" name="email" placeholder="E-mail" value={this.state.email} onChange={this.onChange} />

                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control login-form__input" name="password" placeholder="Password" value={this.state.password} onChange={this.onChange} />
                                </div>

                                <button type="submit" className="cover-button login-button" name="login" onClick={this.login}>
                                    Login
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        );
    }
};

export default Form;
