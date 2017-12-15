var r = require('rethinkdb');
var express = require('express');
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var cors = require('cors')

// Setting up the RethinkDB connection
r.connect({ host: 'localhost', port: 28015, db: 'etfdocrt' }, function(err, conn) {
    if(err) throw err;

    // Create database if it deson't exist
    r.dbList().contains('etfdocrt').do(function(databaseExists) {
        return r.branch(
            databaseExists,
            { dbs_created: 0 },
            r.dbCreate('etfdocrt')
        );
    }).run(conn); 

    // Now check the database
    r.db('etfdocrt').tableList().run(conn, function(err, response) {
        if(response.indexOf('documents') > -1) {
            console.log('Table "documents" already exists. Skipping the creation process.');
            console.log('Tables created - ' + response);
        } else {
            console.log('Table "documents" does not exist. Creating it...');
            r.db('etfdocrt').tableCreate('documents').run(conn);
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
            if(data.content) {
                console.log("unio u bayz");
                r.table('documents').insert({ id: Number(data.id), content: data.content, name: data.name || null, user: data.user}, {conflict: 'update'}).run(conn, function(err, res) {
                    if(err) throw err;
                });
            } 
        });

        // Emiting document change to the socket
        r.table('documents').changes().run(conn, function(err, cursor) {
            if(err) throw err;
            cursor.each(function(err, row) {
                if(err) throw err;
                io.emit('document', row);
            });
        });

        // Get current content
        app.get('/document/:id/:user', function(req, res, next) {
            r.table('documents').get(Number(req.params.id)).
                run(conn, function(err, result) {
                    if(err) throw err;
                    
                    if(result == null) {
                        r.table('documents').insert({ id: Number(req.params.id), content: '', name: '' || null, user: req.params.user}, {conflict: 'update'}).run(conn, function(err, res) {
                            if(err) throw err;
                        });
                    } else {
                        res.send({"content" : result.content || '', "name" : result.name || ''});
                    }
                });
        });
    });
});

// Inital page 
app.get('/', function(req, res) {
    res.send('Wohooo it works!');
});

// Use cors
app.use(cors());

// Setup express listner
http.listen(6400, '0.0.0.0', function() {
    console.log('ETFDoc Node API listening on: 0.0.0.0:6400');
});


