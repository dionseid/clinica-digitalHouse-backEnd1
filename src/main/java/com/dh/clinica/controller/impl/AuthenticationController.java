package com.dh.clinica.controller.impl;

import com.dh.clinica.config.CustomPasswordEncoder;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.persistance.entity.auth.AuthenticationRequest;
import com.dh.clinica.persistance.entity.auth.AuthenticationResponse;
import com.dh.clinica.persistance.entity.auth.User;
import com.dh.clinica.persistance.repository.auth.UserRepository;
import com.dh.clinica.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    //@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @PostMapping("/authenticate")
     ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        //User user = userRepository.getUserByName(authenticationRequest.getUsername()).get();

        //try { // Verificar si logro que la siguiente línea arroje error
            /*authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), customPasswordEncoder.matchesPassword(authenticationRequest.getPassword(), user.getPassword()) ? authenticationRequest.getPassword() : null));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("incorrect");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse((jwt)));*/

        User user = userRepository.getUserByName(authenticationRequest.getUsername()).get();

        if (user != null && customPasswordEncoder.matchesPassword(authenticationRequest.getPassword(), user.getPassword())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse((jwt)));
        } else {return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }
}
