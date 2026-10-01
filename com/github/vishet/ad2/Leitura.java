package com.github.vishet.ad2;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.github.vishet.ad2.sensor.Sensor.Classificacao;

public class Leitura implements Serializable {
  private int valor;
  private LocalDateTime data;

  public Leitura(int valor, LocalDateTime data) {
    this.data = data;
    this.valor = valor;
  }

  public void exibir() {
    System.out.println("Valor: " + valor + ", Data: " + data);
  }

  public void exibir(Classificacao classificacao) {
    System.out.println("Valor: " + valor + ", Data: " + data + ", Classificação: " + classificacao);
  }

  public int getValor() {
    return valor;
  }

  public LocalDateTime getData() {
    return data;
  }
}
