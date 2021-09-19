package com.benz.identity.server.entity;

import com.benz.identity.server.db.EPermission;
import com.benz.identity.server.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "permission",schema = Schema.SECURITYDB,uniqueConstraints = {
        @UniqueConstraint(name = "name",columnNames = "name")
})
@Getter
@Setter
public class Permission {

    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private EPermission name;

}
