package com.github.vishet.ad2.sensor;

import com.github.vishet.ad2.Leitura;

public class SensorQualidadeAr extends SensorPositivo {
  // 50 < NORMAL <= 100 < CRITICO
  private static final int limiteAlerta = 50;
  private static final int limiteCritico = 100;

  public SensorQualidadeAr(String id, String descricaoLocal) {
    super(id, descricaoLocal);
  }

  @Override
  public Classificacao getClassificacao(Leitura leitura) {
    int valor = leitura.getValor();
    if (valor > limiteCritico) {
      return Classificacao.CRITICO;
    } else if (valor > limiteAlerta) {
      return Classificacao.ALERTA;
    } else {
      return Classificacao.NORMAL;
    }
  }

}
