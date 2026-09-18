package br.ucsal.padroes.iterator;

import java.util.ArrayList;
import java.util.List;

// A lista de produtos e privada: quem consome o catalogo recebe um iterador,
// nunca a estrutura interna.
public class Catalogo {

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public Iterable<Produto> porCategoria(String categoria) {
        return () -> new IteradorPorCategoria(produtos, categoria);
    }
}
