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
        Reserva savedReserva = reservaRepository.save(reserva);
        return reservaRepository.findByIdWithDetails(savedReserva.getId()).orElse(savedReserva);
    }

    public boolean deleteById(Long id) {
        reservaRepository.deleteById(id);
        return false;
    }

    public List<Reserva> findByInicioBetween(LocalDateTime start, LocalDateTime end) {
        return reservaRepository.findByInicioBetween(start, end);
    }

    public List<Reserva> findBySala_Id(Long salaId) {
        return reservaRepository.findBySala_Id(salaId);
    }

    // Novos métodos:

    public List<Reserva> findByEvento_Id(Long eventoId) {
        return reservaRepository.findByEvento_Id(eventoId);
    }

    public List<Reserva> findByStatus(String status) {
        return reservaRepository.findByStatus(status);
    }

    public List<Reserva> findBySala_IdAndInicioBetween(Long salaId, LocalDateTime start, LocalDateTime end) {
        return reservaRepository.findBySala_IdAndInicioBetween(salaId, start, end);
    }

    public void updateStatus(String status, Long reservaId) {
        reservaRepository.updateStatus(status, reservaId);
    }

    public void deleteOldReservas(LocalDateTime now) {
        reservaRepository.deleteOldReservas(now);
    }
}
