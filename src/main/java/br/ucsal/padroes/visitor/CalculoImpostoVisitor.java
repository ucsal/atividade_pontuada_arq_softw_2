package br.ucsal.padroes.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

// A regra fiscal de cada tipo de item vive aqui, e nao dentro das classes de item.
public class CalculoImpostoVisitor implements VisitanteNota<BigDecimal> {

    private static final BigDecimal ALIQUOTA_ICMS = new BigDecimal("0.18");
    private static final BigDecimal ALIQUOTA_ISS = new BigDecimal("0.05");

    @Override
    public BigDecimal visitar(ProdutoFisico item) {
        return item.valor().multiply(ALIQUOTA_ICMS).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal visitar(ServicoContratado item) {
        return item.valor().multiply(ALIQUOTA_ISS).setScale(2, RoundingMode.HALF_UP);
    }
}
