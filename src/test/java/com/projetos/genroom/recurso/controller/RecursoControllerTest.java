package com.projetos.genroom.recurso.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetos.genroom.application.RecursoService;
import com.projetos.genroom.domain.Recurso;
import com.projetos.genroom.presentation.RecursoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class RecursoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private RecursoService recursoService;

    @InjectMocks
    private RecursoController recursoController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(recursoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void findAll_ShouldReturnAllResources() throws Exception {
        when(recursoService.findAll()).thenReturn(Arrays.asList(new Recurso(), new Recurso()));

        mockMvc.perform(get("/api/recursos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findById_ShouldReturnResourceWhenExists() throws Exception {
        Long id = 1L;
        Recurso recurso = new Recurso();
        recurso.setId(id);
        recurso.setNome("Projetor");

        when(recursoService.findById(id)).thenReturn(Optional.of(recurso));

        mockMvc.perform(get("/api/recursos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recurso)));
    }

    @Test
    void create_ShouldReturnCreatedResource() throws Exception {
        Recurso recurso = new Recurso();
        recurso.setNome("Projetor");

        when(recursoService.save(any(Recurso.class))).thenReturn(recurso);

        mockMvc.perform(post("/api/recursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recurso)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recurso)));
    }


}

