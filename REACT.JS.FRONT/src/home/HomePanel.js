import React, { Component } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';

import DocumentPanel from './DocumentPanel';
import Upload from './Upload';
const protocol = window.location.protocol;
const hostname = window.location.hostname;

class HomePanel extends Component {

    constructor () {
        super();
        this.state = {
          title: 'Public Files',
          user: null,
          public:1,
        };

        this.createFile = this.createFile.bind(this);
        this.openPublic = this.openPublic.bind(this);
        this.openPrivate = this.openPrivate.bind(this);
    }

    componentWillMount() {
      // Get the user data
      let userinfo = jwtDecode(sessionStorage.getItem('token'));
      let email = userinfo.sub;

      let data = null;

      axios.get('http://'+hostname+':8080/account/getbyemail?email='+email, { })
      .then(function(res) {
          this.setState({user: res.data.id});
      }.bind(this))
      .catch(function(error) {
          console.log("palo");
          console.log(error.response);
      }.bind(this));
    }

    // Otvaranje public i private foldera
    openPublic() {
      // Kod za izlistavanje public foldera
      // Koristiti axios package
      this.setState({title:' Public files '});
      this.setState({public:1,});

    }

    openPrivate() {
      // Kod za izlistavanje private dokumenata
      // Koristiti this.state.id za id usera ili e-mail adresu, zavisi kako bude na backendu napravljeno
      // Koristiti također axios
      this.setState({title:' Private files '});
      this.setState({
        public:0,
      });

    }

    // Kod za kreiranje novog filea
    // Ovo se samo odnosi za tekstualni file
    createFile() {
      let documentName = prompt("Setup the document name");

      axios.post('http://'+hostname+':8080/document/create', {
        name: documentName,
        owner: this.state.user,
        private_flag: 1, // ovo stavlja da je uvijek private, jbg
        native_flag: 1 // hajmo probat ovo prepraviti da ne postoji više? potrebno shendlati dalje backendu ko god ovo bude radio
      })
      .then(this.handleCreateSuccess.bind(this))
      .catch(this.handleCreateError.bind(this));
    }

    // Uspješno kreiranje => dodjeljivanje svih mogućih privilegija
    // ono nije mi baš jasan razlika između write i update na backendu al haj
    // Uglavnom, potrebno je da API vraća json objekat odakel će se uuzimati document i redirektiati se dalje na njega
    handleCreateSuccess(response) {
      axios.post('http://'+hostname+':8080/privileges/create', {
        account: this.state.user,
        document: response.data.id,
        read: true,
        write: true,
        delete: true
      })
      .then(function(res) {
          window.location = '/document/' + res.data.document.id;
      }.bind(this))
      .catch(function(response) {
        alert("Something went wrong while creating privliges.");
      }.bind(this));
    }

    // Hendlanje neuspjelog pokušpaja kreiranja dokumenta
    handleCreateError(error) {
      console.log(error.response);
      alert("Something went wrong while creating the Document.");
    }

    // To Be Developed
    // Upload dokumenta (blob)

    render () {
        return (
            <section id="cover" className="cover-fix">

                    <div className="container container-home">

                        <div className="plain-panel">
                        <div className="row home-row">
                        <div className="col-md-2 col-sm-3 col-xs-5 home-col">
                        <div className="nav-side-menu">
                          <div className="add-file-wrapper">
                            <button className="btn btn-primary add-file-btn" onClick={this.createFile}>
                              <span className="glyphicon glyphicon-btn glyphicon-list-alt"></span><br></br>New text file
                            </button>
                          </div>

                          <Upload />

                          <div className="menu-list">
                            <ul id="menu-content" className="menu-content collapse out">
                              <li onClick={this.openPublic}>
                                <a href="#">
                                  <i className="fa fa-dashboard fa-lg"></i> Public Files
                                </a>
                              </li>
                              <li onClick={this.openPrivate}>
                                <a href="#">
                                  <i className="fa fa-dashboard fa-lg"></i> My Files
                                </a>
                              </li>

                            </ul>
                         </div>

                         </div>
                        </div>
                        <div className="col-md-10 col-sm-9 col-xs-7 home-col">
                          <h1 className="title">{this.state.title}</h1>
                          {
                            this.state.public
                            &&
                            <DocumentPanel publicFiles={true} />
                          }
                          {
                            !this.state.public
                            &&
                            <DocumentPanel publicFiles={false} />
                          }
                        </div>
                       </div>
                    </div>

                    </div>

                }
            </section>
        );
    }
};

export default HomePanel;
