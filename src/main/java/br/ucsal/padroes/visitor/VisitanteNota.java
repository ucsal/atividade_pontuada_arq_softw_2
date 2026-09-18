package br.ucsal.padroes.visitor;

// Cada operacao nova sobre a nota vira uma implementacao desta interface,
// sem alterar as classes de item.
public interface VisitanteNota<R> {

    R visitar(ProdutoFisico item);

    R visitar(ServicoContratado item);
}
