package com.pragma.powerup.smallsquearemicroservice.domain.model;

import java.util.Set;

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;
    private String dniNumber;
    private Set<Long> employeesIds;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, String phone, String urlLogo, Long idOwner, String dniNumber, Set<Long> employeesIds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.idOwner = idOwner;
        this.dniNumber = dniNumber;
        this.employeesIds = employeesIds;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getDniNumber() {
        return dniNumber;
    }

    public void setDniNumber(String dniNumber) {
        this.dniNumber = dniNumber;
    }

    public Set<Long> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(Set<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }
}
