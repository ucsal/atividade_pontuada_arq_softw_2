package br.ucsal.padroes.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Percorre apenas os produtos de uma categoria; o filtro fica dentro do iterador.
public class IteradorPorCategoria implements Iterator<Produto> {

    private final List<Produto> produtos;
    private final String categoria;
    private int posicao = 0;

    public IteradorPorCategoria(List<Produto> produtos, String categoria) {
        this.produtos = produtos;
        this.categoria = categoria;
    }

    @Override
    public boolean hasNext() {
        while (posicao < produtos.size()
                && !produtos.get(posicao).categoria().equalsIgnoreCase(categoria)) {
            posicao++;
        }
        return posicao < produtos.size();
    }

    @Override
    public Produto next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return produtos.get(posicao++);
    }
}
