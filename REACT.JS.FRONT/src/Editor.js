import React, { Component } from 'react';

import { mustAuthenticate } from './_services/Sessions';

import './Home.css';

import Menu from './common/Menu';
import Markdown from './edit/Markdown';

class Editor extends Component {
  constructor() {
    super();

  }

  componentWillMount() {
      mustAuthenticate();
  }

  componentDidMount() {
  
  }

  render () {
    return (
      <div>
        <Markdown
          params={this.props.match.params} />
      </div>
    );
  }
};

export default Editor;
