import React, { Component } from 'react';
import { getParameterByName, currentContent } from '../_services/RealTimeAPI.js';
import openSocket from 'socket.io-client';

const socket = openSocket('http://localhost:6400');

class Markdown extends Component {

    constructor () {
        super();
        this.state = {
          content: '',
          id: Number(getParameterByName('document')),
          user: Number(getParameterByName('user')),
          active: {}
        };

        this.onChange = this.onChange.bind(this);

        socket.on('document', (res) => {
          if(res.new_val.id == this.state.id && res.new_val.user != this.state.user) 
            this.setState({content: res.new_val.content});
        });

        currentContent(Number(getParameterByName('document'))).then((result) => {
          console.log(result);
          this.setState({content: result.content});
        });
    }

    componentWillMount() {
      // Prije nego se komponenta ucita 
      // Provjri authentikaciju
      // mustAuthenticate();

      // Provjeri da li korisnik ima privliegije da uređuje ovaj dokument
      // Ako nema treba redirektati negdje, gdje god
      // Ispisati prouku da ne može pristupiti uređivanju
    }

    onChange(e) {
        this.setState({[e.target.name]:e.target.value});
        var obj = {
          content: e.target.value,
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
                    <h2>Naziv dokument</h2>
                      <hr/>
                    <div className="markdown-editor-collaborators">
                      <ul>
                      </ul>
                    </div>
                  </div>
                  <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <textarea className="editor" name="content" value={this.state.content} onChangeCapture={this.onChange}></textarea>
                  </div>
                </div>
              </div>
            </section>
        );
    }
};

export default Markdown;