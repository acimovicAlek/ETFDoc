package com.etfdoc.etfdoc.Models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Privileges implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
    private Account account;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Document.class)
    private Document document;
    private Boolean read = false;
    private Boolean write = false;
    private Boolean update = false;

    public Privileges(Account account, Document document, Boolean read, Boolean write, Boolean update) {
        this.account = account;
        this.document = document;
        this.read = read;
        this.write = write;
        this.update = update;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }
}

