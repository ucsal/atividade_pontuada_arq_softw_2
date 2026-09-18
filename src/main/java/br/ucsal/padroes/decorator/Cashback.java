package br.ucsal.padroes.decorator;

import java.math.BigDecimal;

// Abate 3% do valor como cashback do programa de fidelidade.
public class Cashback extends PagamentoDecorator {

    private static final BigDecimal PERCENTUAL = new BigDecimal("0.03");

    public Cashback(Pagamento envolvido) {
        super(envolvido);
    }

    @Override
    public BigDecimal calcular() {
        BigDecimal base = envolvido.calcular();
        return base.subtract(base.multiply(PERCENTUAL));
    }

    @Override
    public String descricao() {
        return envolvido.descricao() + " + cashback de 3%";
    }
}
