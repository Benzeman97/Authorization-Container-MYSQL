package com.benz.identity.server.entity;

import com.benz.identity.server.db.ERole;
import com.benz.identity.server.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="role",schema = Schema.SECURITYDB,uniqueConstraints = {
        @UniqueConstraint(name="name",columnNames = "name")
})
@Getter
@Setter
public class Role {

    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="role_permission",
    joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private Set<Permission> permission;

}
