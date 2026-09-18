package br.ucsal.padroes.decorator;

import java.math.BigDecimal;

// Acrescenta juros de 2,99% por parcela sobre o valor do pagamento envolvido.
public class TaxaParcelamento extends PagamentoDecorator {

    private static final BigDecimal JUROS_POR_PARCELA = new BigDecimal("0.0299");

    private final int parcelas;

    public TaxaParcelamento(Pagamento envolvido, int parcelas) {
        super(envolvido);
        this.parcelas = parcelas;
    }

    @Override
    public BigDecimal calcular() {
        BigDecimal base = envolvido.calcular();
        BigDecimal juros = JUROS_POR_PARCELA.multiply(BigDecimal.valueOf(parcelas));
        return base.add(base.multiply(juros));
    }

    @Override
    public String descricao() {
        return envolvido.descricao() + " + parcelamento em " + parcelas + "x";
    }
}
