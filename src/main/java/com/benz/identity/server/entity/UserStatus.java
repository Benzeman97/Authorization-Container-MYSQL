package com.benz.identity.server.entity;

import com.benz.identity.server.db.Schema;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;

@Entity
@Table(name = "user_status",schema = Schema.SECURITYDB)
@Getter
@Setter
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="status_id")
    private int statusId;
    @Column(name = "active")
    private int active;
    @Column(name = "acc_non_expired")
    private int accNonExpired;
    @Column(name="credential_non_expired")
    private int credentialNonExpired;
    @Column(name="acc_non_locked")
    private int accNonLocked;

}
