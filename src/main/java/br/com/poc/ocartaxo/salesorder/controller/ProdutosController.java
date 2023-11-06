package br.com.poc.ocartaxo.salesorder.controller;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.service.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(
            @RequestBody ProdutoCadastroRequest body,
            UriComponentsBuilder builder
    ){
        final var response = service.cadastrarNovoProduto(body);
        final var uri = builder.path("/api/produto/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
