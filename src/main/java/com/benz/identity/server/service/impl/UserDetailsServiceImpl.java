package com.benz.identity.server.service.impl;

import com.benz.identity.server.dao.VoyageUserDao;
import com.benz.identity.server.entity.VoyageUser;
import com.benz.identity.server.exception.DataNotFoundException;
import com.benz.identity.server.model.AuthUserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    final private static Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

    private VoyageUserDao userDao;

    public UserDetailsServiceImpl(VoyageUserDao userDao){
        this.userDao=userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VoyageUser user = userDao.findById(username).orElseThrow(()->{
            LOGGER.error(String.format("user is not found with %s",username));
            return new DataNotFoundException(String.format("user is not found with %s",username));
        });

       return AuthUserDetails.builder(user);
    }
}

