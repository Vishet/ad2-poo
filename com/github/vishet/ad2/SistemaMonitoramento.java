package com.github.vishet.ad2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.vishet.ad2.excecao.IdConflitoException;
import com.github.vishet.ad2.excecao.IdInvalidoException;
import com.github.vishet.ad2.sensor.Sensor;

public class SistemaMonitoramento {
  private Map<String, Sensor> sensores = new HashMap<>();

  public SistemaMonitoramento() {
  }

  public void addSensor(Sensor sensor) {
    if (sensores.containsKey(sensor.getId())) {
      throw new IdConflitoException("Sensor com ID " + sensor.getId() + " já existe.");
    }
    sensores.put(sensor.getId(), sensor);
  }

  public Sensor getSensor(String id) {
    if (!sensores.containsKey(id)) {
      throw new IdInvalidoException("Sensor com ID " + id + " não encontrado.");
    }
    return sensores.get(id);
  }

  public List<Sensor> getSensoresAnormais() {
    List<Sensor> sensoresAnormais = new ArrayList<>();
    for (Sensor sensor : sensores.values()) {
      if (sensor.hasLeituras()) {
        Leitura leituraMaisRecente = sensor.getLeituraMaisRecente();
        if (sensor.getClassificacao(leituraMaisRecente) != Sensor.Classificacao.NORMAL) {
          sensoresAnormais.add(sensor);
        }
      }
    }
    return sensoresAnormais;
  }
}
