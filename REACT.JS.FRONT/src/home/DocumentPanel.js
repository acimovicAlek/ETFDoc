import React, { Component } from 'react';

class DocumentPanel extends Component {

    constructor () {
        super();
    }

    render () {
        return (
                <div className="container container-document">
                  <div className="row document-row">

                    <div className="col-md-2 col-sm-3 col-xs-8 document-col">
                      <a className="document-wrapper">
                      <div className="document-box">
                        <span className="glyphicon glyphicon-file glyphicon-list-alt"></span><br></br><br></br>Test file
                      </div>
                      </a>
                    </div>

                  </div>
                </div>

        );
    }
};

export default DocumentPanel;
