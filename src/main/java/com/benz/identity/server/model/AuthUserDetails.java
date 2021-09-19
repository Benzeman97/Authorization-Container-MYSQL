package com.benz.identity.server.model;

import com.benz.identity.server.entity.UserStatus;
import com.benz.identity.server.entity.VoyageUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authoritis;
    private boolean accNonExpired;
    private boolean accNonLocked;
    private boolean credentialNonExpired;
    private boolean active;

    public AuthUserDetails(String username,String password,List<GrantedAuthority> authorities,
                           boolean accNonExpired,boolean accNonLocked,boolean credentialNonExpired,boolean active){
        this.username=username;
        this.password=password;
        this.authoritis=authorities;
        this.accNonExpired=accNonExpired;
        this.accNonLocked=accNonLocked;
        this.credentialNonExpired=credentialNonExpired;
        this.active=active;
    }

    public static UserDetails builder(VoyageUser user){

         List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

         user.getRoles().forEach(role->{
               grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().getValue()));

               role.getPermission().forEach(perm->{
                   grantedAuthorities.add(new SimpleGrantedAuthority(perm.getName().getValue()));
               });
         });

        UserStatus userStatus = user.getUserStatus();

         return new AuthUserDetails(user.getEmail(),user.getPassword(),grantedAuthorities,(userStatus.getAccNonExpired()==1),
                 (userStatus.getAccNonLocked()==1),(userStatus.getCredentialNonExpired()==1),(userStatus.getActive()==1));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritis;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
