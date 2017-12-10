import React, { Component } from 'react';

class DocumentPanel extends Component {

    constructor () {
        super();

        this.state = {
          email: null,
          documents: []
        }
    }

    componentWillMount() {
       let userinfo = jwtDecode(sessionStorage.getItem('token'));
       this.setState({email: userinfo.sub});
    }

    componentDidMoutn() {
      axios.get('http://localhost:8080/document/getByRootAndOwner?email=' + this.state.email, { })
      .then(this.handleSuccess.bind(this))
      .catch(this.handleError.bind(this));
    }

    handleSuccess(response) {

    } 

    handleError(error) {

    }

    render () {
        return (
                <div className="container container-document">
                  <div className="row document-row">

                    <div className="col-md-2 col-sm-6 col-xs-12 document-col">
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
