package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Role;

public class RoleVM {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public RoleVM(){}

    public RoleVM(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleVM(String name) {
        this.name = name;
    }

    public RoleVM(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
