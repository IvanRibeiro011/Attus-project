package br.com.attus.util;

import br.com.attus.entities.Endereco;
import br.com.attus.entities.Pessoa;
import br.com.attus.repositories.EnderecoRepository;
import br.com.attus.repositories.PessoaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestInitializer {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public TestInitializer(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }


    @PostConstruct
    public void initTestData() {
        Pessoa pessoa = new Pessoa();
        if (!pessoaRepository.existsByNome("Fulano")) {
            pessoa.setNome("Fulano");
            pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));
            pessoa = pessoaRepository.save(pessoa);
            if (!enderecoRepository.existsByPessoaId(pessoa.getId())) {
                Endereco enderecoDTO = new Endereco();
                enderecoDTO.setLogradouro("Rua A");
                enderecoDTO.setCep("12345-678");
                enderecoDTO.setNumero("123");
                enderecoDTO.setCidade("Cidade");
                enderecoDTO.setEstado("Estado");
                enderecoDTO.setPessoa(pessoa);
                enderecoDTO.setPrincipal(true);
                enderecoRepository.save(enderecoDTO);
            }
        }
    }
}
