package br.com.attus.controllers;

import br.com.attus.dtos.PessoaDTO;
import br.com.attus.services.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.findById(id);
        return ResponseEntity.ok(pessoaDTO);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<PessoaDTO> pessoasDTO = pessoaService.findAll();
        return ResponseEntity.ok(pessoasDTO);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO dto) {
        PessoaDTO pessoaDTO = pessoaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        PessoaDTO pessoaDTO = pessoaService.updatePessoa(id, dto);
        return ResponseEntity.ok(pessoaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
