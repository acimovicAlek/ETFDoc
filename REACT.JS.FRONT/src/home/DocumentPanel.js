import React, { Component } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import Doc from './Doc';

const protocol = window.location.protocol;
const hostname = window.location.hostname;

class DocumentPanel extends Component {

    constructor (props) {
        super(props);

        this.state = {
          email: null,
          documents: [],
          keyword: ''
        }

        this.triggerSearch = this.triggerSearch.bind(this);
    }

    componentWillMount() {
       let userinfo = jwtDecode(sessionStorage.getItem('token'));
       this.setState({email: userinfo.sub});

    }


    componentDidMount() {

      let userinfo = jwtDecode(sessionStorage.getItem('token'));
      let email = userinfo.sub;

      console.log(this.props.publicFiles);
      if (this.props.publicFiles==1){
        axios.get(protocol+'//'+hostname+':8080/document/public', { })
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));
      }
      else if (this.props.publicFiles==0) {
          axios.get(protocol+'//'+hostname+':8080/document/private?email='+email, {})
          .then(this.handleSuccess.bind(this))
          .catch(this.handleError.bind(this));
      }
    }

    componentWillRecieveProps(nextProps){
      console.log(nextProps);
    }


    handleSuccess(response) {
      this.setState({documents:response.data});

    }

    handleError(error) {
      console.log(error.response);
      alert("Something went wrong!");
    }

    // Trigger search
    triggerSearch(e) {
      // Setup the keyword
      this.setState({ keyword: e.target.value });

      axios.get('http://localhost:8080/document/getByKeywordAndCollaborator?email=' + this.state.email + '&keyword=' + e.target.value, { })
      .then(this.handleSearchSuccess.bind(this))
      .catch(this.handleSearchError.bind(this));
    }

    handleSearchSuccess(response) {
      this.setState({documents:response.data});
    }

    handleSearchError(error) {
      alert("Something went wrong while triggering the search.");
    }

    render () {
        let elements = this.state.documents.map((ele) => <Doc doc={ele}/>);

        return (
                <div className="container container-document">
                  <div className="row">
                    <div className="col-md-12 col-sm-12 col-xs-12" style={{paddingLeft: "0px"}}>
                      <form action="" method="get">
                        <div className="form-group">
                          <input type="text" name="keyword" className="form-control" placeholder="Enter search keyword" value={this.state.keyword} onChange={this.triggerSearch} />
                        </div>
                      </form>
                    </div>
                  </div>
                  <div className="row document-row">
                    <div>
                      {elements}
                    </div>
                  </div>
                </div>
        );
    }
};

export default DocumentPanel;
