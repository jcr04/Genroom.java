package com.projetos.genroom.evento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.projetos.genroom.application.EventoService;
import com.projetos.genroom.domain.Evento;
import com.projetos.genroom.infrastructure.EventoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    void whenFindAll_thenReturnAllEvents() {
        Evento evento1 = new Evento("Titulo 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Evento evento2 = new Evento("Titulo 2", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        List<Evento> allEventos = Arrays.asList(evento1, evento2);

        when(eventoRepository.findAll()).thenReturn(allEventos);

        List<Evento> found = eventoService.findAll();

        assertEquals(allEventos.size(), found.size(), "O serviço deve retornar todos os eventos");
    }

    @Test
    void whenSaveEvent_thenReturnSavedEvent() {
        Evento evento = new Evento("Titulo", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        when(eventoRepository.save(evento)).thenReturn(evento);

        Evento savedEvento = eventoService.save(evento);

        assertEquals(evento.getTitulo(), savedEvento.getTitulo(), "O evento salvo deve ter o mesmo título");
    }

}

