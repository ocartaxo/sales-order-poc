package br.com.poc.ocartaxo.salesorder.service;

import br.com.poc.ocartaxo.salesorder.fixture.ClienteFixture;
import br.com.poc.ocartaxo.salesorder.infra.exception.DocumentoInvalidoException;
import br.com.poc.ocartaxo.salesorder.infra.exception.InformacoesClienteInvalidaException;
import br.com.poc.ocartaxo.salesorder.mapper.ClienteMapper;
import br.com.poc.ocartaxo.salesorder.model.Cliente;
import br.com.poc.ocartaxo.salesorder.repository.ClientesRepository;
import br.com.poc.ocartaxo.salesorder.service.impl.ClientesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {


    private final ClientesRepository mockRepository = mock(ClientesRepository.class);
    private final ClienteMapper mockMapper = mock(ClienteMapper.class);

    private ClientesService service;

    @BeforeEach
    public void setup() {
        service = new ClientesServiceImpl(mockMapper, mockRepository, ClienteFixture.validadorCliente());

        when(mockRepository.save(any(Cliente.class))).thenReturn(ClienteFixture.cliente());
    }

    @Test
    @DisplayName("Deve cadastrar com sucesso um cliente quando cliente for válido")
    public void testeCadastroClienteValido() {
        final var novoCliente = ClienteFixture.clienteValidoRequest();

        Assertions.assertDoesNotThrow(() -> service.cadastrarNovoCliente(novoCliente));
    }

    @Test
    @DisplayName("Deve lançar a exceção DocumentoInvalidoException quando o cliente cadastrado possuir cpf inválido")
    public void testeCadastroClienteCpfInvalido() {
        final var novoCliente = ClienteFixture.clienteRequestCpfInvalido();

        Assertions.assertThrows(DocumentoInvalidoException.class, () -> service.cadastrarNovoCliente(novoCliente));
    }


    @Test
    @DisplayName("Deve lançar a exceção DocumentoInvalidoException quando o cliente cadastrado possuir cnpj inválido")
    public void testeCadastroClienteCnpjInvalido() {
        final var novoCliente = ClienteFixture.clienteRequestCnpjInvalido();

        Assertions.assertThrows(DocumentoInvalidoException.class, () -> service.cadastrarNovoCliente(novoCliente));
    }

    @Test
    @DisplayName("Deve lançar a exceção InformacoesClienteInvalidaException quando o cliente cadastrado possuir email inválido")
    public void testeCadastroClienteEmailInvalido() {
        final var novoCliente = ClienteFixture.clienteRequestEmailInvalido();

        Assertions.assertThrows(InformacoesClienteInvalidaException.class, () -> service.cadastrarNovoCliente(novoCliente));
    }

    @Test
    @DisplayName("Deve lançar a exceção InformacoesClienteInvalidaException quando o cliente cadastrado possuir nome inválido")
    public void testeCadastroClienteNomeInvalido() {
        final var novoCliente = ClienteFixture.clienteRequestNomeInvalido();

        Assertions.assertThrows(InformacoesClienteInvalidaException.class, () -> service.cadastrarNovoCliente(novoCliente));
    }
}
