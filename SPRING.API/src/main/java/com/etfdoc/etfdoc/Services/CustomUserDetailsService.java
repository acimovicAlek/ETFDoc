package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Role;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Account userAccount = accountRepository.getAccountByEmail(s);
        if(userAccount == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(userAccount.getEmail(), userAccount.getPassword(), getGrantedAuthorities(userAccount));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(Account user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getRole() != null) {
            Role role = user.getRole();
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}