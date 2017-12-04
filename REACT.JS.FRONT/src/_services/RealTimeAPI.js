import React from 'react';

export function getParameterByName(name) { 
    var url = window.location.href; 
    name = name.replace(/[\[\]]/g, "\\$&"); 
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex.exec(url); 
    if (!results) return null; 
    if (!results[2]) return ''; 
    return decodeURIComponent(results[2].replace(/\+/g, " ")); 
}

export function currentContent(document) {
    let ROOTURL = 'http://localhost:6400/';

    return new Promise((resolve, reject) => {
        fetch(ROOTURL + 'document/' + document, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        .then((response) => response.json())
        .then((res) => {
            resolve(res);
        })
        .catch((error) => {
            alert(error);
            reject(error);
        });
    });
}