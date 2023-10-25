package com.projetos.genroom.presentation;

import com.projetos.genroom.application.EventoService;
import com.projetos.genroom.domain.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Endpoint para listar todos os eventos
    @GetMapping
    public List<Evento> findAll() {
        return eventoService.findAll();
    }

    // Endpoint para buscar um evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> findById(@PathVariable Long id) {
        return eventoService.findById(id)
                .map(evento -> ResponseEntity.ok(evento))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo evento
    @PostMapping
    public Evento create(@RequestBody Evento evento) {
        return eventoService.save(evento);
    }

    // Endpoint para atualizar um evento existente
    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.findById(id).map(existingEvent -> {
            evento.setId(id);
            return ResponseEntity.ok(eventoService.save(evento));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para deletar um evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventoService.findById(id).ifPresent(evento -> eventoService.deleteById(id));
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para buscar eventos por título
    @GetMapping("/titulo/{titulo}")
    public List<Evento> findByTitulo(@PathVariable String titulo) {
        return eventoService.findByTitulo(titulo);
    }

    // Endpoint adicional para buscar eventos por descrição
    @GetMapping("/descricao")
    public List<Evento> findByDescricaoContaining(@RequestParam String descricao) {
        return eventoService.findByDescricaoContaining(descricao);
    }
}

