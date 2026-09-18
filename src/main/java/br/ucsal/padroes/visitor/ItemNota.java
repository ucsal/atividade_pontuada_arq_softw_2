package br.ucsal.padroes.visitor;

import java.math.BigDecimal;

// O metodo aceitar faz o duplo despacho: cada item concreto sabe qual
// sobrecarga de visitar deve ser chamada.
public interface ItemNota {

    <R> R aceitar(VisitanteNota<R> visitante);

    String descricao();

    BigDecimal valor();
}
