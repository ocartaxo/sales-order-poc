package br.com.poc.ocartaxo.salesorder.controller;

import br.com.poc.ocartaxo.salesorder.dto.CadastroClienteRequest;
import br.com.poc.ocartaxo.salesorder.dto.ClienteResponse;
import br.com.poc.ocartaxo.salesorder.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/api/cliente")
@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService service;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastra(
            @RequestBody CadastroClienteRequest request,
            UriComponentsBuilder builder
    ){
        final var response = service.cadastraNovoCliente(request);
        final var uri = builder.path("/api/cliente/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
