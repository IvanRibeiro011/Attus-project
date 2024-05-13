package br.com.attus.dtos;

import br.com.attus.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO {

    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;

    private boolean principal;

    private Long pessoaId;

    public EnderecoDTO(Endereco e) {
        this.logradouro = e.getLogradouro();
        this.cep = e.getCep();
        this.numero = e.getNumero();
        this.cidade = e.getCidade();
        this.estado = e.getEstado();
        this.principal = e.isPrincipal();
        this.pessoaId = e.getPessoa().getId();
    }
}
