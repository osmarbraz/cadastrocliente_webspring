package com.controle;

import com.formulario.ClienteFrm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestClienteControle {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testMenu() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Menu")));
    }
    
    @Test
    void testFrmClienteIncluir() throws Exception {
        this.mockMvc.perform(get("/FrmClienteIncluir")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Incluir")));
    }
    
    @Test
    void testFrmClienteAlterar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteAlterar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Alterar")));
    }
    
    @Test
    void testFrmClienteExcluir() throws Exception {
        this.mockMvc.perform(get("/FrmClienteExcluir")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Excluir")));
    }
    
    @Test
    void testFrmClienteConsultar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteConsultar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Consultar")));
    }
    
    @Test
    void testFrmClienteListar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteListar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Listar")));
    }
}
