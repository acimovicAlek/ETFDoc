import React, { Component } from 'react';


class About extends Component {
    render () {
        return (
            <section id="about">
                <div className="container">
                    <div className="row">
                        <div className="col-md-4 col-sm-12 col-xs-12">
                            <img src={require('../img/left-about.png')} style={{width: '100%'}} />
                            <h2 className="about-heading">Share files with other students</h2>
                            <span className="about-paragraph">
                                Lorem ipsum dolor sit amet, consectetur 
                                adipiscing elit. Fusce vitae aliquet libero. 
                                Morbi faucibus diam eu viverra laoreet. 
                                Curabitur quis quam nunc. Etiam lobortis elit 
                                porttitor nibh pretium pellentesque. In 
                                scelerisque ornare ultrices. Vivamus eget 
                                eleifend lectus. Vivamus dignissim justo erat, sit 
                                amet sollicitudin sem tempor at. Integer
                                imperdiet  ut odio sit amet ultrices.
                            </span>
                        </div>
                        <div className="col-md-4 col-sm-12 col-xs-12">
                            <img src={require('../img/middle-about.png')} style={{width: '100%'}} />
                            <h2 className="about-heading">Personal private document storage</h2>
                            <span className="about-paragraph">
                                Lorem ipsum dolor sit amet, consectetur 
                                adipiscing elit. Fusce vitae aliquet libero. 
                                Morbi faucibus diam eu viverra laoreet. 
                                Curabitur quis quam nunc. Etiam lobortis elit 
                                porttitor nibh pretium pellentesque. In 
                                scelerisque ornare ultrices. Vivamus eget 
                                eleifend lectus. Vivamus dignissim justo erat, sit 
                                amet sollicitudin sem tempor at. Integer
                                imperdiet  ut odio sit amet ultrices.
                            </span>
                        </div>
                        <div className="col-md-4 col-sm-12 col-xs-12">
                            <img src={require('../img/right-about.png')} style={{width: '100%'}} />
                            <h2 className="about-heading">Real time document editing collaboration</h2>
                            <span className="about-paragraph">
                                Lorem ipsum dolor sit amet, consectetur 
                                adipiscing elit. Fusce vitae aliquet libero. 
                                Morbi faucibus diam eu viverra laoreet. 
                                Curabitur quis quam nunc. Etiam lobortis elit 
                                porttitor nibh pretium pellentesque. In 
                                scelerisque ornare ultrices. Vivamus eget 
                                eleifend lectus. Vivamus dignissim justo erat, sit 
                                amet sollicitudin sem tempor at. Integer
                                imperdiet  ut odio sit amet ultrices.
                            </span>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
}

export default About;