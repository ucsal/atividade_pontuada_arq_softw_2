package br.ucsal.padroes.visitor;

import java.math.BigDecimal;

public record ServicoContratado(String descricao, BigDecimal valor, int cargaHoraria) implements ItemNota {

    @Override
    public <R> R aceitar(VisitanteNota<R> visitante) {
        return visitante.visitar(this);
    }
}
