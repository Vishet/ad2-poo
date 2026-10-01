package com.github.vishet.ad2.sensor;

import com.github.vishet.ad2.Leitura;

public class SensorNivelAgua extends SensorPositivo {
  // 0 <= NORMAL < 2m <= ALERTA <= 3m < CRITICO
  private static final int limiteAlerta = 2;
  private static final int limiteCritico = 3;

  public SensorNivelAgua(String id, String descricaoLocal) {
    super(id, descricaoLocal);
  }

  @Override
  public Classificacao getClassificacao(Leitura leitura) {
    int valor = leitura.getValor();
    if (valor > limiteCritico) {
      return Classificacao.CRITICO;
    } else if (valor >= limiteAlerta) {
      return Classificacao.ALERTA;
    } else {
      return Classificacao.NORMAL;
    }
  }

}
