package com.omerterci.flightsearchapi.config;

import com.omerterci.flightsearchapi.controller.AirportController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SecurityConfig.class)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void whenUserWithRoleUser_thenAccessGrantedForUserApi() throws Exception {
        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk());
    }


    @Test
    public void whenUserNotAuthenticated_thenAccessDenied() throws Exception {
        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isUnauthorized());
    }
}
