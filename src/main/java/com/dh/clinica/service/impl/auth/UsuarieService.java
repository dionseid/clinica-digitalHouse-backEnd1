package com.dh.clinica.service.impl.auth;

import com.dh.clinica.entity.auth.Usuarie;
//import com.dh.clinica.repository.auth.RolRepository;
import com.dh.clinica.repository.auth.UsuarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarieService implements UserDetailsService {
    private final UsuarieRepository usuarieRepository;
    //private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarieService(UsuarieRepository usuarieRepository,/* RolRepository rolRepository,*/ BCryptPasswordEncoder passwordEncoder) {
        this.usuarieRepository = usuarieRepository;
        //this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*public Usuarie guardar(Usuarie usuarie) throws BadRequestException {
        if (usuarie == null)
            throw new BadRequestException("Le usuarie no puede ser null");
        if (usuarie.getDni() == null)
            throw new BadRequestException("El DNI del usuarie no puede ser null");
        rolRepository.saveAll(usuarie.getRoles());
        usuarie.setPassword(passwordEncoder.encode(usuarie.getPassword()));
        return usuarieRepository.save(usuarie);
    }*/

    public List<Usuarie> listar() {
        return usuarieRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarieRepository.findByUsername(username).orElseThrow((() -> new UsernameNotFoundException("user not found")));
    }


    //@Override
   //@Transactional(readOnly = true)
    //public UserDetails loadUserByUsername(String email) /*throws UsernameNotFoundException*/ {
        //return usuarieRepository.findByEmail(email).get();
        /*Optional<Usuarie> u = usuarieRepository.findByUsername(username);
        if (u.isEmpty())
            throw new UsernameNotFoundException("No existe le usuarie con username: " + username);

        Usuarie usuarie = u.get();
        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        for (Rol rol: usuarie.getRoles()) {
            autorizaciones.add(new SimpleGrantedAuthority(rol.getName()));
        }

        return new User(usuarie.getEmail(), usuarie.getPassword(), true, true, true, true, autorizaciones);*/
   // }
}
