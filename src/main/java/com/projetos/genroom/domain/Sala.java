package com.projetos.genroom.domain;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataModificacao;

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

    public String getDataCriacao() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(dataCriacao);
    }

    public String getDataModificacao() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dataModificacao != null ? sdf.format(dataModificacao) : null;
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

    @PrePersist
    protected void onCreate() {
        dataCriacao = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dataModificacao = new Date();
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
                ", reservas=" + (reservas != null ? reservas.size() : 0) +
                ", dataCriacao=" + getDataCriacao() +
                ", dataModificacao=" + getDataModificacao() +
                '}';
    }

    public boolean isDisponivel(String inicio, String fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime inicioDateTime = LocalDateTime.parse(inicio, formatter);
        LocalDateTime fimDateTime = LocalDateTime.parse(fim, formatter);

        for (Reserva reserva : reservas) {
            LocalDateTime reservaInicio = reserva.getInicio();
            LocalDateTime reservaFim = reserva.getFim();
            if ((inicioDateTime.isEqual(reservaInicio) || inicioDateTime.isAfter(reservaInicio)) && inicioDateTime.isBefore(reservaFim) ||
                    (fimDateTime.isAfter(reservaInicio) && fimDateTime.isBefore(reservaFim) || fimDateTime.isEqual(reservaFim))) {
                return false;  // Sala não está disponível
            }
        }
        return true;  // Sala está disponível
    }
}
