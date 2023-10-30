package com.projetos.genroom.application;

import com.projetos.genroom.domain.Recurso;
import com.projetos.genroom.infrastructure.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    // Método para buscar todos os recursos
    public List<Recurso> findAll() {
        return recursoRepository.findAll();
    }

    // Método para buscar um recurso pelo ID
    public Optional<Recurso> findById(Long id) {
        return recursoRepository.findById(id);
    }

    // Método para buscar recursos pelo nome
    public List<Recurso> findByNome(String nome) {
        return recursoRepository.findByNome(nome);
    }

    // Método para criar ou atualizar um recurso
    public Recurso save(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    // Método para deletar um recurso pelo ID
    public void deleteById(Long id) {
        recursoRepository.deleteById(id);
    }

    // Método para verificar se um recurso existe pelo ID
    public boolean existsById(Long id) {
        return recursoRepository.existsById(id);
    }

}
