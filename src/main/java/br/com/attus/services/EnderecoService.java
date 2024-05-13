package br.com.attus.services;

import br.com.attus.dtos.EnderecoDTO;
import br.com.attus.entities.Endereco;
import br.com.attus.entities.Pessoa;
import br.com.attus.repositories.EnderecoRepository;
import br.com.attus.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository repository;
    private final PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        Endereco endereco = repository.findById(id).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findAll() {
        return repository.findAll().stream().map(EnderecoDTO::new).toList();
    }

    @Transactional
    public EnderecoDTO save(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setCep(dto.getCep());
        endereco.setNumero(dto.getNumero());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setPrincipal(dto.isPrincipal());
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        for (Endereco e : pessoa.getEnderecos()) {
            if (endereco.isPrincipal() && e.isPrincipal()) {
                endereco.setPrincipal(false);
            }
        }
        endereco.setPessoa(pessoa);

        return new EnderecoDTO(repository.save(endereco));
    }

    @Transactional
    public void associateEnderecoToPessoas(Long id, List<Long> pessoaIds) {
        Endereco endereco = repository.findById(id).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        for (Long i : pessoaIds) {
            Pessoa p = pessoaRepository.findById(i).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
            p.getEnderecos().add(endereco);
            pessoaRepository.save(p);
        }
    }

    @Transactional
    public EnderecoDTO updateEndereco(Long id, EnderecoDTO dto) {
        Endereco endereco = repository.findById(id).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setCep(dto.getCep());
        endereco.setNumero(dto.getNumero());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setPrincipal(dto.isPrincipal());
        return new EnderecoDTO(repository.save(endereco));
    }

    @Transactional
    public void delete(Long id) {
        Endereco endereco = repository.findById(id).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        repository.delete(endereco);
    }

}
