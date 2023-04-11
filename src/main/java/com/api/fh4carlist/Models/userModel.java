package com.api.fh4carlist.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class userModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_cars_fk", referencedColumnName = "id")
    private List<carModel> ownedCars;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<carModel> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(List<carModel> ownedCars) {
        this.ownedCars = ownedCars;
    }
}
