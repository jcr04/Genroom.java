package com.projetos.genroom.presentation;

import com.projetos.genroom.application.ReservaService;
import com.projetos.genroom.domain.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
                .map(reserva -> ResponseEntity.ok(reserva))
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
    public List<Reserva> findByInicioBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return reservaService.findByInicioBetween(start, end);
    }

    // Endpoint adicional para buscar reservas por sala
    @GetMapping("/sala/{salaId}")
    public List<Reserva> findBySala_Id(@PathVariable Long salaId) {
        return reservaService.findBySala_Id(salaId);
    }
}

