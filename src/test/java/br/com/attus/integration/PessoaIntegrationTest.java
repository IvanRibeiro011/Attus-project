package br.com.attus.integration;

import br.com.attus.dtos.PessoaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class PessoaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePessoa() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Fulano");
        pessoaDTO.setDataNascimento(LocalDate.parse("1990-01-01"));

        mockMvc.perform(post("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Fulano"));
    }

    @Test
    public void testFindAllPessoas() throws Exception {
        mockMvc.perform(get("/api/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindPessoaById() throws Exception {
        mockMvc.perform(get("/api/pessoas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").exists())
                .andExpect(jsonPath("$.dataNascimento").exists());
    }

    @Test
    public void testUpdatePessoa() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Beltrano");
        pessoaDTO.setDataNascimento(LocalDate.parse("1980-01-01"));

        mockMvc.perform(put("/api/pessoas/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Beltrano"));
    }

    @Test
    public void testDeletePessoa() throws Exception {
        mockMvc.perform(delete("/api/pessoas/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
