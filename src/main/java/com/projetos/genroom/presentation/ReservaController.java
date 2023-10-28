package com.projetos.genroom.presentation;

import com.projetos.genroom.application.ReservaService;
import com.projetos.genroom.domain.Reserva;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        return reservaService.findById(id).map(existingReserva -> {
            reserva.setId(id);
            return ResponseEntity.ok(reservaService.save(reserva));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return reservaService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/intervalo")
    public ResponseEntity<List<Reserva>> findByInicioBetween(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        return ResponseEntity.ok(reservaService.findByInicioBetween(inicio, fim));
    }

    @GetMapping("/sala/{salaId}")
    public ResponseEntity<List<Reserva>> findBySala_Id(@PathVariable Long salaId) {
        return ResponseEntity.ok(reservaService.findBySala_Id(salaId));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Reserva>> findByEvento_Id(@PathVariable Long eventoId) {
        return ResponseEntity.ok(reservaService.findByEvento_Id(eventoId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reserva>> findByStatus(@PathVariable String status) {
        return ResponseEntity.ok(reservaService.findByStatus(status));
    }

    @GetMapping("/sala/{salaId}/intervalo")
    public ResponseEntity<List<Reserva>> findBySala_IdAndInicioBetween(
            @PathVariable Long salaId,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        return ResponseEntity.ok(reservaService.findBySala_IdAndInicioBetween(salaId, inicio, fim));
    }
}
