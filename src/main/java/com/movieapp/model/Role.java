package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Roles")
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(username, role.username) &&
                Objects.equals(user, role.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, username, user);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }
}
