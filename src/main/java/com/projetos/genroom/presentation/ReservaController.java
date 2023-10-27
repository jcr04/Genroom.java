package com.projetos.genroom.presentation;

import com.projetos.genroom.application.ReservaService;
import com.projetos.genroom.domain.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Endpoint para listar todas as reservas
    @GetMapping
    public List<Reserva> findAll() {
        return reservaService.findAll();
    }

    // Endpoint para buscar uma reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar uma nova reserva
    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    // Endpoint para atualizar uma reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        return reservaService.findById(id).map(existingReserva -> {
            reserva.setId(id);
            return ResponseEntity.ok(reservaService.save(reserva));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para deletar uma reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservaService.findById(id).ifPresent(reserva -> reservaService.deleteById(id));
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para buscar reservas por intervalo de tempo
    @GetMapping("/intervalo")
    public List<Reserva> findByInicioBetween(
            @RequestParam("inicio") String startStr,
            @RequestParam("fim") String endStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.parse(endStr, formatter);

        return reservaService.findByInicioBetween(start, end);
    }

    // Endpoint adicional para buscar reservas por sala
    @GetMapping("/sala/{salaId}")
    public List<Reserva> findBySala_Id(@PathVariable Long salaId) {
        return reservaService.findBySala_Id(salaId);
    }

    // Novos endpoints:

    // Endpoint para buscar reservas por evento
    @GetMapping("/evento/{eventoId}")
    public List<Reserva> findByEvento_Id(@PathVariable Long eventoId) {
        return reservaService.findByEvento_Id(eventoId);
    }

    // Endpoint para buscar reservas por status
    @GetMapping("/status/{status}")
    public List<Reserva> findByStatus(@PathVariable String status) {
        return reservaService.findByStatus(status);
    }

    // Endpoint para buscar reservas por sala e intervalo de tempo
    @GetMapping("/sala/{salaId}/intervalo")
    public List<Reserva> findBySala_IdAndInicioBetween(
            @PathVariable Long salaId,
            @RequestParam("inicio") String startStr,
            @RequestParam("fim") String endStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.parse(endStr, formatter);

        return reservaService.findBySala_IdAndInicioBetween(salaId, start, end);
    }
}
