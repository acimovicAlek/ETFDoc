import React, { Component } from 'react';
import axios from 'axios';
import openSocket from 'socket.io-client';
import ReactQuill from 'react-quill';

const socket = openSocket('http://localhost:6400');

class Markdown extends Component {

    constructor () {
        super();
        this.state = {
          text: '',
          id: '',
          name: '',
          user: '',
          active: {},
          privs: false
        };

        this.onChange = this.onChange.bind(this);
        this.onQuillChange = this.onQuillChange.bind(this);
    }

    componentWillMount() {
      this.setState({user: sessionStorage.getItem('id')});
    }

    componentDidMount() {
        // Attache document id
        let document = this.props.params.document;

        this.setState({
          id: document,
        });

        axios.get('http://localhost:6400/document/'+document+'/'+this.state.user, {})
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));  
    }

    handleSuccess(response) {
      this.setState({
          text: response.data.content,
          name: response.data.name
      });
    }

    handleError(error) {
      console.log(error.response);
    }

    onChange(e) {
        this.setState({[e.target.name]:e.target.value});
        var obj = {
          content: this.state.text,
          name: this.state.name,
          id: this.state.id,
          user: this.state.user
        };
        console.log(obj);

        socket.emit('document-update', obj);  
    }

    onQuillChange(value) {
      this.setState({ text: value })
      var obj = {
          content: this.state.text,
          name: this.state.name,
          id: this.state.id,
          user: this.state.user
      };
      socket.emit('document-update', obj);  
    }

    render () {
        return (
            <section id="markdown-editor">
              <div className="container">
                <div className="row">
                  <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <input type="text" name="name" value={this.state.name} onChangeCapture={this.onChange} className="naziv-dokumenta" placeholder="Enter document name..." />
                      <hr/>
                    <div className="markdown-editor-collaborators">
                      <ul>
                      </ul>
                    </div>
                  </div>
                  <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <ReactQuill theme="snow"
                                value={this.state.text} 
                                onChange={this.onQuillChange} />
                  </div>
                </div>
              </div>
            </section>
        );
    }
};

export default Markdown;