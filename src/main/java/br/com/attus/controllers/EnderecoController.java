package br.com.attus.controllers;

import br.com.attus.dtos.EnderecoDTO;
import br.com.attus.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        EnderecoDTO enderecoDTO = enderecoService.findById(id);
        return ResponseEntity.ok(enderecoDTO);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        List<EnderecoDTO> enderecosDTO = enderecoService.findAll();
        return ResponseEntity.ok(enderecosDTO);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> save(@RequestBody EnderecoDTO dto) {
        EnderecoDTO enderecoDTO = enderecoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO dto) {
        EnderecoDTO enderecoDTO = enderecoService.updateEndereco(id, dto);
        return ResponseEntity.ok(enderecoDTO);
    }

    @PutMapping("/{id}/associar-pessoas")
    public ResponseEntity<Void> associateEnderecoToPessoas(@PathVariable Long id, @RequestBody List<Long> pessoaIds) {
        enderecoService.associateEnderecoToPessoas(id, pessoaIds);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
