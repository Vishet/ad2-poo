package com.github.vishet.ad2.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.github.vishet.ad2.Leitura;
import com.github.vishet.ad2.excecao.SemLeiturasException;

public abstract class Sensor implements Serializable {
  private String id;
  private String descricaoLocal;

  private List<Leitura> leituras = new ArrayList<>();

  public static enum Classificacao {
    NORMAL, ALERTA, CRITICO
  }

  public Sensor(String id, String descricaoLocal) {
    this.id = id;
    this.descricaoLocal = descricaoLocal;
  }

  public abstract Classificacao getClassificacao(Leitura leitura);

  public String getId() {
    return id;
  }

  public String getDescricaoLocal() {
    return descricaoLocal;
  }

  public void addLeitura(Leitura leitura) {
    leituras.add(leitura);
  }

  public boolean hasLeituras() {
    return !leituras.isEmpty();
  }

  public Leitura getLeituraMaisRecente() {
    if (leituras.isEmpty()) {
      throw new SemLeiturasException("Não há leituras disponíveis.");
    } else {
      return leituras.get(leituras.size() - 1);
    }
  }

  public void exibirLeituraMaisRecente() {
    if (leituras.isEmpty()) {
      throw new SemLeiturasException("Não há leituras disponíveis.");
    } else {
      Leitura leituraMaisRecente = getLeituraMaisRecente();
      Classificacao classificacao = getClassificacao(leituraMaisRecente);
      System.out.println("Leitura mais recente do sensor " + id + ":");
      leituraMaisRecente.exibir(classificacao);
    }
  }

  public double getMediaLeituras() {
    if (leituras.isEmpty()) {
      throw new SemLeiturasException("Não há leituras disponíveis.");
    } else {
      double soma = 0.0;
      for (Leitura leitura : leituras) {
        soma += leitura.getValor();
      }
      return soma / leituras.size();
    }
  }

  public void exibirLeituras() {
    if (leituras.isEmpty()) {
      System.out.println("O sensor " + id + " não possui leituras.");
    } else {
      System.out.println("Leituras do sensor " + id + ":");
      for (Leitura leitura : leituras) {
        Classificacao classificacao = getClassificacao(leitura);
        leitura.exibir(classificacao);
      }
    }
  }
}
