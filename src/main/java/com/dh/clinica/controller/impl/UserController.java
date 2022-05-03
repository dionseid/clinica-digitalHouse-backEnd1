package com.dh.clinica.controller.impl;

import com.dh.clinica.persistance.entity.auth.AuthenticationRequest;
import com.dh.clinica.persistance.entity.auth.AuthenticationResponse;
import com.dh.clinica.persistance.entity.auth.User;
import com.dh.clinica.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> user() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = new User();
        user.setUsername(userDetails.getUsername());

        return ResponseEntity.ok(user);
    }
}
