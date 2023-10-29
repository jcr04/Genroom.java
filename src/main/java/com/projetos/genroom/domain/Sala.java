package com.projetos.genroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "salas")
@Data
@NoArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private int capacidade;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reserva> reservasCollection;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataModificacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataModificacao = LocalDateTime.now();
    }

    public boolean isDisponivel(String inicio, String fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime inicioDateTime = LocalDateTime.parse(inicio, formatter);
        LocalDateTime fimDateTime = LocalDateTime.parse(fim, formatter);

        if (reservasCollection == null) {
            return true;  // Sala está disponível se não há reservas
        }

        for (Reserva reserva : reservasCollection) {
            LocalDateTime reservaInicio = reserva.getInicio();
            LocalDateTime reservaFim = reserva.getFim();
            if ((inicioDateTime.isEqual(reservaInicio) || inicioDateTime.isAfter(reservaInicio)) && inicioDateTime.isBefore(reservaFim) ||
                    (fimDateTime.isAfter(reservaInicio) && fimDateTime.isBefore(reservaFim) || fimDateTime.isEqual(reservaFim))) {
                return false;  // Sala não está disponível
            }
        }
        return true;  // Sala está disponível
    }

    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    public boolean getReservas() {
        return (reservasCollection != null && !reservasCollection.isEmpty());
    }

}
