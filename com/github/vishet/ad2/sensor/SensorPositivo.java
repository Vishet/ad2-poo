package com.github.vishet.ad2.sensor;

import com.github.vishet.ad2.Leitura;
import com.github.vishet.ad2.excecao.IdInvalidoException;

public abstract class SensorPositivo extends Sensor {
  public SensorPositivo(String id, String descricaoLocal) {
    super(id, descricaoLocal);
  }

  @Override
  public void addLeitura(Leitura leitura) {
    if (leitura.getValor() < 0) {
      throw new IdInvalidoException("Valor da leitura não pode ser negativo.");
    }
    super.addLeitura(leitura);
  }
}
