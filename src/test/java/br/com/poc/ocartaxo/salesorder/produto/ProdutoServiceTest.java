package br.com.poc.ocartaxo.salesorder.produto;

import br.com.poc.ocartaxo.salesorder.infra.exception.CadastroProdutoException;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import br.com.poc.ocartaxo.salesorder.repository.ProdutosRepository;
import br.com.poc.ocartaxo.salesorder.service.ProdutosService;
import br.com.poc.ocartaxo.salesorder.service.impl.ProdutosServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProdutoServiceTest {

    private ProdutosService service;
    private final ProdutosRepository repository = mock(ProdutosRepository.class);

    @BeforeEach
    public void setup() {

        service = new ProdutosServiceImpl(repository, ProdutoFixture.mapper(), ProdutoFixture.validadorProduto());

        when(repository.save(any(Produto.class))).thenReturn(ProdutoFixture.produto());
    }

    @Test
    @DisplayName("Deve cadastrar com sucesso um produto quando o produto for válido.")
    public void testaCadastroProdutoValido() {

        final var novoProduto = ProdutoFixture.cadastroProdutoValido();

        Assertions.assertDoesNotThrow(() -> service.cadastrarNovoProduto(novoProduto));

    }

    @Test
    @DisplayName("Deve lançar exceção CadastroProdutoException quando a descrição do produto for vazia.")
    public void testaCadastroProdutoDescricaoInvalida() {

        final var novoProduto = ProdutoFixture.cadastroProdutoDescricaoInvalida();

        Assertions.assertThrows(CadastroProdutoException.class, () -> service.cadastrarNovoProduto(novoProduto));

    }

    @Test
    @DisplayName("Deve lançar exceção CadastroProdutoException quando o preco do produto for igual a zero")
    public void testaCadastroProdutoValorIgualAZero() {

        final var novoProduto = ProdutoFixture.cadastroProdutoPrecoInvalidoIgualAZero();

        Assertions.assertThrows(CadastroProdutoException.class, () -> service.cadastrarNovoProduto(novoProduto));

    }

    @Test
    @DisplayName("Deve lançar exceção CadastroProdutoException quando o preco do produto for menor que zero")
    public void testaCadastroProdutoValorInvalido() {

        final var novoProduto = ProdutoFixture.cadastroProdutoPrecoInvalidoMenorQueZero();

        Assertions.assertThrows(CadastroProdutoException.class, () -> service.cadastrarNovoProduto(novoProduto));

    }

    @Test
    @DisplayName("Deve lançar exceção CadastroProdutoException quando quantidade em estoque do produto for igual a zero")
    public void testaCadastroProdutoQuantidadeIgualAZero() {

        final var novoProduto = ProdutoFixture.cadastroProdutoQuantidadeEstoqueIgualAZero();

        Assertions.assertThrows(CadastroProdutoException.class, () -> service.cadastrarNovoProduto(novoProduto));

    }

    @Test
    @DisplayName("Deve lançar exceção CadastroProdutoException quantidade de estoque do produto for menor que zero")
    public void testaCadastroProdutoQuantidadeInvalida() {

        final var novoProduto = ProdutoFixture.cadastroProdutoPrecoInvalidoMenorQueZero();

        Assertions.assertThrows(CadastroProdutoException.class, () -> service.cadastrarNovoProduto(novoProduto));

    }

}
