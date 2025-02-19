package com.jeiup.fitnessap.service;

import com.jeiup.fitnessap.model.MyUserDetails;
import com.jeiup.fitnessap.model.User;
import com.jeiup.fitnessap.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                    String.format("User with username '%s' not found", username)));
    }
}
