package com.benz.identity.server.dao;

import com.benz.identity.server.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsDao extends JpaRepository<OauthClientDetails,String> {
}
