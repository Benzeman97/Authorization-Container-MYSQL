package com.benz.identity.server.config;

import com.benz.identity.server.exception.CustomOauthException;
import com.benz.identity.server.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private DataSource dataSource;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private SecurityProperties securityProperties;
    private TokenStore tokenStore;

    public AuthorizationServerConfig(DataSource dataSource,PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,UserDetailsService userDetailsService,
                                     SecurityProperties securityProperties){
        this.dataSource=dataSource;
        this.authenticationManager=authenticationManager;
        this.passwordEncoder=passwordEncoder;
        this.userDetailsService=userDetailsService;
        this.securityProperties=securityProperties;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Bean
    public TokenStore tokenStore(){
        if(tokenStore==null)
            tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return tokenStore;
    }

    @Bean
    public DefaultTokenServices defaultTokenServices(TokenStore tokenStore, ClientDetailsService clientDetailsService){
        DefaultTokenServices tokenServices=new DefaultTokenServices();
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setAuthenticationManager(authenticationManager);
        return tokenServices;
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        SecurityProperties.JwtProperties jwtProperties =  securityProperties.getJwt();

        KeyPair keyPair = getKeyPair(jwtProperties,keyStoreKeyFactory(jwtProperties));

        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();

        accessTokenConverter.setKeyPair(keyPair);

        return accessTokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .exceptionTranslator(ex->{
                    if(ex instanceof OAuth2Exception)  {
                        OAuth2Exception oAuth2Exception = (OAuth2Exception) ex;
                        return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
                                .body(new CustomOauthException(oAuth2Exception.getMessage()));
                    } else
                        throw ex;
                }).accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore());
    }

    private KeyPair getKeyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory){
        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(),jwtProperties.getKeyPairPassword().toCharArray());
    }

    private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties){
     return new KeyStoreKeyFactory(jwtProperties.getKeyStore(),jwtProperties.getKeyStorePassword().toCharArray());
    }

    
}
