package br.com.poc.ocartaxo.salesorder.controller;

import br.com.poc.ocartaxo.salesorder.dto.ProdutoAtualizacaoRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoCadastroRequest;
import br.com.poc.ocartaxo.salesorder.dto.ProdutoResponse;
import br.com.poc.ocartaxo.salesorder.model.Produto;
import br.com.poc.ocartaxo.salesorder.service.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> exibir(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarProduto(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> listar(@PageableDefault(size = 5)Pageable pageable){
        return ResponseEntity.ok(service.listarTodosProdutos(pageable));
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<ProdutoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody ProdutoAtualizacaoRequest body
    ){
        return ResponseEntity.ok(service.atualizarProduto(id, body));
    }
}
