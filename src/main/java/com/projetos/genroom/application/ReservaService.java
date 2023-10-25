package com.projetos.genroom.application;

import com.projetos.genroom.domain.Reserva;
import com.projetos.genroom.infrastructure.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> findByInicioBetween(LocalDateTime start, LocalDateTime end) {
        return reservaRepository.findByInicioBetween(start, end);
    }

    public List<Reserva> findBySala_Id(Long salaId) {
        return reservaRepository.findBySala_Id(salaId);
    }
}

