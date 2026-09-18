package br.ucsal.padroes.decorator;

import java.math.BigDecimal;

// Interface comum: pagamentos e decoradores a implementam, por isso podem ser encadeados.
public interface Pagamento {

    BigDecimal calcular();

    String descricao();
}
