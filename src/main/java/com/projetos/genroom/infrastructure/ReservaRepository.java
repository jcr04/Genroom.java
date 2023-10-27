package com.projetos.genroom.infrastructure;

import com.projetos.genroom.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Método personalizado para buscar reservas por intervalo de tempo
    List<Reserva> findByInicioBetween(LocalDateTime start, LocalDateTime end);

    // Método personalizado para buscar reservas por sala
    List<Reserva> findBySala_Id(Long salaId);

    // Novos métodos:

    // Método personalizado para buscar reservas por evento
    List<Reserva> findByEvento_Id(Long eventoId);

    // Método personalizado para buscar reservas por status
    List<Reserva> findByStatus(String status);

    // Método personalizado para buscar reservas por sala e intervalo de tempo
    List<Reserva> findBySala_IdAndInicioBetween(Long salaId, LocalDateTime start, LocalDateTime end);

    // Método para atualizar o status de uma reserva
    @Modifying
    @Query("update Reserva r set r.status = ?1 where r.id = ?2")
    void updateStatus(String status, Long reservaId);

    // Método para deletar reservas antigas
    @Modifying
    @Query("delete from Reserva r where r.fim < ?1")
    void deleteOldReservas(LocalDateTime now);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.sala s JOIN FETCH r.evento e WHERE r.id = :reservaId")
    Optional<Reserva> findByIdWithDetails(@Param("reservaId") Long reservaId);

}
