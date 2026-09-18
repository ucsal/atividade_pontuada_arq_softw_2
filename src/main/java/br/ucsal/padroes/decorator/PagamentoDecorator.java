package br.ucsal.padroes.decorator;

// Decorador abstrato: guarda o pagamento envolvido e deixa as subclasses
// acrescentarem comportamento sem criar uma subclasse por combinacao.
public abstract class PagamentoDecorator implements Pagamento {

    protected final Pagamento envolvido;

    protected PagamentoDecorator(Pagamento envolvido) {
        this.envolvido = envolvido;
    }
}
