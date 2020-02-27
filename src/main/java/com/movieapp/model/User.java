package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Users")
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "hasDvd")
    private boolean hasDvd;

    @Column(name = "hasBluRay")
    private boolean hasBluRay;

    @Column(name = "has4k")
    private boolean has4k;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<Rating>();

    /**
     * empty constructor
     */
    public User() {

    }

    public User(String firstName, String lastName, String username, String password, boolean hasDvd, boolean hasBluRay, boolean has4k, Set<Rating> ratings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.hasDvd = hasDvd;
        this.hasBluRay = hasBluRay;
        this.has4k = has4k;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasDvd() {
        return hasDvd;
    }

    public void setHasDvd(boolean hasDvd) {
        this.hasDvd = hasDvd;
    }

    public boolean isHasBluRay() {
        return hasBluRay;
    }

    public void setHasBluRay(boolean hasBluRay) {
        this.hasBluRay = hasBluRay;
    }

    public boolean isHas4k() {
        return has4k;
    }

    public void setHas4k(boolean has4k) {
        this.has4k = has4k;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                hasDvd == user.hasDvd &&
                hasBluRay == user.hasBluRay &&
                has4k == user.has4k &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, password, hasDvd, hasBluRay, has4k);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hasDvd=" + hasDvd +
                ", hasBluRay=" + hasBluRay +
                ", has4k=" + has4k +
                '}';
    }
}