import React, { Component } from 'react';

import DocumentPanel from './DocumentPanel';
class HomePanel extends Component {

    constructor () {
        super();
        this.state = {
          title:'Public Files'
        };
    }

    openPublic=()=>{
      this.setState({
        title: 'Public Files'
      });
    }

    openPrivate=()=>{
      this.setState({
        title: 'Private Files'
      });
    }

    render () {
        return (
            <section id="cover" className="cover-fix">
                <div className="container container-home">
                    <div className="plain-panel">
                    <div className="row home-row">
                    <div className="col-md-2 col-sm-3 col-xs-5 home-col">
                    <div className="nav-side-menu">
                      <div className="add-file-wrapper">
                        <button className="btn btn-primary add-file-btn">
                          <span className="glyphicon glyphicon-btn glyphicon-list-alt"></span><br></br>New text file
                        </button>
                      </div>
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
                      <DocumentPanel />
                    </div>
                   </div>
                </div>
                </div>
            </section>
        );
    }
};

export default HomePanel;
