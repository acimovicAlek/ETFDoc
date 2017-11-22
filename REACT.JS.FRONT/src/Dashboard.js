import React, { Component } from 'react';
import { mustAuthenticate } from './_services/Sessions';

import './App.css';

import Dash from './dashboard/Dashboard';
import Footer from './common/Footer';


class Dashboard extends Component {
  constructor() {
    super();
  }

  componentWillMount() {
      mustAuthenticate();
  }

  render () {
    return (
      <div>
        <Dash />
        <Footer />
      </div>
    );
  }
};

export default Dashboard;