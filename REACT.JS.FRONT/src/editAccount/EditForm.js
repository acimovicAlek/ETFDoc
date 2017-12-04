import React, { Component } from 'react';
import { PostData } from '../_services/PostData.js';

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

        this.edit = this.edit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    edit (event) {
        event.preventDefault();
        let emre = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(emre.test(this.state.email)) {
             if(this.state.password == this.state.repPassword) {
                PostData('account/register', this.state).then((result) => {
                    if(result == "true") {
                        alert('Successfully edited account info!');
                        window.location = '/login';
                    } else
                        alert('Already exists!');
                });
             } else alert('Not the same password');
        } else alert('Invalid e-mail format.');
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
                            <div className="edit-form">
                                <div className="form-group ">
                                    <div className="edit-field">
                                      <p className="edit-label">First name: </p>
                                      <input type="first_name" className="form-control edit-form__input" name="first_name" placeholder="First name" onChange={this.onChange}/>
                                    </div>
                                    <div className="edit-field">
                                      <p className="edit-label">Last name: </p>
                                      <input type="last_name" className="form-control edit-form__input" name="last_name" placeholder="Last name" onChange={this.onChange}/>
                                    </div>
                                    <div className="edit-field">
                                      <p className="edit-label">E-mail: </p>
                                      <input type="email" className="form-control edit-form__input" name="email" placeholder="E-mail" onChange={this.onChange}/>
                                    </div>


                                </div>

                                <button type="submit" className="cover-button login-button" name="register" onClick={this.register}>
                                    Save changes
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
