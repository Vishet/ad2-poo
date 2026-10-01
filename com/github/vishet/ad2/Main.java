package com.github.vishet.ad2;

import java.time.LocalDateTime;

import com.github.vishet.ad2.excecao.IdConflitoException;
import com.github.vishet.ad2.excecao.IdInvalidoException;
import com.github.vishet.ad2.excecao.SemLeiturasException;
import com.github.vishet.ad2.sensor.Sensor;
import com.github.vishet.ad2.sensor.SensorNivelAgua;
import com.github.vishet.ad2.sensor.SensorQualidadeAr;
import com.github.vishet.ad2.sensor.SensorTemperatura;

public class Main {
  private static SistemaMonitoramento testarSistema() {
    SistemaMonitoramento sistema = new SistemaMonitoramento();

    // Criando sensores
    SensorQualidadeAr sensorQualidadeAr = new SensorQualidadeAr("S1_QUALIDADE_AR", "Local 1");
    SensorTemperatura sensorTemperatura = new SensorTemperatura("S2_TEMPERATURA", "Local 2");
    SensorNivelAgua sensorNivelAgua = new SensorNivelAgua("S3_NIVEL_AGUA", "Local 3");

    // Adicionando sensores ao sistema
    sistema.addSensor(sensorQualidadeAr);
    sistema.addSensor(sensorTemperatura);
    sistema.addSensor(sensorNivelAgua);

    // Adicionando leituras aos sensores
    sensorQualidadeAr.addLeitura(new Leitura(32, LocalDateTime.now()));
    sensorQualidadeAr.addLeitura(new Leitura(55, LocalDateTime.now()));
    sensorQualidadeAr.addLeitura(new Leitura(120, LocalDateTime.now()));
    sensorTemperatura.addLeitura(new Leitura(-25, LocalDateTime.now()));
    sensorTemperatura.addLeitura(new Leitura(38, LocalDateTime.now()));
    sensorTemperatura.addLeitura(new Leitura(21, LocalDateTime.now()));
    sensorNivelAgua.addLeitura(new Leitura(1, LocalDateTime.now()));
    sensorNivelAgua.addLeitura(new Leitura(5, LocalDateTime.now()));
    sensorNivelAgua.addLeitura(new Leitura(2, LocalDateTime.now()));

    // Exibindo leituras dos sensores
    sistema.getSensor("S1_QUALIDADE_AR").exibirLeituras();
    System.out.println();
    sistema.getSensor("S2_TEMPERATURA").exibirLeituras();
    System.out.println();
    sistema.getSensor("S3_NIVEL_AGUA").exibirLeituras();
    System.out.println("\n-----------------------------------\n");

    // Exibindo leituras mais recentes de alguns sensores
    sistema.getSensor("S1_QUALIDADE_AR").exibirLeituraMaisRecente();
    System.out.println();
    sistema.getSensor("S3_NIVEL_AGUA").exibirLeituraMaisRecente();
    System.out.println("\n-----------------------------------\n");

    // Exibindo a média das leituras do sensor S3
    double media = sistema.getSensor("S3_NIVEL_AGUA").getMediaLeituras();
    System.out
        .println(String.format("Média das leituras do sensor S3: %.2f", media));
    System.out.println("\n-----------------------------------\n");

    // Exibindo sensores com leituras anormais
    System.out.println("Sensores com leituras anormais:");
    for (Sensor sensor : sistema.getSensoresAnormais()) {
      System.out.println("ID: " + sensor.getId() + ", Local: " + sensor.getDescricaoLocal()
          + ", Classificação da leitura mais recente: " + sensor.getClassificacao(sensor.getLeituraMaisRecente()));
    }
    System.out.println("\n-----------------------------------\n");

    // Tentando adicionar um sensor com ID duplicado
    try {
      SensorQualidadeAr sensorDuplicado = new SensorQualidadeAr("S1_QUALIDADE_AR", "Local Duplicado");
      sistema.addSensor(sensorDuplicado);
    } catch (IdConflitoException e) {
      System.out.println(e.getMessage());
    }

    // Tentando consultar leituras de um sensor inexistente
    try {
      sistema.getSensor("S4").exibirLeituras();
    } catch (IdInvalidoException e) {
      System.out.println(e.getMessage());
    }

    // Tentando consultar a leitura mais recente de um sensor sem leituras
    try {
      SensorNivelAgua sensorSemLeituras = new SensorNivelAgua("S5", "Local 5");
      sistema.addSensor(sensorSemLeituras);
      sensorSemLeituras.exibirLeituraMaisRecente();
    } catch (SemLeiturasException e) {
      System.out.println(e.getMessage());
    }

    return sistema;
  };

  public static void main(String[] args) {
    if (args.length > 0) {
      String filePath = args[0];
      SistemaMonitoramento sistema = SistemaMonitoramento.carregar(filePath);

      if (sistema != null) {
        System.out.println("Sistema de monitoramento carregado com sucesso!");
      } else {
        System.out.println("Erro ao carregar o sistema de monitoramento.");
        return;
      }

      System.out.println("\n-----------------------------------\n");

      for (Sensor sensor : sistema.getSensores()) {
        sensor.exibirLeituras();
        System.out.println();
      }

      return;
    }

    SistemaMonitoramento sistema = testarSistema();
    if (sistema.salvar() == true)
      System.out.println("\nSistema de monitoramento salvo com sucesso!");
    else
      System.out.println("\nErro ao salvar o sistema de monitoramento.");
  }
}