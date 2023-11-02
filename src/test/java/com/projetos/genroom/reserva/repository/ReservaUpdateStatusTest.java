package com.projetos.genroom.reserva.repository;

import com.projetos.genroom.domain.Reserva;
import com.projetos.genroom.infrastructure.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaUpdateStatusTest {

    @Autowired
    private ReservaRepository reservaRepository;

    private Long reservaId;

    @BeforeTransaction
    public void setup() {
        Reserva reserva = new Reserva();
        reserva.setInicio(LocalDateTime.now());
        reserva.setFim(LocalDateTime.now().plusHours(2));
        reserva.setStatus("Pendente");
        reserva = reservaRepository.saveAndFlush(reserva);
        reservaId = reserva.getId();
    }

    @Test
    @Transactional
    @DirtiesContext
    public void whenUpdateStatus_thenStatusIsUpdated() {
        reservaRepository.updateStatus("Confirmada", reservaId);
        Reserva updatedReserva = reservaRepository.findById(reservaId).orElse(null);
        assertNotNull(updatedReserva);
        assertEquals("Confirmada", updatedReserva.getStatus());
    }
}

