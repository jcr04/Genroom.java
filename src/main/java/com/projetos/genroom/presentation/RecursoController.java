package com.projetos.genroom.presentation;

import com.projetos.genroom.application.RecursoService;
import com.projetos.genroom.domain.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    // Endpoint para listar todos os recursos
    @GetMapping
    public List<Recurso> findAll() {
        return recursoService.findAll();
    }

    // Endpoint para buscar um recurso pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Recurso> findById(@PathVariable Long id) {
        return recursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo recurso
    @PostMapping
    public ResponseEntity<Recurso> create(@RequestBody Recurso recurso) {
        try {
            Recurso newRecurso = recursoService.save(recurso);
            return ResponseEntity.ok(newRecurso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint para atualizar um recurso
    @PutMapping("/{id}")
    public ResponseEntity<Recurso> update(@PathVariable Long id, @RequestBody Recurso recurso) {
        if (!recursoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        recurso.setId(id);
        return ResponseEntity.ok(recursoService.save(recurso));
    }

    // Endpoint para deletar um recurso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!recursoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        recursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para buscar recursos pelo nome
    @GetMapping("/nome/{nome}")
    public List<Recurso> findByNome(@PathVariable String nome) {
        return recursoService.findByNome(nome);
    }

}
