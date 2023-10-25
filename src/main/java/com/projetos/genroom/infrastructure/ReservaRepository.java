package com.projetos.genroom.infrastructure;

import com.projetos.genroom.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Método personalizado para buscar reservas por intervalo de tempo
    List<Reserva> findByInicioBetween(LocalDateTime start, LocalDateTime end);

    // Método personalizado para buscar reservas por sala
    List<Reserva> findBySala_Id(Long salaId);
}

