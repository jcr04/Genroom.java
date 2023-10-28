package com.projetos.genroom.presentation;

import com.projetos.genroom.application.SalaService;
import com.projetos.genroom.domain.Sala;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @GetMapping
    public ResponseEntity<List<Sala>> findAll() {
        return ResponseEntity.ok(salaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> findById(@PathVariable Long id) {
        return salaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sala> create(@RequestBody Sala sala) throws Exception {
        return ResponseEntity.ok(salaService.save(sala));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> update(@PathVariable Long id, @RequestBody Sala sala) {
        return salaService.findById(id).map(existingSala -> {
            sala.setId(id);
            try {
                return ResponseEntity.ok(salaService.save(sala));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return salaService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Sala>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(salaService.findByNome(nome));
    }

    @GetMapping("/capacidade/{capacidade}")
    public ResponseEntity<List<Sala>> findByCapacidadeGreaterThanEqual(@PathVariable int capacidade) {
        return ResponseEntity.ok(salaService.findByCapacidadeGreaterThanEqual(capacidade));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Sala>> findAvailableRooms(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        return ResponseEntity.ok(salaService.findAvailableRooms(inicio, fim));
    }

    @GetMapping("/disponiveis/capacidade/{capacidade}")
    public ResponseEntity<List<Sala>> findAvailableRoomsByCapacity(
            @PathVariable int capacidade,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        return ResponseEntity.ok(salaService.findAvailableRoomsByCapacity(capacidade, inicio, fim));
    }

    @GetMapping("/nome/{nome}/capacidade/{capacidade}")
    public ResponseEntity<List<Sala>> findByNomeAndCapacidadeGreaterThanEqual(
            @PathVariable String nome,
            @PathVariable int capacidade) {
        return ResponseEntity.ok(salaService.findByNomeAndCapacidadeGreaterThanEqual(nome, capacidade));
    }
}
