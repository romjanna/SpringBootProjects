package com.jeiup.fitnessap.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenUnauthenticated_thenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/api/users"))
               .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUserAuthenticated_thenSuccess() throws Exception {
        mockMvc.perform(get("/api/users"))
               .andExpect(status().isOk());
    }
} 