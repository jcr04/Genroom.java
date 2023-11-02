package com.projetos.genroom.reserva.service;

import com.projetos.genroom.application.ReservaService;
import com.projetos.genroom.domain.Reserva;
import com.projetos.genroom.infrastructure.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        reserva.setId(1L);
        reserva.setInicio(LocalDateTime.now());
        reserva.setFim(LocalDateTime.now().plusHours(2));
        // Configure other fields if needed
    }

    @Test
    void whenFindAll_thenReturnAllReservas() {
        when(reservaRepository.findAll()).thenReturn(List.of(reserva));
        List<Reserva> reservas = reservaService.findAll();
        assertEquals(1, reservas.size());
        verify(reservaRepository).findAll();
    }

    @Test
    void whenFindById_thenReturnReserva() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        Optional<Reserva> found = reservaService.findById(1L);
        assertTrue(found.isPresent());
        assertEquals(reserva.getId(), found.get().getId());
        verify(reservaRepository).findById(1L);
    }

    // More tests for other methods...
}

