package br.com.attus.dtos;

import br.com.attus.entities.Endereco;
import br.com.attus.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    List<EnderecoDTO> enderecos = new ArrayList<>();

    public PessoaDTO(Pessoa p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.dataNascimento = p.getDataNascimento();
        for (Endereco e : p.getEnderecos()) {
            enderecos.add(new EnderecoDTO(e));
        }
    }
}
