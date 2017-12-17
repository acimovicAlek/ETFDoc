package com.etfdoc.etfdoc.Models;

import javax.persistence.*;

@Entity
public class DocumentBlob {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private byte[] data;
    private String fileType;
    @OneToOne
    private Document document;
    private String name;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentBlob(byte[] data, String fileType, Document document, String name) {
        this.data = data;
        this.fileType = fileType;
        this.document = document;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DocumentBlob() {

    }

    public DocumentBlob(byte[] data, String fileType) {

        this.data = data;
        this.fileType = fileType;
    }

    public DocumentBlob(byte[] data, String fileType, Document document) {

        this.data = data;
        this.fileType = fileType;
        this.document = document;
    }
}
