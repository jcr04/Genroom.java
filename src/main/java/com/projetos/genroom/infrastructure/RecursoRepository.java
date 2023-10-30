package com.projetos.genroom.infrastructure;

import com.projetos.genroom.domain.Recurso;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    // Método para buscar todos os recursos
    @Override
    List<Recurso> findAll();

    // Método para buscar um recurso pelo ID
    @Override
    Optional<Recurso> findById(@NotNull Long id);

    // Método para salvar (criar ou atualizar) um recurso
    @Override
    <S extends Recurso> S save(S entity);

    // Método para deletar um recurso pelo ID
    @Override
    void deleteById(Long id);

    // Método para verificar se um recurso existe pelo ID
    @Override
    boolean existsById(Long id);

    // Método para buscar recursos pelo nome
    List<Recurso> findByNome(String nome);

}
