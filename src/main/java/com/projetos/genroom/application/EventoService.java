package com.projetos.genroom.application;

import com.projetos.genroom.domain.Evento;
import com.projetos.genroom.infrastructure.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> findByTitulo(String titulo) {
        return eventoRepository.findByTitulo(titulo);
    }

    public List<Evento> findByDescricaoContaining(String descricao) {
        return eventoRepository.findByDescricaoContaining(descricao);
    }
}

