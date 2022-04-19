package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.UserEntity;
import com.example.ihealtzstore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class iHealtzUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public iHealtzUserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        boolean demo = userRepository.existsByUsername(username);


        UserEntity userEntity =
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " was not found."));


        return mapToUserDetails(userEntity);

    }
    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities =
                userEntity.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
