import React, { Component } from 'react';
import axios from 'axios';
import openSocket from 'socket.io-client';
import jwtDecode from 'jwt-decode';

const protocol = window.location.protocol;
const hostname = window.location.hostname;

const socket = openSocket(protocol+'//'+hostname+':6400');

class Markdown extends Component {

    constructor () {
        super();
        this.state = {
          text: '',
          id: '',
          name: '',
          user: '',
          active: {},
          recieved: false,
          privs: false
        };

        this.onNameChange = this.onNameChange.bind(this);
        this.onTextChange = this.onTextChange.bind(this);

        socket.on("document",(res) => {
          console.log(res);
          if(res.new_val.document === this.state.documetn && Number(res.new_val.user) != this.state.user){
            this.setState({
              text : res.new_val.content,
              name : res.new_val.name
            })
          }
        });
    }

    componentDidMount() {
        // Attache document id
        let document = this.props.params.document;

        this.setState({
          id: document,
        });

        let userinfo = jwtDecode(sessionStorage.getItem('token'));
        let email = userinfo.sub;

        let data = null;

        axios.get('http://'+hostname+':8080/account/getbyemail?email='+email, { })
        .then(function(res) {
            this.setState({user: res.data.id});

            // Fetch the data
            axios.get('http://'+hostname+':6400/document/'+document+'/'+this.state.user, {})
            .then(this.handleSuccess.bind(this))
            .catch(this.handleError.bind(this)); 
        }.bind(this))   
        .catch(function(error) {
            console.log(error.response);
        }.bind(this));   
    }

    handleSuccess(response) {
      console.log(response);
      this.setState({
          text: response.data.content,
          name: response.data.name
      });
    }

    handleError(error) {
      console.log(error.response);
    }

    onNameChange(e) {
        this.setState({[e.target.name]:e.target.value});
        var obj = {
          content: this.state.text,
          name: e.target.value,
          id: this.state.id,
          user: this.state.user,
          recieved: false
        };
        socket.emit('document-update', obj);  
    }

    onTextChange(e) {
      this.setState({ text: e.target.value })
      var obj = {
          content: e.target.value,
          name: this.state.name,
          id: this.state.id,
          user: this.state.user,
          recieved: false
      };

      socket.emit('document-update', obj);  
    }

    render () {
        return (
            <section id="markdown-editor">
              <div className="container">
                <div className="row">
                  <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <input type="text" name="name" value={this.state.name} onChangeCapture={this.onNameChange} className="naziv-dokumenta" placeholder="Enter document name..." />
                      <hr/>
                    <div className="markdown-editor-collaborators">
                      <ul>
                      </ul>
                    </div>
                  </div>
                  <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <textarea className="ql-editor" name="text" value={this.state.text} onChangeCapture={this.onTextChange}></textarea>
                  </div>
                </div>
              </div>
            </section>
        );
    }
};

export default Markdown;