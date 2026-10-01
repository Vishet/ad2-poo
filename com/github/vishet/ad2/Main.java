package com.github.vishet.ad2;

import java.time.LocalDateTime;

import com.github.vishet.ad2.sensor.SensorNivelAgua;
import com.github.vishet.ad2.sensor.SensorQualidadeAr;
import com.github.vishet.ad2.sensor.SensorTemperatura;

public class Main {
  public static void main(String[] args) {
    SistemaMonitoramento sistema = new SistemaMonitoramento();

    // Criando sensores
    SensorQualidadeAr sensorQualidadeAr = new SensorQualidadeAr("S1", "Local 1");
    SensorTemperatura sensorTemperatura = new SensorTemperatura("S2", "Local 2");
    SensorNivelAgua sensorNivelAgua = new SensorNivelAgua("S3", "Local 3");

    // Adicionando sensores ao sistema
    sistema.addSensor(sensorQualidadeAr);
    sistema.addSensor(sensorTemperatura);
    sistema.addSensor(sensorNivelAgua);

    // Adicionando leituras aos sensores
    sensorQualidadeAr.addLeitura(new Leitura(32, LocalDateTime.now()));
    sensorQualidadeAr.addLeitura(new Leitura(55, LocalDateTime.now()));
    sensorQualidadeAr.addLeitura(new Leitura(120, LocalDateTime.now()));
    sensorTemperatura.addLeitura(new Leitura(-25, LocalDateTime.now()));
    sensorTemperatura.addLeitura(new Leitura(44, LocalDateTime.now()));
    sensorTemperatura.addLeitura(new Leitura(21, LocalDateTime.now()));
    sensorNivelAgua.addLeitura(new Leitura(1, LocalDateTime.now()));
    sensorNivelAgua.addLeitura(new Leitura(5, LocalDateTime.now()));
    sensorNivelAgua.addLeitura(new Leitura(2, LocalDateTime.now()));

    // Exibindo leituras dos sensores
    sistema.getSensor("S1").exibirLeituras();
    sistema.getSensor("S2").exibirLeituras();
    sistema.getSensor("S3").exibirLeituras();

    sistema.getSensor("S1").getLeituraMaisRecente();
    sistema.getSensor("S3").getLeituraMaisRecente();
  }
}