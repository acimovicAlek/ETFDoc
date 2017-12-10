import React, { Component } from 'react';

import './Home.css';

import Menu from './common/Menu';
import Footer from './common/Footer';
import HomePanel from './home/HomePanel';

class Home extends Component {
  constructor() {
    super();
  }

  componentWillMount() {

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
