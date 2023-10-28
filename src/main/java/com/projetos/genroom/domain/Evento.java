package com.projetos.genroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String titulo;

    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NonNull
    private LocalDateTime dataHoraInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NonNull
    private LocalDateTime dataHoraFim;

    private String localizacao;

    private String responsavel;

    private String status;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
        reserva.setEvento(this);
    }

    public void removeReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.setEvento(null);
    }
}
