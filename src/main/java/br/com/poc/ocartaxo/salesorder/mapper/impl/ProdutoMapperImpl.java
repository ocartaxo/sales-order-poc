package br.com.poc.ocartaxo.salesorder.mapper.impl;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResumidoResponse;
import br.com.poc.ocartaxo.salesorder.mapper.ProdutoMapper;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {
    @Override
    public Produto converteParaEntidade(ProdutoCadastroRequest body) {
        final var produto = new Produto();

        produto.setValor(body.valor());
        produto.setDescricao(body.descricao());
        produto.setCategoria(body.categoria());
        produto.setQuantidadeEstoque(body.quantidadeEstoque());


        return produto;
    }

    @Override
    public ProdutoResponse converteParaDto(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getValor(),
                produto.getQuantidadeEstoque()
        );
    }

    @Override
    public ProdutoResumidoResponse converteParaDtoResumido(Produto produto) {
        return new ProdutoResumidoResponse(produto.getDescricao(), produto.getCategoria(), produto.getValor());
    }
}
