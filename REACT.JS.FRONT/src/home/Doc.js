import React, {Component} from 'react';
import axios from 'axios';

const hostname = window.location.hostname;

class Doc extends Component{

    constructor(props){
        super(props);
        
        console.log(this.props);
        this.state = {
        };

        this.deleteDocument = this.deleteDocument.bind(this);
    }

    deleteDocument() {
      if(window.confirm("Do you want to delete this document?")) {
        axios.delete('http://'+hostname+':8080/document/delete?id='+this.props.doc.id, {
            id: this.props.doc.id
        })
        .then(this.handleDeleteSuccess.bind(this))
        .catch(this.handleDeleteError.bind(this));
      }
    }

    handleDeleteSuccess(response) {
      window.location = '/home';
    }

    handleDeleteError(error) {
      alert("Something went wrong while deleting document.");
    }

    render(){
        return(
            <div className="col-md-3 col-sm-4 col-xs-12">
                <div className="document-box">
                  <span className="glyphicon glyphicon-file glyphicon-list-alt"></span>
                  <br></br>
                  <a className="document-wrapper" onClick={this.deleteDocument}>
                  <span className="glyphicon glyphicon-trash"></span>
                  </a>
                  <br></br>
                  <a className="document-wrapper" href={'document/' + this.props.doc.id}>{this.props.doc.name}

                  </a>
                </div>
            </div>
        );
    }
}

export default Doc;
