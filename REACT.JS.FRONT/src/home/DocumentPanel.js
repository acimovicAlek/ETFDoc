import React, { Component } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import Doc from './Doc';

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

    componentDidMount() {
      axios.get('http://localhost:8080/document/getByRootAndOwner?email=' + this.state.email, { })
      .then(this.handleSuccess.bind(this))
      .catch(this.handleError.bind(this));
    }

    handleSuccess(response) {


      this.setState({documents:response.data});
      console.log(response);

    } 

    handleError(error) {

    }

    render () {
        let elements = this.state.documents.map((ele) => <Doc doc={ele}/>);

        return (
                <div className="container container-document">
                  <div className="row document-row">
                  <div className="col-md-2 col-sm-6 col-xs-12 document-col">
                    {elements}
                  </div>
                  </div>
                </div>
        );
    }
};

export default DocumentPanel;
