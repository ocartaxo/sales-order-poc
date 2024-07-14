package br.com.poc.ocartaxo.salesorder.mapper;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResumidoResponse;
import br.com.poc.ocartaxo.salesorder.model.Produto;

public interface ProdutoMapper {

    Produto converteParaEntidade(ProdutoCadastroRequest body);
    ProdutoResponse converteParaDto(Produto produto);

    ProdutoResumidoResponse converteParaDtoResumido(Produto produto);
}
