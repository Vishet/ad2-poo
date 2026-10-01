package com.github.vishet.ad2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.vishet.ad2.excecao.IdConflitoException;
import com.github.vishet.ad2.excecao.IdInvalidoException;
import com.github.vishet.ad2.sensor.Sensor;

public class SistemaMonitoramento implements Serializable {
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

  public List<Sensor> getSensores() {
    return new ArrayList<>(sensores.values());
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

  public void salvar() {
    try (FileOutputStream fileOut = new FileOutputStream("sistema_monitoramento.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(this);
    } catch (IOException e) {
      throw new RuntimeException("Erro ao salvar o sistema de monitoramento: " + e.getMessage());
    }
  }

  public static SistemaMonitoramento carregar(String filePath) {
    try (FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream in = new ObjectInputStream(fileIn)) {
      return (SistemaMonitoramento) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException("Erro ao carregar o sistema de monitoramento: " + e.getMessage());
    }
  }
}
