import React, { Component } from 'react';
import axios from 'axios';
import openSocket from 'socket.io-client';
import jwtDecode from 'jwt-decode';

const protocol = window.location.protocol;
const hostname = window.location.hostname;

const socket = openSocket(protocol+'//'+hostname+':6400');  

var myCodeMirror;

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
          privs: false,
            priv_read : false,
            priv_write : false,
            priv_delete : false,
            priv_email: '',
            priv_id: null
        };

        this.onNameChange = this.onNameChange.bind(this);
        this.onTextChange = this.onTextChange.bind(this);
        this.deleteDocument = this.deleteDocument.bind(this);
        this.onPrivEmailChange = this.onPrivEmailChange.bind(this);
        this.onCheckboxChange = this.onCheckboxChange.bind(this);
        this.grantPrivlieges = this.grantPrivlieges.bind(this);
        this.grantPrivilegesAction = this.grantPrivilegesAction.bind(this);

        socket.on("document",(res) => {
          console.log(res);
          if(res.new_val.document === this.state.document && Number(res.new_val.user) != this.state.user){
            var pos = this.getCursor();
            let dif = res.new_val.content.length - this.state.text.length;
            this.setState({
              text : res.new_val.content,
              name : res.new_val.name
            })
            this.setCursor(pos+dif); 
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

    getCursor(){
      var el = document.getElementById("editor");
      var pos = 0;
      if('selectionStart' in el) {
          pos = el.selectionStart;
      } else if('selection' in document) {
          el.focus();
          var Sel = document.selection.createRange();
          var SelLength = document.selection.createRange().text.length;
          Sel.moveStart('character', -el.value.length);
          pos = Sel.text.length - SelLength;
      }
      return el.selectionStart;
    }

    setCursor(pos){
      console.log(pos);
      var el = document.getElementById("editor");
      el.setSelectionRange(pos,pos);
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

    // Delete file 
    deleteDocument() {
      if(window.confirm("Do you want to delete this document?")) {
        axios.delete('http://'+hostname+':8080/document/delete/'+this.state.document, {})
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

    // Adding privlieges check
    grantPrivlieges(e) {
      e.preventDefault();

      if(this.state.email != "") {
        alert("Please enter an e-mail address.");
        return false;
      }

      // Check if the user exists in the database
      axios.get('http://'+hostname+':8080/account/getbyemail?email='+this.state.priv_email, { })
        .then(function(res) {
          this.setState({priv_id: res.data.id});
          this.grantPrivilegesAction();
        }.bind(this))
        .catch(function(error) {
          alert("User doesn't exist");
        }.bind(this));
    }

    // Real action adding privileges
    grantPrivilegesAction() {
      axios.post('http://'+hostname+':8080/privileges/create', {
        account: this.state.priv_id,
        document: this.state.id,
        read: this.state.priv_read,
        write: this.state.priv_write,
        delete: this.state.priv_delete
      })
      .then(function(res) {
          alert("Sucessfully granted privileges!");
      }.bind(this))
      .catch(function(response) {
        alert("Something went wrong while creating privliges.");
      }.bind(this));
    }

    onPrivEmailChange(e) {
      this.setState({ priv: {
        email: e.target.value
      }});
    }

    onCheckboxChange(state, name, e) {
      let current = state;
      let change = !(state);

      let privname = "priv_" + name;

      this.setState({ [privname]: change });
    
      console.log(this.state);
    }

    render () {
        const del = true;

        return (
            <section id="markdown-editor">
              <div className="container">
                <div className="row">
                  <div className="col-md-8 col-sm-12 col-xs-12">
                    <input  type="text" name="name" value={this.state.name} onChangeCapture={this.onNameChange} className="naziv-dokumenta" placeholder="Enter document name..." />
                      <hr/>
                    <div className="markdown-editor-collaborators">
                      <ul>
                      </ul>
                    </div>
                    <textarea id="editor" ref="editor" className="ql-editor" name="text" value={this.state.text} onChangeCapture={this.onTextChange}></textarea>
                  </div>
                  <div className="col-md-4 col-sm-12 col-xs-12">
                    <div className="panel panel-default">
                      <div className="panel-footer">Postavke</div>
                      <div className="panel-body">
                        <div className="add-collab">
                          <form action="" method="post">
                            <div className="form-group">
                              <label for="email">E-mail:</label>
                              <input type="text" name="email" className="form-control" placeholder="Enter users e-mail" onChange={this.onPrivEmailChange} value={this.state.priv_email} />
                            </div>
                            <label className="checkbox-inline"><input type="checkbox" name="read" onChange={(e) => this.onCheckboxChange(this.state.priv_read, 'read', e)} />Read</label>
                            <label className="checkbox-inline"><input type="checkbox" name="write" onChange={(e) => this.onCheckboxChange(this.state.priv_write, "write", e)} />Write</label>
                            <label className="checkbox-inline"><input type="checkbox" name="delete" onChange={(e) => this.onCheckboxChange(this.state.priv_delete, "delete", e)} />Delete</label>

                            <input type="submit" name="submit" value="Add collaborator" className="btn btn-primary" style={{width: "100%", padding: "15px 0", marginTop: "15px"}} onClick={this.grantPrivlieges}/>
                          </form> 

                          <hr />
                          {
                            del
                            &&
                            (
                              <div>
                                <label>You are able to delete this document permanently.</label>
                                <button className="btn btn-danger" style={{width: "100%", padding: "15px 0", marginTop: "15px"}} onClick={this.deleteDocument}>
                                  Delete document
                                </button>
                              </div>
                            )
                          }
                          
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
        );
    }
};

export default Markdown;