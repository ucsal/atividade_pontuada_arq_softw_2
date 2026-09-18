package br.ucsal.padroes.decorator;

import java.math.BigDecimal;

// Pagamento base, sem nenhum encargo.
public class PagamentoPix implements Pagamento {

    private final BigDecimal valor;

    public PagamentoPix(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public BigDecimal calcular() {
        return valor;
    }

    @Override
    public String descricao() {
        return "Pagamento via Pix";
    }
}
