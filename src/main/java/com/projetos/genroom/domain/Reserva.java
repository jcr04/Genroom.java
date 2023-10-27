package com.projetos.genroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime inicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fim;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    private String status;
    private String notas;

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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    // Métodos auxiliares
    public Duration getDuracao() {
        return Duration.between(inicio, fim);
    }

    public boolean isDataValida() {
        return inicio.isBefore(fim);
    }

    public void confirmarReserva() {
        this.status = "Confirmada";
    }

    public void cancelarReserva() {
        this.status = "Cancelada";
    }
}
