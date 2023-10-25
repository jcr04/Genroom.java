package com.projetos.genroom.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "salas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private int capacidade;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}


