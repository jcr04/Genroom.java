package com.projetos.genroom.evento.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.projetos.genroom.domain.Evento;
import com.projetos.genroom.infrastructure.EventoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventoRepositoryTest {

    @Mock
    private EventoRepository eventoRepository;

    private List<Evento> eventos;

    @BeforeEach
    void setUp() {
        Evento evento = new Evento("Conferência Java", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        evento.setStatus("Confirmado");
        eventos = new ArrayList<>();
        eventos.add(evento);
    }


    @Test
    void whenFindByTitulo_thenReturnList() {
        // Dado (Given)
        String titulo = "Conferência Java";
        when(eventoRepository.findByTitulo(titulo)).thenReturn(eventos);

        // Quando (When)
        List<Evento> foundEventos = eventoRepository.findByTitulo(titulo);

        // Então (Then)
        assertAll("findByTitulo retorna uma lista de eventos com o título correspondente",
                () -> assertNotNull(foundEventos, "A lista retornada não deve ser nula"),
                () -> assertFalse(foundEventos.isEmpty(), "A lista retornada não deve estar vazia"),
                () -> assertEquals(titulo, foundEventos.get(0).getTitulo(), "O título do evento deve corresponder ao critério de busca")
        );
    }

    @Test
    void whenFindByStatus_thenReturnList() {
        // Dado (Given)
        String status = "Confirmado";
        when(eventoRepository.findByStatus(status)).thenReturn(eventos);

        // Quando (When)
        List<Evento> foundEventos = eventoRepository.findByStatus(status);

        // Então (Then)
        assertAll("findByStatus retorna uma lista de eventos com o status correspondente",
                () -> assertNotNull(foundEventos, "A lista retornada não deve ser nula"),
                () -> assertFalse(foundEventos.isEmpty(), "A lista retornada não deve estar vazia"),
                () -> assertEquals(status, foundEventos.get(0).getStatus(), "O status do evento deve corresponder ao critério de busca")
        );
    }
}

