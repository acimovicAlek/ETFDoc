import React, {Component} from 'react';

class Doc extends Component{

    constructor(props){
        super(props);
        this.state = {
        };
    }

    render(){
        return(

                <div className="document-box">
                  <span className="glyphicon glyphicon-file glyphicon-list-alt"></span>
                  <br></br>
                  <a className="document-wrapper">
                  <span className="glyphicon glyphicon-trash"></span>
                  </a>
                  <br></br>
                  <a className="document-wrapper" href={'document/'}/*+this.props.doc.id}*/>{this.props.doc.name}

                  </a>
                </div>

        );
    }
}

export default Doc;
