package com.school.schoolstat.config.jwt;

import java.util.ArrayList;
import java.util.Collection;

import com.school.schoolstat.models.entities.Account;
import com.school.schoolstat.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountServices.loadUserByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Account not found with username: " + username);
        }
        if (account.isDisable()) {
            throw new RuntimeException("User is not authorised to connect");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new User(username, account.getPassword(), authorities);
    }

}

