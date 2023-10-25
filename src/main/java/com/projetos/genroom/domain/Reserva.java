package com.projetos.genroom.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public Sala getSala() {
        return sala;
    }

    public Evento getEvento() {
        return evento;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

