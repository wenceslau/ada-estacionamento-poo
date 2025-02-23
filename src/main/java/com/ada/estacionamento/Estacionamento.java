package com.ada.estacionamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Estacionamento {

    private final int capacidade;
    private final List<Registro> registros;

    public Estacionamento(int capacidade) {
        if (capacidade <= 0){
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        this.capacidade = capacidade;
        this.registros = new ArrayList<>();
    }

    public Registro registrarVeiculo(Veiculo veiculo) {

        if (veiculo == null){
            throw new IllegalArgumentException("Veiculo não pode ser nulo");
        }

        var optional = procurarRegistro(veiculo);

        if (optional.isEmpty()) {
            return registrarEntrada(veiculo);
        }else {
            Registro registro = optional.get();
            return registrarSaida(registro);
        }
    }

    private Optional<Registro> procurarRegistro(Veiculo veiculo) {
        return registros.stream()
                .filter(r -> r.getVeiculo().getPlaca().equals(veiculo.getPlaca()))
                .filter(r -> r.getHoraSaida() == null)
                .findFirst();
    }

    private Registro registrarEntrada(Veiculo veiculo) {
        int count = registros.stream()
                .filter(r -> r.getHoraSaida() == null)
                .mapToInt(r -> 1)
                .sum();

        if (count == capacidade) {
            throw new IllegalStateException("Estacionamento lotado");
        }

        var registro = new Registro(veiculo, LocalDateTime.now());
        registros.add(registro);
        return registro;
    }

    private Registro registrarSaida(Registro registration) {
        registration.setHoraSaida(LocalDateTime.now());
        return registration;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
