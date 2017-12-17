package com.etfdoc.etfdoc.Services;
import com.etfdoc.etfdoc.Models.Document;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.exc.ReqlError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import org.json.simple.JSONObject;
import org.springframework.security.web.firewall.RequestRejectedException;

import java.net.ConnectException;

public class RethinkDBService {

    private final static RethinkDB r = RethinkDB.r;
    private Connection conn;


    public RethinkDBService() throws ConnectException {
        try {
            connectToRDB();
        }catch(ConnectException e){
            throw new ConnectException(e.getLocalizedMessage());
        }
    }

    private void connectToRDB() throws ConnectException {

        conn = r.connection().hostname("localhost").port(28015).connect();

        if (conn == null){
            throw new ConnectException("Failed to connect to ReDB!");
        }

    }

    public JSONObject createDocument(Document document){

        Cursor cursor = r.table("document").filter(row -> row.g("id").eq(document.getId())).run(conn);

        if(cursor != null) throw new RequestRejectedException("Document with the same ID already exists in ReDB");
        else{
            JSONObject response = r.table("document")
                    .insert(r.array(
                            r.hashMap("id", document.getId())
                            .with("name", document.getName())
                            .with("content", "")
                    )).run(conn);
            return response;
        }

    }

    public JSONObject deleteDocument(Document document){

        try {
            JSONObject response = r.table("document")
                    .filter(row -> row.g("id")
                            .eq(document.getId()).delete())
                    .run(conn);
            return response;    
        }catch (Exception e){
            throw new RequestRejectedException(
                    "Unable to delete document from ReDB!\n"+
                    e.getLocalizedMessage()
            );
        }

    }

}
