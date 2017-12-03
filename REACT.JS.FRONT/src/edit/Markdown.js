import React, { Component } from 'react';

class Markdown extends Component {

    constructor () {
        super();
        this.state = {
          content: '';
          id: this.props.location.query.document,
          user: sessionStorage.getItem('userData').id
        };
    },
    componentWillMount() {
      // Prije nego se komponenta ucita 
      // Provjri authentikaciju
      // mustAuthenticate();

      // Provjeri da li korisnik ima privliegije da uređuje ovaj dokument
      // Ako nema treba redirektati negdje, gdje god
      // Ispisati prouku da ne može pristupiti uređivanju
    },
    handleChange: function(event) {
      this.setState({value: event.target.value});
    },
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
                        <li>mirza.ohranovic@etf.unsa.ba</li>
                        <li>aleksandar.acimovic@etf.unsa.ba</li>
                      </ul>
                    </div>
                  </div>
                  <div className="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <textarea name="content" value={this.state.content} onChange={this.handleChange}></textarea>
                  </div>
                </div>
              </div>
            </section>
        );
    }
};

export default Markdown;