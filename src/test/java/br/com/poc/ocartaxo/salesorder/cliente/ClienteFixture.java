package br.com.poc.ocartaxo.salesorder.cliente;

import br.com.poc.ocartaxo.salesorder.dto.CadastroClienteRequest;
import br.com.poc.ocartaxo.salesorder.dto.DocumentoRequest;
import br.com.poc.ocartaxo.salesorder.dto.EnderecoRequest;
import br.com.poc.ocartaxo.salesorder.enums.TipoDocumento;
import br.com.poc.ocartaxo.salesorder.enums.TipoEndereco;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.mapper.impl.ClienteMapperImpl;
import br.com.poc.ocartaxo.salesorder.mapper.impl.DocumentoMapper;
import br.com.poc.ocartaxo.salesorder.mapper.impl.EnderecoMapper;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.model.Documento;
import br.com.poc.ocartaxo.salesorder.model.Endereco;
import br.com.poc.ocartaxo.salesorder.validacao.ValidadorCliente;
import br.com.poc.ocartaxo.salesorder.validacao.impl.ValidadorCNPJ;
import br.com.poc.ocartaxo.salesorder.validacao.impl.ValidadorCPF;
import br.com.poc.ocartaxo.salesorder.validacao.impl.ValidadorClienteImpl;
import br.com.poc.ocartaxo.salesorder.validacao.impl.ValidadorEmail;

import java.util.List;
import java.util.Map;

public final class ClienteFixture {
    public static Cliente cliente() {
        final var c = new Cliente();
        c.setId(0L);
        c.setNome("Otávio Augusto");
        c.setEmail("otavio.augusto@email.com");
        c.setDocumento(new Documento("123.456.789-09", TipoDocumento.CPF));
        c.setEnderecos(List.of(new Endereco(0L, "Rua das Flores, 123, Jardim Primavera", TipoEndereco.ALL)));

        return c;
    }

    public static CadastroClienteRequest clienteValidoRequest() {
        return new CadastroClienteRequest(
                "José da Silva",
                "jose.silva@email.com",
                new DocumentoRequest("393.044.950-16", TipoDocumento.CPF),
                List.of(new EnderecoRequest("Rua das Flores, 123, Jardim Primavera", TipoEndereco.ALL))
        );
    }

    public static CadastroClienteRequest clienteRequestCpfInvalido() {
        return new CadastroClienteRequest(
                "José da Silva",
                "jose.silva@email.com",
                new DocumentoRequest("393.044.950", TipoDocumento.CPF),
                List.of(new EnderecoRequest("Rua das Flores, 123, Jardim Primavera", TipoEndereco.ALL))
        );
    }

    public static CadastroClienteRequest clienteRequestCnpjInvalido() {
        return new CadastroClienteRequest(
                "José da Silva",
                "jose.silva@email.com",
                new DocumentoRequest("73.605.1700001-73", TipoDocumento.CNPJ),
                List.of(new EnderecoRequest("Rua das Flores, 123, Jardim Primavera", TipoEndereco.ALL))
        );
    }

    public static CadastroClienteRequest clienteRequestEmailInvalido() {
        return new CadastroClienteRequest(
                "",
                "jose.silvaemail.com",
                new DocumentoRequest("393.044.950-16", TipoDocumento.CPF),
                List.of(new EnderecoRequest("Rua das Flores, 123, Jardim Primavera", TipoEndereco.ALL))
        );
    }

    public static CadastroClienteRequest clienteRequestNomeInvalido() {
        return new CadastroClienteRequest(
                "",
                "jose.silva@email.com",
                new DocumentoRequest("393.044.950-16", TipoDocumento.CPF),
                List.of(new EnderecoRequest("Rua das Flores, 123, Jardim Primavera", TipoEndereco.ALL))
        );
    }

    public static ValidadorCliente validadorCliente() {
        return new ValidadorClienteImpl(
                Map.of(TipoDocumento.CNPJ, new ValidadorCNPJ(),
                       TipoDocumento.CPF, new ValidadorCPF()
                ),
                new ValidadorEmail()
        );
    }

    public static ClienteMapper mapper() {
        return new ClienteMapperImpl(new EnderecoMapper(), new DocumentoMapper());
    }

}
