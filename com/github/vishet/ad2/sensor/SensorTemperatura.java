package com.github.vishet.ad2.sensor;

import com.github.vishet.ad2.Leitura;

public class SensorTemperatura extends Sensor {
  // CRITICO < 0° <= NORMAL <= 35° < ALERTA <= 40° < CRITICO
  private static final int limiteInferiorCritico = 0;
  private static final int limiteAlerta = 35;
  private static final int limiteSuperiorCritico = 40;

  public SensorTemperatura(String id, String descricaoLocal) {
    super(id, descricaoLocal);
  }

  @Override
  public Classificacao getClassificacao(Leitura leitura) {
    int valor = leitura.getValor();
    if (valor < limiteInferiorCritico || valor > limiteSuperiorCritico) {
      return Classificacao.CRITICO;
    } else if (valor > limiteAlerta) {
      return Classificacao.ALERTA;
    } else {
      return Classificacao.NORMAL;
    }
  }
}
