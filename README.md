# Padrões de Projeto — Categoria Extensão

Trabalho da disciplina demonstrando os três padrões da categoria **Extensão**
(Decorator, Iterator e Visitor), cada um aplicado a um problema do mundo real,
em um projeto Java com Spring Boot. Ao rodar a aplicação, as três
demonstrações são impressas no console.

## Como rodar

Requisitos: JDK 21 ou superior (o Maven não precisa estar instalado — o
projeto inclui o Maven Wrapper).

```bash
./mvnw spring-boot:run
```

## 1. Decorator — cálculo de pagamento

O Decorator permite acrescentar responsabilidades a um objeto em tempo de
execução, envolvendo-o em outros objetos que implementam a mesma interface.
Sem ele, cada combinação de encargos de um pagamento (juros, cashback etc.)
exigiria uma subclasse própria. Aqui, `PagamentoPix` é o pagamento base e
`TaxaParcelamento` e `Cashback` são decoradores que podem ser empilhados em
qualquer ordem.

```java
public abstract class PagamentoDecorator implements Pagamento {

    protected final Pagamento envolvido;

    protected PagamentoDecorator(Pagamento envolvido) {
        this.envolvido = envolvido;
    }
}

// Uso: cada decorador envolve o anterior
Pagamento pagamento = new Cashback(
        new TaxaParcelamento(
                new PagamentoPix(new BigDecimal("100.00")), 3));
```

Saída no console:

```
=== Decorator: calculo de pagamento ===
Pagamento via Pix + parcelamento em 3x + cashback de 3%
Valor final: R$ 105.70
```

## 2. Iterator — catálogo de produtos

O Iterator dá acesso sequencial aos elementos de uma coleção sem expor como
ela é guardada por dentro. O `Catalogo` mantém sua lista privada e entrega um
iterador que percorre apenas os produtos de uma categoria — quem consome usa
um `for` comum, sem saber que existe um filtro.

```java
@Override
public boolean hasNext() {
    while (posicao < produtos.size()
            && !produtos.get(posicao).categoria().equalsIgnoreCase(categoria)) {
        posicao++;
    }
    return posicao < produtos.size();
}
```

Saída no console:

```
=== Iterator: catalogo de produtos ===
Produtos da categoria livros:
- Clean Code (R$ 129.90)
- Padroes de Projeto (R$ 184.50)
```

## 3. Visitor — impostos da nota fiscal

O Visitor separa as operações da estrutura de objetos sobre a qual elas
atuam: cada operação nova vira uma classe visitante, sem alterar as classes
visitadas. Os itens da nota (`ProdutoFisico`, `ServicoContratado`) são tipos
estáveis, e o `CalculoImpostoVisitor` aplica a regra de cada um (ICMS de 18%
no produto físico, ISS de 5% no serviço). O método `aceitar` faz o duplo
despacho. O padrão só vale a pena quando os tipos são estáveis e as operações
mudam com frequência — se novos tipos surgem toda hora, ele atrapalha.

```java
public record ProdutoFisico(String descricao, BigDecimal valor, double pesoKg) implements ItemNota {

    @Override
    public <R> R aceitar(VisitanteNota<R> visitante) {
        return visitante.visitar(this);
    }
}
```

Saída no console:

```
=== Visitor: impostos da nota fiscal ===
Monitor 24 polegadas -> imposto: R$ 161.82
Instalacao e configuracao -> imposto: R$ 15.00
```
