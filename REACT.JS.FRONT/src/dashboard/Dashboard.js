import React, { Component } from 'react';

class Dash extends Component{
    render () {
        return (
            <section id="cover">
                <div className="container">
                    <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                        <div className="cover-text">
                            <img className="cover-logo" src={require('../img/cover-logo.png')} />
                            <h2 className="cover-header">Welcome to your dashboard!</h2>
                        </div>
                        <div className="cover-scroll">Scroll down <blue>for more info</blue>
                        <br></br><img className="scroll-icon" src={require('../img/scroll-icon.png')}/>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
};

export default Dash;
