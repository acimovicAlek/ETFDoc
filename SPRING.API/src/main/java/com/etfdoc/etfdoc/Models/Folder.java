package com.etfdoc.etfdoc.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Folder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(cascade = Casca)
    private Folder owner;
}
