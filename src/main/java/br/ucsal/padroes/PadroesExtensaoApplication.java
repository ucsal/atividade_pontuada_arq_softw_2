package br.ucsal.padroes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ucsal.padroes.decorator.Cashback;
import br.ucsal.padroes.decorator.Pagamento;
import br.ucsal.padroes.decorator.PagamentoPix;
import br.ucsal.padroes.decorator.TaxaParcelamento;
import br.ucsal.padroes.iterator.Catalogo;
import br.ucsal.padroes.iterator.Produto;
import br.ucsal.padroes.visitor.CalculoImpostoVisitor;
import br.ucsal.padroes.visitor.ItemNota;
import br.ucsal.padroes.visitor.ProdutoFisico;
import br.ucsal.padroes.visitor.ServicoContratado;

@SpringBootApplication
public class PadroesExtensaoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PadroesExtensaoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        demonstrarDecorator();
        demonstrarIterator();
        demonstrarVisitor();
    }

    private void demonstrarDecorator() {
        System.out.println();
        System.out.println("=== Decorator: calculo de pagamento ===");
        Pagamento pagamento = new Cashback(
                new TaxaParcelamento(
                        new PagamentoPix(new BigDecimal("100.00")), 3));
        System.out.println(pagamento.descricao());
        System.out.println("Valor final: R$ " + pagamento.calcular().setScale(2, RoundingMode.HALF_UP));
    }

    private void demonstrarIterator() {
        System.out.println();
        System.out.println("=== Iterator: catalogo de produtos ===");
        Catalogo catalogo = new Catalogo();
        catalogo.adicionar(new Produto("Mouse sem fio", "eletronicos", new BigDecimal("89.90")));
        catalogo.adicionar(new Produto("Clean Code", "livros", new BigDecimal("129.90")));
        catalogo.adicionar(new Produto("Teclado mecanico", "eletronicos", new BigDecimal("349.00")));
        catalogo.adicionar(new Produto("Padroes de Projeto", "livros", new BigDecimal("184.50")));
        System.out.println("Produtos da categoria livros:");
        for (Produto produto : catalogo.porCategoria("livros")) {
            System.out.println("- " + produto.nome() + " (R$ " + produto.preco() + ")");
        }
    }

    private void demonstrarVisitor() {
        System.out.println();
        System.out.println("=== Visitor: impostos da nota fiscal ===");
        List<ItemNota> itens = List.of(
                new ProdutoFisico("Monitor 24 polegadas", new BigDecimal("899.00"), 3.2),
                new ServicoContratado("Instalacao e configuracao", new BigDecimal("300.00"), 4));
        CalculoImpostoVisitor imposto = new CalculoImpostoVisitor();
        for (ItemNota item : itens) {
            System.out.println(item.descricao() + " -> imposto: R$ " + item.aceitar(imposto));
        }
    }
}
