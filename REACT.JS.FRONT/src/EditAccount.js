import React, { Component } from 'react';

import './EditAccount.css';

import Menu from './common/Menu';
import Footer from './common/Footer';
import EditForm from './editAccount/EditForm';

class EditAccount extends Component {
  constructor() {
    super();
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
