package com.benz.identity.server.dao;

import com.benz.identity.server.entity.VoyageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageUserDao extends JpaRepository<VoyageUser,String> {
}
