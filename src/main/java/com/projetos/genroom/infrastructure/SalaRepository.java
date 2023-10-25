package com.projetos.genroom.infrastructure;

import com.projetos.genroom.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    List<Sala> findByNome(String nome);

    List<Sala> findByCapacidadeGreaterThanEqual(int capacidade);

    @Query("SELECT s FROM Sala s WHERE NOT EXISTS (SELECT r FROM Reserva r WHERE r.sala = s AND (:inicio BETWEEN r.inicio AND r.fim OR :fim BETWEEN r.inicio AND r.fim))")
    List<Sala> findAvailableRooms(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}

