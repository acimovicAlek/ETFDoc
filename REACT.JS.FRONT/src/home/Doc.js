import React, {Component} from 'react'; 

class Doc extends Component{

    constructor(props){
        super(props);
        this.state = {
        };
    }

    render(){
        return(
            
            <a className="document-wrapper">
            <div className="document-box">
              <span className="glyphicon glyphicon-file glyphicon-list-alt"></span><br></br><br></br>{this.props.doc.name}
            </div>
            </a>
          
        );
    }
}

export default Doc;