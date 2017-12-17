package com.etfdoc.etfdoc.Models;

import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.DETACH, targetEntity = Account.class)
    private  Account owner;
    private Boolean privateFlag = false;
    private Boolean native_flag = true;
    @DateTimeFormat
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date date;

    public Boolean getNative_flag() {
        return native_flag;
    }

    public void setNative_flag(Boolean native_flag) {
        this.native_flag = native_flag;
    }

    public Document() {}

    public Document(String name, Account owner, Boolean private_flag) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.native_flag = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(Boolean private_flag) {
        this.privateFlag = private_flag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}