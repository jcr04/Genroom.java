package com.projetos.genroom.recurso.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import com.projetos.genroom.application.RecursoService;
import com.projetos.genroom.domain.Recurso;
import com.projetos.genroom.infrastructure.RecursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RecursoServiceTest {

    @Mock
    private RecursoRepository recursoRepository;

    @InjectMocks
    private RecursoService recursoService;

    @Test
    void whenFindAll_thenReturnAllResources() {
        Recurso recurso1 = new Recurso();
        recurso1.setNome("Projetor");
        Recurso recurso2 = new Recurso();
        recurso2.setNome("Computador");
        List<Recurso> allRecursos = Arrays.asList(recurso1, recurso2);

        when(recursoRepository.findAll()).thenReturn(allRecursos);

        List<Recurso> found = recursoService.findAll();

        assertEquals(allRecursos.size(), found.size(), "O serviço deve retornar todos os recursos");
    }

    @Test
    void whenSaveResource_thenReturnSavedResource() {
        Recurso recurso = new Recurso();
        recurso.setNome("Projetor");

        when(recursoRepository.save(any(Recurso.class))).thenReturn(recurso);

        Recurso savedRecurso = recursoService.save(new Recurso());

        assertNotNull(savedRecurso, "O recurso salvo não deve ser nulo");
        assertEquals(recurso.getNome(), savedRecurso.getNome(), "O recurso salvo deve ter o mesmo nome");
    }

}

