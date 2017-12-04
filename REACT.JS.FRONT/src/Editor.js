import React, { Component } from 'react';

import './Home.css';

import Menu from './common/Menu';
import Markdown from './edit/Markdown';

class Editor extends Component {
  constructor() {
    super();
  }

  render () {
    return (
      <div>
        <Markdown />
      </div>
    );
  }
};

export default Editor;
