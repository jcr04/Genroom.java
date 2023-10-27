package com.projetos.genroom.presentation;

import com.projetos.genroom.application.SalaService;
import com.projetos.genroom.domain.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    // Endpoint para listar todas as salas
    @GetMapping
    public List<Sala> findAll() {
        return salaService.findAll();
    }

    // Endpoint para buscar uma sala por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sala> findById(@PathVariable Long id) {
        return salaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar uma nova sala
    @PostMapping
    public ResponseEntity<Sala> create(@RequestBody Sala sala) {
        try {
            Sala newSala = salaService.save(sala);
            return ResponseEntity.ok(newSala);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/disponiveis/capacidade/{capacidade}")
    public List<Sala> findAvailableRoomsByCapacity(@PathVariable int capacidade, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim) {
        return salaService.findAvailableRoomsByCapacity(capacidade, inicio, fim);
    }

    @GetMapping("/nome/{nome}/capacidade/{capacidade}")
    public List<Sala> findByNomeAndCapacidadeGreaterThanEqual(@PathVariable String nome, @PathVariable int capacidade) {
        return salaService.findByNomeAndCapacidadeGreaterThanEqual(nome, capacidade);
    }

    // Endpoint para atualizar uma sala existente
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

    // Endpoint para deletar uma sala
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salaService.findById(id).ifPresent(sala -> salaService.deleteById(id));
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para buscar salas por nome
    @GetMapping("/nome/{nome}")
    public List<Sala> findByNome(@PathVariable String nome) {
        return salaService.findByNome(nome);
    }

    // Endpoint adicional para buscar salas por capacidade
    @GetMapping("/capacidade/{capacidade}")
    public List<Sala> findByCapacidadeGreaterThanEqual(@PathVariable int capacidade) {
        return salaService.findByCapacidadeGreaterThanEqual(capacidade);
    }

    // Endpoint adicional para buscar salas disponíveis em um intervalo de tempo
    @GetMapping("/disponiveis")
    public List<Sala> findAvailableRooms(@RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim) {
        return salaService.findAvailableRooms(inicio, fim);
    }
}

