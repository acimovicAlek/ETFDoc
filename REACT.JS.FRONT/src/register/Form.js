import React, { Component } from 'react';
import axios from 'axios';

const protocol = window.location.protocol;
const hostname = window.location.hostname;

class Form extends Component {

    constructor () {
        super();
        this.state = {
            first_name: '',
            last_name: '',
            email: '',
            password: '',
            repPassword: ''
        }

        this.register = this.register.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    // Registration handler
    register (event) {
        event.preventDefault();
        // E-mail regex
        let emre = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(emre.test(this.state.email)) {
             if(this.state.password === this.state.repPassword) {
                axios.post(protocol+'//'+hostname+':8080/account/create', {
                    email: this.state.email,
                    firstName: this.state.first_name,
                    lastName: this.state.last_name,
                    password: this.state.password
                })
                .then(this.handleSuccess.bind(this))
                .catch(this.handleError.bind(this));
             } else alert('Not the same password');
        } else alert('Invalid e-mail format.');
    }

    handleSuccess(response) {
        if(response.data)
        {
            alert('Uspješno ste se registrovali!');
            window.location = '/login';
        } 
    }   

    handleError(error) {
        alert(error.response.data);
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
                                    <input type="first_name" className="form-control login-form__input" name="first_name" placeholder="First name" onChange={this.onChange}/>
                                </div>
                                <div className="form-group">
                                    <input type="last_name" className="form-control login-form__input" name="last_name" placeholder="Last name" onChange={this.onChange}/>
                                </div>
                                <div className="form-group">
                                    <input type="email" className="form-control login-form__input" name="email" placeholder="E-mail" onChange={this.onChange}/>
                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control login-form__input" name="password" placeholder="Password" onChange={this.onChange}/>
                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control login-form__input" name="repPassword" placeholder="Password" onChange={this.onChange}/>
                                </div>

                                <button type="submit" className="cover-button login-button" name="register" onClick={this.register}>
                                    Create account
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
