import React, { Component } from 'react';

class Footer extends Component {
    render() {
        return (
            <section id="footer">
                <div className="container">
                    <div className="row">
                        <div className="col-md-3 col-sm-12 col-xs-12">
                            ETFDocs
                        </div>
                        <div className="col-md-9 col-sm-12 col-xs-12">
                            <div className="footer-right">
                                <a href="#">About</a>
                                <a href="#">Sign in</a>
                                <a href="#">Register</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
}

export default Footer;