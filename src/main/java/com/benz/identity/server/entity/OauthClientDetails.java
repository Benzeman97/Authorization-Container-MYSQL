package com.benz.identity.server.entity;

import com.benz.identity.server.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details",schema = Schema.SECURITYDB)
@Getter
@Setter
public class OauthClientDetails {

    @Id
    @Column(name="client_id")
    private String clientId;
    @Column(name="client_secret")
    private String clientSecret;
    @Column(name="web_server_redirect_uri")
    private String webServerRedirectUri;
    @Column(name = "scope")
    private String scope;
    @Column(name="access_token_validity")
    private long accessTokenValidity;
    @Column(name = "refresh_token_validity")
    private long refreshTokenValidity;
    @Column(name = "resource_ids")
    private String resourceIds;
    @Column(name="authorized_grant_types")
    private String authorizedGrantTypes;
    @Column(name="authorities")
    private String authorities;
    @Column(name="additional_information")
    private String additionalInformation;
    @Column(name = "autoapprove")
    private String autoapprove;


}
