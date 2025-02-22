package com.ada.parking.veiculos;


import com.ada.parking.Veiculo;

public class Moto extends Veiculo {

    public Moto(String placa) {
        super(placa);
    }

    @Override
    public double valorHora() {
        return 5.00;
    }

    @Override
    public double valorHoraAdicional() {
        return 6.00;
    }

}
