package com.ada.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {

    private final Veiculo veiculo;
    private final LocalDateTime horaEntrada;

    private LocalDateTime horaSaida;
    private Duration duracao;
    private Double valorPagar;

    public Registro(Veiculo veiculo, LocalDateTime horaEntrada) {
        this.veiculo = veiculo;
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {

        if (horaSaida == null){
            throw new IllegalArgumentException("Check-out date is required");
        }
        if (horaSaida.isBefore(horaEntrada)){
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        this.horaSaida = horaSaida;
        this.duracao = Duration.between(horaEntrada, horaSaida);
        this.valorPagar = veiculo.valorPagar(duracao.toMinutes());
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public Double getValorPagar() {
        return valorPagar;
    }

}
