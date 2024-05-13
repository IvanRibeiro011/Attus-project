package br.com.attus.repositories;

import br.com.attus.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    Boolean existsByPessoaId(Long id);
}
