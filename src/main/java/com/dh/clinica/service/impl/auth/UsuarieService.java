package com.dh.clinica.service.impl.auth;

import com.dh.clinica.model.auth.Roles;
import com.dh.clinica.model.auth.Usuarie;
//import com.dh.clinica.repository.auth.RolRepository;
import com.dh.clinica.model.dto.user.CreateUserDto;
import com.dh.clinica.repository.auth.UsuarieRepository;
import com.dh.clinica.service.BaseService;
import com.dh.clinica.util.exceptions.NewUserWithDifferentPasswordsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarieService extends BaseService<Usuarie, Long, UsuarieRepository> {
    private final PasswordEncoder passwordEncoder;

    public Optional<Usuarie> findByUsername(String username) {
        return this.repositorio.findByUsername(username);
    }

    public Usuarie guardar(CreateUserDto newUsuarie) {
        if (newUsuarie.getPassword().contentEquals(newUsuarie.getPassword2())) {
            Usuarie usuarie = Usuarie.builder()
                    .username(newUsuarie.getUsername())
                    .password(passwordEncoder.encode(newUsuarie.getPassword()))
                    .roles(Stream.of(Roles.USER).collect(Collectors.toSet()))
                    .build();

            try {
                return guardar(usuarie);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuarie ya existe");
            }
        } else throw new NewUserWithDifferentPasswordsException();
    }




    /*private final UsuarieRepository usuarieRepository;
    //private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarieService(UsuarieRepository usuarieRepository,/* RolRepository rolRepository,*/ /*BCryptPasswordEncoder passwordEncoder) {
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

    /*public List<Usuarie> listar() {
        return usuarieRepository.findAll();
    }




    //@Override
   //@Transactional(readOnly = true)
    //public UserDetails loadUserByUsername(String email) /*throws UsernameNotFoundException*/ /*{
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
