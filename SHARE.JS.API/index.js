var r = require('rethinkdb');
var express = require('express');
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

// Setting up the RethinkDB connection
r.connect({ host: 'localhost', port: 28015 }, function(err, conn) {
    if(err) throw err;

    // Create database if it deson't exist
    r.dbList().contains('etfdoc-rt').do(function(databaseExists) {
        return r.branch(
            databaseExists,
            { dbs_created: 0 },
            r.dbCreate('etfdoc-rt')
        );
    }).run(conn);

    // Now check the database
    r.db('etfdoc-rt').tableList().run(conn, function(err, response) {
        if(response.indexOf('documents') > -1) {
            console.log('Table "documents" already exists. Skipping the creation process.');
            console.log('Tables created - ' + response);
        } else {
            console.log('Table "documents" does not exist. Creating it...');
            r.db('etfdoc-rt').tabelCreate('documents').run(conn);
        }
    });

    // Setting up the sockets
    io.on('connection', function(socket) {
        console.log("Someone connected to the socket.");

        // Show if someone has disconnected
        socket.on('disconnect', function() {
            console.log("Someone disconnected from the socket.");
        });

        // Document update 
        // Anyway, this part of code creates a new document with the following id if it doesn't exists
        // If it does exist, it updates the conflicted 
        socket.on('document-update', function(data) {
            r.table('documents').insert({ id: data.id, value: data.value, user: data.user}, {conflict: 'update'}).run(conn, function(err, res) {
                if(err) throw err;
            });
        });
    });
});

// Inital page 
app.get('/', function(req, res) {
    res.send('Wohooo it works!');
});

// Setup express listner
http.listen(6400, '0.0.0.0', function() {
    console.log('ETFDoc Node API listening on: 0.0.0.0:6400');
});


