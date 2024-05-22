package com.example.do_an_toeic.entity;

import com.example.do_an_toeic.utils.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends EntityBase {

    @Column(name = "username",length = 100,nullable = false)
    private String userName;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Column(name = "active", nullable = false, columnDefinition = "tinyint(1) default 1")
    private boolean active;

    private Boolean isDeleted = false;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "users_roles"
            ,joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roles;
}
