import React, { Component } from 'react';
import openSocket from 'socket.io-client';

import './Home.css';

import Menu from './common/Menu';
import Footer from './common/Footer';
import HomePanel from './home/HomePanel';

const socket = openSocket('http://localhost:6400');

class Home extends Component {
  constructor() {
    super();
  }

  render () {
    return (
      <div>
        <HomePanel />
        <Footer />
      </div>
    );
  }
};

export default Home;
