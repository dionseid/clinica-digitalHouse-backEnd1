package com.dh.clinica.service.impl.auth;

import com.dh.clinica.model.auth.Usuarie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarieService usuarieService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarieService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " no encontrade"));
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarie appUser = usuarieRepository.findByUsername(username).orElseThrow((() -> new UsernameNotFoundException("user not found")));

        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();

        appUser.getRoles().stream().map(role -> grantList.add(new SimpleGrantedAuthority(role)));

        UserDetails user = null;
        user = (UserDetails) new User(username, appUser.getPassword(), grantList);

        return user;
    }*/
}
