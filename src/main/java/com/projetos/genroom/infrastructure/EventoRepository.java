package com.projetos.genroom.infrastructure;

import com.projetos.genroom.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Método personalizado para buscar eventos por título
    List<Evento> findByTitulo(String titulo);

    // Método personalizado para buscar eventos por descrição
    List<Evento> findByDescricaoContaining(String descricao);

    // Método personalizado para buscar eventos por data e hora de início
    List<Evento> findByDataHoraInicioBetween(LocalDateTime inicio, LocalDateTime fim);

    // Método personalizado para buscar eventos por data e hora de fim
    List<Evento> findByDataHoraFimBetween(LocalDateTime inicio, LocalDateTime fim);

    // Método personalizado para buscar eventos por localização
    List<Evento> findByLocalizacao(String localizacao);

    // Método personalizado para buscar eventos por responsável
    List<Evento> findByResponsavel(String responsavel);

    // Método personalizado para buscar eventos por status
    List<Evento> findByStatus(String status);
}
