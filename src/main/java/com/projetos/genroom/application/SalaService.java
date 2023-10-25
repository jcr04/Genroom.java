package com.projetos.genroom.application;

import com.projetos.genroom.domain.Sala;
import com.projetos.genroom.infrastructure.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Optional<Sala> findById(Long id) {
        return salaRepository.findById(id);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public void deleteById(Long id) {
        salaRepository.deleteById(id);
    }

    public List<Sala> findByNome(String nome) {
        return salaRepository.findByNome(nome);
    }

    public List<Sala> findByCapacidadeGreaterThanEqual(int capacidade) {
        return salaRepository.findByCapacidadeGreaterThanEqual(capacidade);
    }

    public List<Sala> findAvailableRooms(LocalDateTime inicio, LocalDateTime fim) {
        return salaRepository.findAvailableRooms(inicio, fim);
    }
}
