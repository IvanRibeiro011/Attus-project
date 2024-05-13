package br.com.attus.integration;

import br.com.attus.dtos.EnderecoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class EnderecoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateEndereco() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua A");
        enderecoDTO.setCep("12345-678");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCidade("Cidade");
        enderecoDTO.setEstado("Estado");
        enderecoDTO.setPrincipal(true);

        mockMvc.perform(post("/api/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.logradouro").value("Rua A"));
    }

    @Test
    public void testFindAllEnderecos() throws Exception {
        mockMvc.perform(get("/api/enderecos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindEnderecoById() throws Exception {
        mockMvc.perform(get("/api/enderecos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.logradouro").exists())
                .andExpect(jsonPath("$.cep").exists())
                .andExpect(jsonPath("$.numero").exists())
                .andExpect(jsonPath("$.cidade").exists())
                .andExpect(jsonPath("$.estado").exists());
    }

    @Test
    public void testUpdateEndereco() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua B");
        enderecoDTO.setCep("54321-876");
        enderecoDTO.setNumero("456");
        enderecoDTO.setCidade("Cidade B");
        enderecoDTO.setEstado("Estado B");
        enderecoDTO.setPrincipal(false);

        mockMvc.perform(put("/api/enderecos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logradouro").value("Rua B"));
    }

    @Test
    public void testDeleteEndereco() throws Exception {
        mockMvc.perform(delete("/api/enderecos/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
