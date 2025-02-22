package com.ada.parking;

import com.ada.parking.veiculos.VeiculoTipo;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        System.out.println("Informe a capacidade do estacionamento");
        int capacidade = Integer.parseInt(scanner.nextLine());
        var estacionamento = new Estacionamento(capacidade);

        String opcao;
        while (true) {
            System.out.println("===========================================================================");
            System.out.println("Opções");
            System.out.println("\t 1 - Digite a placa e o tipo do veículo");
            System.out.println("\t 0 - Sair");
            opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1" -> registrar(scanner, estacionamento);
                    case "0" -> System.exit(0);
                    default -> System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                System.err.println("Entrada de dados inválidos. " + e.getMessage());
            }
        }
    }

    private static void registrar(Scanner scanner, Estacionamento estacionamento) {

        System.out.println("Digite a placa e o tipo do veículo. Ex: ABC-1234 CARRO");
        var input = scanner.nextLine();
        var placa = input.split(" ")[0];
        var veiculoTipo = input.split(" ")[1];

        var veiculoTipoEnum = VeiculoTipo.converter(veiculoTipo);
        var veiculo = veiculoTipoEnum.criarInstancia(placa);

        var vehicleRegistration = estacionamento.registrarVeiculo(veiculo);

        if (vehicleRegistration.getHoraSaida() == null) {
            System.out.printf("Entrada do veículo com placa: %s realizada.%n", placa);
        } else {
            System.out.printf("Saida do veículo com placa: %s. Tempo: %d minutos. Valor a ser cobrado: R$ %.2f%n",
                    placa, vehicleRegistration.getDuracao().toMinutes(), vehicleRegistration.getValorPagar());
        }

        //Imprime o relatório
        relatorio(estacionamento);
    }

    private static void relatorio(Estacionamento estacionamento) {
        System.out.println("_______________________________________________________________________________");
        System.out.println("VEÍCULOS ESTACIONADOS: ");

        var registros = estacionamento.getRegistros();

        for (Registro registro : registros) {
            if (registro.getHoraSaida() == null) {
                var dataFormatada = registro.getHoraEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                System.out.printf("\tPlaca %s - Hora de entrada: %s %n", registro.getVeiculo().getPlaca(), dataFormatada);
            }
        }

        System.out.println();
        System.out.println("REGISTROS DE SAÍDAS: ");

        for (Registro registro : registros) {
            if (registro.getHoraSaida() != null) {
                var placa = registro.getVeiculo().getPlaca();
                var minutos = registro.getDuracao().toMinutes();
                var valorPagar = registro.getValorPagar();
                System.out.printf("\tPlaca %s - tempo permanência: %d minutos - valor cobrado: %.2f %n", placa, minutos, valorPagar);
            }
        }
    }

}
