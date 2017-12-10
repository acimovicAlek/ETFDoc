import React from 'react'
import axios from 'axios';
import jwtDecode from 'jwt-decode';

export function mustAuthenticate() {
    if(sessionStorage.getItem('token') === null)
        window.location = '/login';
} 

export function mustNotAuthenticate() {
    if(sessionStorage.getItem('token') !== null)
        window.location = '/dashboard';
}

