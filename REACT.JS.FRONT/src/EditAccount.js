import React, { Component } from 'react';

import { mustAuthenticate } from './_services/Sessions';

import './EditAccount.css';

import Menu from './common/Menu';
import Footer from './common/Footer';
import EditForm from './editAccount/EditForm';

class EditAccount extends Component {
  constructor() {
    super();
  }

  componentWillMount() {
      mustAuthenticate();
  }

  render () {
    return (
      <div>
        <EditForm />
        <Footer />
      </div>
    );
  }
};

export default EditAccount;
