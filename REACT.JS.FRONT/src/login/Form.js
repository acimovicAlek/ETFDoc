import React, { Component } from 'react';
import { PostData } from '../_services/PostData.js';

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
            PostData('account/login', this.state).then((result) => {
                let responseJson = result;

                if(result) {
                    sessionStorage.setItem('userData', JSON.stringify(responseJson));
                    window.location = '/dashboard';
                }
            });
        }
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
