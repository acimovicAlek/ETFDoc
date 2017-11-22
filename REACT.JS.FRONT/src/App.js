import React, { Component } from 'react';
import './App.css';

import Menu from './common/Menu';
import Footer from './common/Footer';

import Cover from './landing/Cover';
import About from './landing/About';


class App extends Component {
  render () {
    return (
      <div>
        <Cover />
        <About />
        <Footer />
      </div>
    );
  }
};

export default App;