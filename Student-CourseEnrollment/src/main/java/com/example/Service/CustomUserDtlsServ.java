package com.example.Service;

import com.example.Model.User;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDtlsServ implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmail(username);

        if (optUser.isPresent()){
            User user = optUser.get();

            List<GrantedAuthority> authorityList = new ArrayList<>();

            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());
            authorityList.add(sga);

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorityList);
        }else {
            throw new RuntimeException("Credentials Invalid!");
        }
    }
}
