package com.projetos.genroom.reserva.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projetos.genroom.application.ReservaService;
import com.projetos.genroom.domain.Reserva;
import com.projetos.genroom.presentation.ReservaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class ReservaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }

    @Test
    public void whenCreateReserva_thenReturnsReserva() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setInicio(LocalDateTime.now());
        reserva.setFim(LocalDateTime.now().plusHours(2));
        reserva.setStatus("Pendente");

        given(reservaService.save(any(Reserva.class))).willReturn(reserva);

        mockMvc.perform(post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Pendente"));
    }

    @Test
    public void whenFindById_thenReturnsReserva() throws Exception {
        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        reserva.setId(reservaId);
        reserva.setInicio(LocalDateTime.now());
        reserva.setFim(LocalDateTime.now().plusHours(2));
        reserva.setStatus("Pendente");

        given(reservaService.findById(reservaId)).willReturn(Optional.of(reserva));

        mockMvc.perform(get("/api/reservas/{id}", reservaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reservaId))
                .andExpect(jsonPath("$.status").value("Pendente"));
    }
}

