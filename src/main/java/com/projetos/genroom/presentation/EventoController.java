package com.projetos.genroom.presentation;

import com.projetos.genroom.application.EventoService;
import com.projetos.genroom.domain.Evento;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> findAll() {
        return ResponseEntity.ok(eventoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> findById(@PathVariable Long id) {
        return eventoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.save(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.findById(id).map(existingEvent -> {
            evento.setId(id);
            return ResponseEntity.ok(eventoService.save(evento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return eventoService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Evento>> findByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(eventoService.findByTitulo(titulo));
    }

    @GetMapping("/descricao")
    public ResponseEntity<List<Evento>> findByDescricaoContaining(@RequestParam String descricao) {
        return ResponseEntity.ok(eventoService.findByDescricaoContaining(descricao));
    }

    @GetMapping("/data-inicio")
    public ResponseEntity<List<Evento>> findByDataHoraInicioBetween(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        return ResponseEntity.ok(eventoService.findByDataHoraInicioBetween(inicio, fim));
    }

    @GetMapping("/data-fim")
    public ResponseEntity<List<Evento>> findByDataHoraFimBetween(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        return ResponseEntity.ok(eventoService.findByDataHoraFimBetween(inicio, fim));
    }

    @GetMapping("/localizacao/{localizacao}")
    public ResponseEntity<List<Evento>> findByLocalizacao(@PathVariable String localizacao) {
        return ResponseEntity.ok(eventoService.findByLocalizacao(localizacao));
    }

    @GetMapping("/responsavel/{responsavel}")
    public ResponseEntity<List<Evento>> findByResponsavel(@PathVariable String responsavel) {
        return ResponseEntity.ok(eventoService.findByResponsavel(responsavel));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Evento>> findByStatus(@PathVariable String status) {
        return ResponseEntity.ok(eventoService.findByStatus(status));
    }
}
