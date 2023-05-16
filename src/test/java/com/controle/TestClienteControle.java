package com.controle;

import com.dao.ClienteDAO;
import com.entidade.Cliente;
import com.formulario.ClienteFrm;
import com.servico.ClienteServico;

import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;

@SpringBootTest
@AutoConfigureMockMvc
class TestClienteControle {

    @Autowired
    private MockMvc mockMvc;
   
    @Autowired
    //@Mock
    private ClienteDAO dao;
    
    @Mock
    //@Autowired   
    private ClienteServico servico;
    
    @Mock
    private Model model;

    @InjectMocks
    private ClienteControle controle;
    
     /**
     * Verifica se o dao foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoDAO() {
        assertThat(dao).isNotNull();
    }
        
    /**
     * Verifica se o serviço foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoServico() {
        assertThat(servico).isNotNull();
    }
    
     /**
     * Verifica se o model foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoModel() {
        assertThat(model).isNotNull();
    }
    
    /**
     * Verifica se o controle foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoControle() {
        assertThat(controle).isNotNull();
    }
    
    /**
     * Testa o carregamento do menu.
     *
     * @throws Exception
     */
    @Test
    void testMenu() throws Exception {
        //Verifica sem na requisão "/" existe a string "Menu".
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("Menu")));
    }

    /**
     * Testa o carregamento do formulário de incluir cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteIncluir() throws Exception {
        this.mockMvc.perform(get("/FrmClienteIncluir"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("Incluir")));
    }
   
    /**
     * Testa a inclusão de cliente com CPF válido.
     *
     */
    @Test
    void testClienteIncluirCPFValido() {       
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("11111111111"); // CPF válido
        clienteFrm.setMensagem(null);
        //Simula o cenário de inserção de algum cliente com sucesso(true) no serviço
        when(servico.inserir(any(Cliente.class))).thenReturn(true);
        
        //Retorno do controle
        String resultado = controle.clienteIncluir(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteIncluir", resultado);
        assertEquals("Inclusão realizada com sucesso.", clienteFrm.getMensagem());
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
     /**
     * Testa a inclusão de cliente com CPF válido mas com falha.
     *
     */
    @Test
    void testClienteIncluirCPFValidoFalha() {
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("11111111111"); // CPF válido
        clienteFrm.setMensagem(null);       
        
        //Simula o cenário de inserção de algum cliente com falha(false) no serviço
        when(servico.inserir(any(Cliente.class))).thenReturn(false);

        //Retorno do controle
        String resultado = controle.clienteIncluir(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteIncluir", resultado);
        assertEquals("Inclusão não realizada.", clienteFrm.getMensagem());
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
    /**
     * Testa a inclusão de cliente com CPF inválido.
     *
     */
    @Test
    void testClienteIncluirCPFInvalido() {
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("12345678900"); // CPF inválido
        clienteFrm.setMensagem(null);
        
        //Simula o cenário de inserção de algum cliente com sucesso(true) no serviço
        when(servico.inserir(any(Cliente.class))).thenReturn(true);
        
        //Retorno do controle
        String retorno = controle.clienteIncluir(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteIncluir", retorno);
        assertEquals("CPF Inválido!", clienteFrm.getMensagem());
        //Verifica se o método nunca foi chamado
        verify(servico, never()).inserir(any(Cliente.class));
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
    /**
     * Testa o carregamento do formulário de alterar cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteAlterar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteAlterar"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("Alterar")));
    }
    
    /**
     * Testa a alteração de cliente com CPF válido.
     *
     */
    @Test
    void testClienteAlterarCPFValido() {        
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("11111111111"); // CPF válido
        clienteFrm.setMensagem(null);
        //Simula o cenário de alteração de algum cliente com sucesso(1) no serviço
        when(servico.alterar(any(Cliente.class))).thenReturn(1);

        //Retorno do controle
        String retorno = controle.clienteAlterar(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteAlterar", retorno);
        assertEquals("Alteração realizada com sucesso.", clienteFrm.getMensagem());
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
     /**
     * Testa a alteração de cliente com CPF válido e inexistente.
     *
     */
    @Test
    void testClienteAlterarCPFValidoInexistente() {
        //Cliente a ser alterado
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("11111111111"); // CPF válido
        clienteFrm.setMensagem(null);
        //Simula o cenário de alteração de algum cliente com falha(0) no serviço
        when(servico.excluir(any(Cliente.class))).thenReturn(0);

        //Retorno do controle
        String resultado = controle.clienteAlterar(clienteFrm, model);

        //Retorno do controle
        assertEquals("ClienteAlterar", resultado);
        assertEquals("Alteração não realizada.", clienteFrm.getMensagem()); 
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
    
     /**
     * Testa a alteração de cliente com CPF inválido.
     *
     */
    @Test
    void testClienteAlterarCPFInvalido() {
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("12345678900"); // CPF inválido
        clienteFrm.setMensagem(null);
        //Simula o cenário de alteração de algum cliente com sucesso(1) no serviço
        when(servico.excluir(any(Cliente.class))).thenReturn(1);

        //Retorno do controle
        String result = controle.clienteAlterar(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteAlterar", result);
        assertEquals("CPF Inválido!", clienteFrm.getMensagem());        
        verify(servico, never()).alterar(any(Cliente.class));
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
    /**
     * Testa o carregamento do formulário de excluir cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteExcluir() throws Exception {
        this.mockMvc.perform(get("/FrmClienteExcluir"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("Excluir")));
    }
   
    /**
     * Testa a exclusão de cliente existente.
     *
     */
    @Test
    void testClienteExcluirExistente() {
         //Cliente a ser excluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("12345678900"); // CPF inválido
        clienteFrm.setMensagem(null);
        //Simula o cenário de exclusão de algum cliente com sucesso(1) no serviço
        when(servico.excluir(any(Cliente.class))).thenReturn(1);

        //Retorno do controle
        String result = controle.clienteExcluir(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteExcluir", result);
        assertEquals("Exclusão realizada com sucesso.", clienteFrm.getMensagem());        
        verify(servico, never()).alterar(any(Cliente.class));        
    }
    
    /**
     * Testa a exclusão de cliente inexistente.
     *
     */
    @Test
    void testClienteExcluirInexistente() {
        //Cliente a ser excluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("12345678900"); // CPF inválido
        clienteFrm.setMensagem(null);
        //Simula o cenário de exclusão de algum cliente com falha(0) no serviço
        when(servico.excluir(any(Cliente.class))).thenReturn(0);

        //Retorno do controle
        String resultado = controle.clienteExcluir(clienteFrm, model);

        //Retorno do controle
        assertEquals("ClienteExcluir", resultado);
        assertEquals("Exclusão não realizada.", clienteFrm.getMensagem()); 
        verify(model).addAttribute("cliente", clienteFrm);
    }
    
    /**
     * Testa o carregamento do formulário de consulta de cliente.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteConsultar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteConsultar"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("Consultar")));
    }
        
    /**
     * Testa o controle da consulta de cliente existente.
     *
     * @throws Exception
     */
    @Test
    void testClienteConsultarExistente() {
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("11111111111");
        clienteFrm.setMensagem(null);

        Cliente cliente = new Cliente(131, "Cliente 131", "11111111111");

        when(servico.getClientePeloId(131)).thenReturn(cliente);

        //Retorno do controle
        String resultado = controle.clienteConsultar(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteConsultar", resultado);
        assertEquals("Cliente encontrado.", clienteFrm.getMensagem());
        //Verifica se o método foi chamado 1 vez
        verify(servico, times(1)).getClientePeloId(131);
    }
    
    /**
     * Testa o controle da consulta de cliente inexistente.
     *
     * @throws Exception
     */
    @Test
    void testClienteConsultarInexistente() {
        //Cliente a ser incluído
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setClienteId(131);
        clienteFrm.setNome("Cliente 131");        
        clienteFrm.setCpf("11111111111");
        clienteFrm.setMensagem(null);

        Cliente cliente = new Cliente(132, "Cliente 132", "11111111111");

        when(servico.getClientePeloId(132)).thenReturn(cliente);

        //Retorno do controle
        String resultado = controle.clienteConsultar(clienteFrm, model);

        //Avalia os retornos
        assertEquals("ClienteConsultar", resultado);
        assertEquals("Cliente não encontrado.", clienteFrm.getMensagem());
        
        //Se o método nunca foi chamado
        verify(servico, never()).getClientePeloId(132);
    }
    
    /**
     * Testa o carregamento do formulário de listar clientes.
     *
     * @throws Exception
     */
    @Test
    void testFrmClienteListar() throws Exception {
        this.mockMvc.perform(get("/FrmClienteListar"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("Listar")));
    }
        
    /**
     * Testa o serviço getCliente existente.
     *
     * @throws Exception
     */
    @Test
    void testGetClienteExistente() throws Exception {        
        //Cliente a ser incluído
        Cliente cliente = new Cliente(132, "Cliente 132", "11111111111");
        dao.save(cliente);

        //Id do cliente a ser consultado
        Integer clienteId = 132;

        this.mockMvc.perform(get("/cliente/" + clienteId))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //Retorna um JSON 
                .andExpect(content().string(containsString("{\"clienteId\":132,\"nome\":\"Cliente 132\",\"cpf\":\"11111111111\"}")));
                
        //Exclui o cliente        
        dao.deleteById(clienteId);
    }
    
    /**
     * Testa o serviço getCliente inexistente.
     *
     * @throws Exception
     */
    @Test
    void testGetClienteInexistente() throws Exception {        
        //Id do cliente inexistente
        Integer clienteId = 133;

        this.mockMvc.perform(get("/cliente/" + clienteId))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200   
                .andExpect(content().string(containsString("")));
    }
    
    /**
     * Testa o serviço getLista.
     *
     * @throws Exception
     */
    @Test
    void testGetLista() throws Exception {        
        //Cliente a ser incluído
        Cliente cliente = new Cliente(131, "TesteGetLista", "11111111111");
        dao.save(cliente);

        //Id do cliente a ser excluído
        Integer clienteId = 131;

        this.mockMvc.perform(get("/clientes"))
                .andDo(print())
                .andExpect(status().isOk()) //API retorna o código 200                
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //Retorna um JSON                
                .andExpect(content().string(containsString("{\"clienteId\":131,\"nome\":\"TesteGetLista\",\"cpf\":\"11111111111\"}")));
                
        //Exclui o cliente        
        dao.deleteById(clienteId);
    }
}