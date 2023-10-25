package com.projetos.genroom.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}


