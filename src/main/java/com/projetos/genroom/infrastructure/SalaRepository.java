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

    @Query("SELECT s FROM Sala s WHERE NOT EXISTS (" +
            "SELECT r FROM Reserva r WHERE r.sala = s AND (" +
            ":inicio < r.fim AND :fim > r.inicio))")
    List<Sala> findAvailableRooms(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT s FROM Sala s WHERE s.capacidade >= :capacidade AND NOT EXISTS (" +
            "SELECT r FROM Reserva r WHERE r.sala = s AND (" +
            ":inicio < r.fim AND :fim > r.inicio))")
    List<Sala> findAvailableRoomsByCapacity(@Param("capacidade") int capacidade, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    List<Sala> findByNomeAndCapacidadeGreaterThanEqual(String nome, int capacidade);
}
