package com.iba.ministry.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_number", nullable = false, unique = true, length = 12)
    private String id_number;

    @JsonIgnore
    @ManyToMany(mappedBy = "memberMinistry", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Ministry> ministries = new HashSet<>();

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "first_name", nullable = false, length = 50)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String last_name;

    @Column(name = "sex", nullable = false)
    private Character sex;

    @Column(name = "age")
    private int age;

    @Column(name = "phone", length = 50)
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_day", length = 25)
    private Date birth_day;

    @Column(name = "home_address", length = 25)
    private String home_address;

    @Column(name = "email", unique = true, length = 25, nullable = false)
    private String email;

    @Column(name = "active")
    private boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Member() {
    }

    public Member(Long id, String id_number, Set<Ministry> ministries, String name, String first_name,
                  String last_name, Character sex, int age, String phone, Date birth_day, String home_address,
                  String email, boolean active) {
        this.id = id;
        this.id_number = id_number;
        this.ministries = ministries;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.birth_day = birth_day;
        this.home_address = home_address;
        this.email = email;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth_day() { return birth_day; }

    public void setBirth_day(Date birth_day) { this.birth_day = birth_day; }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Set<Ministry> getMinistries() {
        return ministries;
    }

    public void setMinistries(Set<Ministry> ministries) {
        this.ministries = ministries;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}