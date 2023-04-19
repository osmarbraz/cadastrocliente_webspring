package com.controle;

import com.servico.ClienteServico;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestClienteControle {

    @Autowired
    private MockMvc mockMvc;
   
    /**
     * Testa o carregamento do menu.
     *
     * @throws Exception
     */
    @Test
    void testMenu() throws Exception {
        // Verifica sem na requisão "/" existe a string "Menu".
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Menu")));
    }

    /**
     * Testa o carregamento do formulário de incluir cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteIncluir() throws Exception {
        this.mockMvc.perform(get("/FrmClienteIncluir")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Incluir")));
    }

    /**
     * Testa o carregamento do formulário de alterar cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteAlterar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteAlterar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Alterar")));
    }

    /**
     * Testa o carregamento do formulário de excluir cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteExcluir() throws Exception {
        this.mockMvc.perform(get("/FrmClienteExcluir")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Excluir")));
    }

    /**
     * Testa o carregamento do formulário de consulta cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteConsultar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteConsultar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Consultar")));
    }

    /**
     * Testa o carregamento do formulário de listar clientes.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteListar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteListar")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Listar")));
    }
}
