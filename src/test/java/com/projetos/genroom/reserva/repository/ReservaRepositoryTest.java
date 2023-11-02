package com.projetos.genroom.reserva.repository;

import com.projetos.genroom.domain.Reserva;
import com.projetos.genroom.infrastructure.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservaRepository reservaRepository;

    private Reserva reserva;

    @BeforeEach
    public void setup() {
        reserva = new Reserva();
        reserva.setInicio(LocalDateTime.of(2023, 11, 1, 10, 0));
        reserva.setFim(LocalDateTime.of(2023, 11, 1, 12, 0));
        reserva = entityManager.persistFlushFind(reserva);
    }

    @Test
    public void whenFindByInicioBetween_thenFindReserva() {
        LocalDateTime start = LocalDateTime.of(2023, 11, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 11, 1, 13, 0);
        List<Reserva> foundReservas = reservaRepository.findByInicioBetween(start, end);
        assertFalse(foundReservas.isEmpty());
        assertTrue(foundReservas.contains(reserva));
    }
}
