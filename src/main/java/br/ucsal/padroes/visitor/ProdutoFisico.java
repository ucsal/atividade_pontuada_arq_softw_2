package br.ucsal.padroes.visitor;

import java.math.BigDecimal;

public record ProdutoFisico(String descricao, BigDecimal valor, double pesoKg) implements ItemNota {

    @Override
    public <R> R aceitar(VisitanteNota<R> visitante) {
        return visitante.visitar(this);
    }
}
