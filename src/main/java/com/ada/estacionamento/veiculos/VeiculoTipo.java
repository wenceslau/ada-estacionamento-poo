package com.ada.estacionamento.veiculos;

import com.ada.estacionamento.Veiculo;

import java.util.Arrays;

public enum VeiculoTipo {
    CARRO,
    MOTO;

    public Veiculo criarInstancia(String placa) {
        return switch (this) {
            case CARRO -> new Carro(placa);
            case MOTO -> new Moto(placa);
        };
    }

    public static VeiculoTipo converter(String vehicleType) {
        try {
            return VeiculoTipo.valueOf(vehicleType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo veiculo invalido. Deve ser: " + Arrays.toString(VeiculoTipo.values()));
        }
    }

}
