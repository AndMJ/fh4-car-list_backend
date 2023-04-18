package com.api.fh4carlist.Models.user;

import com.api.fh4carlist.Models.car.carModel;
import jakarta.persistence.*;

import java.util.Set;
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

    @ManyToMany
    @JoinTable(name = "user_cars_tb",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "car_id", referencedColumnName = "id")
            }
    )
    private Set<carModel> ownedCars; //set para nao existir duplicados

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

    public Set<carModel> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(Set<carModel> ownedCars) {
        this.ownedCars = ownedCars;
    }
}
