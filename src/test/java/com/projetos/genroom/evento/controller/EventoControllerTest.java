package com.projetos.genroom.evento.controller;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import com.projetos.genroom.application.EventoService;
import com.projetos.genroom.domain.Evento;
import com.projetos.genroom.presentation.EventoController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EventoControllerTest {

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private EventoController eventoController;

    private Evento evento;
    private final Long eventoId = 1L;

    @BeforeEach
    void setUp() {
        evento = new Evento("Titulo do Evento", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        evento.setId(eventoId);
    }

    @Test
    void testCreateEvento() {
        // Dado (Given)
        given(eventoService.save(any(Evento.class))).willReturn(evento);

        // Quando (When)
        ResponseEntity<Evento> response = eventoController.create(evento);

        // Então (Then)
        assertAll("Deveria criar um evento e retornar o evento criado",
                () -> assertNotNull(response.getBody(), "O corpo da resposta não deveria ser nulo"),
                () -> assertEquals(OK, response.getStatusCode(), "O status da resposta deveria ser OK"),
                () -> assertEquals(eventoId, Objects.requireNonNull(response.getBody()).getId(), "O ID do evento retornado deveria ser igual ao evento criado")
        );

        verify(eventoService).save(any(Evento.class));
    }

    @Test
    void testFindById() {
        // Dado (Given)
        given(eventoService.findById(eventoId)).willReturn(Optional.of(evento));

        // Quando (When)
        ResponseEntity<Evento> response = eventoController.findById(eventoId);

        // Então (Then)
        assertAll("Deveria encontrar um evento pelo ID e retornar o evento encontrado",
                () -> assertTrue(response.getBody() != null && eventoId.equals(response.getBody().getId()), "O evento encontrado deveria ter o ID solicitado"),
                () -> assertEquals(OK, response.getStatusCode(), "O status da resposta deveria ser OK")
        );

        verify(eventoService).findById(eventoId);
    }
}

