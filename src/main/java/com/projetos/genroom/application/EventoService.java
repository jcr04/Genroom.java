package com.projetos.genroom.application;

import com.projetos.genroom.domain.Evento;
import com.projetos.genroom.infrastructure.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public boolean deleteById(Long id) {
        eventoRepository.deleteById(id);
        return false;
    }

    public List<Evento> findByTitulo(String titulo) {
        return eventoRepository.findByTitulo(titulo);
    }

    public List<Evento> findByDescricaoContaining(String descricao) {
        return eventoRepository.findByDescricaoContaining(descricao);
    }

    public List<Evento> findByDataHoraInicioBetween(LocalDateTime inicio, LocalDateTime fim) {
        return eventoRepository.findByDataHoraInicioBetween(inicio, fim);
    }

    public List<Evento> findByDataHoraFimBetween(LocalDateTime inicio, LocalDateTime fim) {
        return eventoRepository.findByDataHoraFimBetween(inicio, fim);
    }

    public List<Evento> findByLocalizacao(String localizacao) {
        return eventoRepository.findByLocalizacao(localizacao);
    }

    public List<Evento> findByResponsavel(String responsavel) {
        return eventoRepository.findByResponsavel(responsavel);
    }

    public List<Evento> findByStatus(String status) {
        return eventoRepository.findByStatus(status);
    }
}
