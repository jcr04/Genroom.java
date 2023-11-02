package com.projetos.genroom.recurso.repository;

import com.projetos.genroom.application.RecursoService;
import com.projetos.genroom.domain.Recurso;
import com.projetos.genroom.infrastructure.RecursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RecursoRepositoryTest {

    @Mock
    private RecursoRepository recursoRepository;

    @InjectMocks
    private RecursoService recursoService;

    @BeforeEach
    void setUp() {
        // Configuração inicial se necessário
    }

    @Test
    void findAll_ShouldReturnAllResources() {
        // Dado (Given)
        Recurso recurso1 = new Recurso();
        Recurso recurso2 = new Recurso();
        List<Recurso> expectedRecursos = Arrays.asList(recurso1, recurso2);

        when(recursoRepository.findAll()).thenReturn(expectedRecursos);

        // Quando (When)
        List<Recurso> actualRecursos = recursoService.findAll();

        // Então (Then)
        assertNotNull(actualRecursos);
        assertEquals(expectedRecursos.size(), actualRecursos.size());
        verify(recursoRepository).findAll();
    }

    @Test
    void findById_ShouldReturnResourceWhenPresent() {
        // Dado (Given)
        Long id = 1L;
        Optional<Recurso> expectedRecurso = Optional.of(new Recurso());

        when(recursoRepository.findById(id)).thenReturn(expectedRecurso);

        // Quando (When)
        Optional<Recurso> actualRecurso = recursoService.findById(id);

        // Então (Then)
        assertTrue(actualRecurso.isPresent());
        assertEquals(expectedRecurso, actualRecurso);
        verify(recursoRepository).findById(id);
    }
}

