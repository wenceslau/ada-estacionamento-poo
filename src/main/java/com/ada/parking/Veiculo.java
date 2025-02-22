package com.ada.parking;

public abstract class Veiculo {

    private String placa;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public double valorPagar(long minutos) {

        //O calculo do valor a pagar é o mesmo para todos
        //Usa-se o valor hora e hora adicional de cada tipo de veiculo

        if (minutos <= 5) {
            return 0;
        } else if (minutos <= 60) {
            return valorHora();
        } else {
            //Identifica a hora excedente
            //Ex: 61 minutos = 1 hora, 59 minutos = 0 hora
            long horaExtra = (minutos - 60) / 60;

            //Identifica o minuto excedente
            //Ex: 61 minutos = 1 minuto, 59 minutos = 59 minutos
            double minutoExtra = (minutos - 60) % 60;

            return valorHora() + //Valor da primeira hora
                   (horaExtra * valorHoraAdicional()) + //Valor das horas adicionais
                   (minutoExtra > 0 ? valorHoraAdicional() : 0); //Valor do minuto adicional
        }
    }

    public abstract double valorHora();

    public abstract double valorHoraAdicional();
}
