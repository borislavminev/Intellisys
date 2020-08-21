package com.intellisoft.excelreader.service;


import com.intellisoft.excelreader.domain.entity.CustomUserDetails;
import com.intellisoft.excelreader.domain.entity.User;
import com.intellisoft.excelreader.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        optionalUser
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));

       return optionalUser
               .map(CustomUserDetails::new).get();
    }
}
