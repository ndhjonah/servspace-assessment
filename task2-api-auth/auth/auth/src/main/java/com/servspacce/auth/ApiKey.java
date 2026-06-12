package com.servspacce.auth;

import jakarta.persistence.*;

@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;

    @Column(unique = true)
    private String keyValue;

    private boolean active;

    public ApiKey() {}

    public ApiKey(String ownerName, String keyValue) {
        this.ownerName = ownerName;
        this.keyValue  = keyValue;
        this.active    = true;
    }

    public Long getId()          { return id; }
    public String getOwnerName() { return ownerName; }
    public String getKeyValue()  { return keyValue; }
    public boolean isActive()    { return active; }
    public void setActive(boolean active) { this.active = active; }
}