package com.jeiup.fitnessap.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.jeiup.fitnessap.repository.UserRepository;
import com.jeiup.fitnessap.model.User;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    private MyUserDetailsService userService;
    
    @BeforeEach
    void setUp() {
        userService = new MyUserDetailsService(userRepository);
    }
    
    @Test
    void whenCreateUser_thenSuccessful() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("Test@1234");
        
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        UserDetails savedUser = userService.loadUserByUsername(user.getUserName());
        assertNotNull(savedUser);
        verify(userRepository).save(any(User.class));
    }
} 