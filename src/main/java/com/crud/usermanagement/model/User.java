package com.crud.usermanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "password")
    protected String password;

    @Column(name = "role")
    protected String role;

    public User() {
    }

    public User(String name, String password, String role) {
        super();
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(int id, String name, String password, String role) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}



