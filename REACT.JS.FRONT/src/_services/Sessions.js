import React from 'react'
import  { Redirect } from 'react-router-dom'

export function mustAuthenticate() {
    if(sessionStorage.getItem('userData') === null)
        window.location = '/login';
} 

export function mustNotAuthenticate() {
    if(sessionStorage.getItem('userData') !== null)
        window.location = '/dashboard';
}
