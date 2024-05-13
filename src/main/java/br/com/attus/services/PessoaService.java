package br.com.attus.services;

import br.com.attus.dtos.PessoaDTO;
import br.com.attus.entities.Pessoa;
import br.com.attus.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o id: " + id));
        return new PessoaDTO(pessoa);
    }

    @Transactional(readOnly = true)
    public List<PessoaDTO> findAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaDTO::new).toList();
    }

    @Transactional
    public PessoaDTO save(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        return new PessoaDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaDTO updatePessoa(Long id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o id: " + id));
        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        return new PessoaDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public void delete(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o id: " + id));
        pessoaRepository.delete(pessoa);
    }

}