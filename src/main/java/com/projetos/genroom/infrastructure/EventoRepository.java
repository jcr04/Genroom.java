package com.projetos.genroom.infrastructure;

import com.projetos.genroom.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Método personalizado para buscar eventos por título
    List<Evento> findByTitulo(String titulo);

    // Método personalizado para buscar eventos por descrição
    List<Evento> findByDescricaoContaining(String descricao);
}

