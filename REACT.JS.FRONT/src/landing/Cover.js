import React, { Component } from 'react';

class Cover extends Component{
    render () {
        return (
            <section id="cover">
                <div className="container">
                    <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                        <div className="cover-text">
                            <img className="cover-logo" src={require('../img/cover-logo.png')} />
                            <h2 className="cover-header">University platform for document sharing and collaboration</h2>
                        </div>
                        <a href="/login" className="cover-button">Access platform</a>
                        <div className="cover-scroll">Scroll down <blue>for more info</blue>
                        <br></br><img className="scroll-icon" src={require('../img/scroll-icon.png')}/>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
};

export default Cover;
