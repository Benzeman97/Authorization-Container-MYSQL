package com.benz.identity.server.entity;

import com.benz.identity.server.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "voyage_user",schema = Schema.SECURITYDB)
@Getter
@Setter
public class VoyageUser {

    @Id
    private String email;
    private String fname;
    private String lname;
    private String country;
    private LocalDate dob;
    private String password;

    @OneToOne(targetEntity = UserStatus.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",referencedColumnName = "status_id")
    private UserStatus userStatus;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "email")},
    inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private Set<Role> roles;

}
