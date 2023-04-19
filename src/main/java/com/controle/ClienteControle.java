package com.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.util.Valida;
import static com.util.Util.nonNullCopyProperties;
import com.servico.ClienteServico;
import com.formulario.ClienteFrm;
import com.entidade.Cliente;

/**
 * Classe de controle de cliente.
 *
 * @author osmar
 */
@Controller
public class ClienteControle {

    private static final String CLIENTE = "cliente";

    @Autowired
    private final ClienteServico clienteServico;

    public ClienteControle(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }

    /**
     * Especifica a raiz("/") do projeto.
     *
     * @return Uma string com o caminho.
     */
    @GetMapping("/")
    public String menu() {
        //Carrega o arquivo menu.html do src/main/recourses/template
        return "menu";
    }

    /**
     * Mapeamento da inclusão de cliente.
     *
     * @param clienteFrm Objeto do formulário.
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @PostMapping("/ClienteIncluir")
    public String clienteIncluir(@ModelAttribute ClienteFrm clienteFrm, Model model) {
        model.addAttribute(CLIENTE, clienteFrm);
        Valida valida = new Valida();
        boolean cpfValido = valida.validaCPF(clienteFrm.getCpf());
        if (cpfValido) {
            //Instância um cliente para receber os dados do formulário
            Cliente cliente = new Cliente();
            //Copia os dados do formulário para a entidade
            nonNullCopyProperties(clienteFrm, cliente);
            if (clienteServico.inserir(cliente)) {
                clienteFrm.setMensagem("Inclusão realizada com sucesso.");
            } else {
                clienteFrm.setMensagem("Inclusão não realizada.");
            }
        } else {
            clienteFrm.setMensagem("CPF Inválido!");
        }
        //Carrega o arquivo ClienteIncluir.html do src/main/recourses/template
        return "ClienteIncluir";
    }

    /**
     * Mapeamento da alteração de cliente.
     *
     * @param clienteFrm Objeto do formulário.
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @PostMapping("/ClienteAlterar")
    public String clienteAlterar(@ModelAttribute ClienteFrm clienteFrm, Model model) {
        model.addAttribute(CLIENTE, clienteFrm);
        Valida valida = new Valida();
        boolean cpfValido = valida.validaCPF(clienteFrm.getCpf());
        if (cpfValido) {
            //Instância um cliente para receber os dados do formulário
            Cliente cliente = new Cliente();
            //Copia os dados do formulário para a entidade
            nonNullCopyProperties(clienteFrm, cliente);

            int resultado = clienteServico.alterar(cliente);
            if (resultado != 0) {
                clienteFrm.setMensagem("Alteração realizada com sucesso.");
            } else {
                clienteFrm.setMensagem("Alteração não realizada.");
            }
        } else {
            clienteFrm.setMensagem("CPF Inválido!");
        }
        //Carrega o arquivo ClienteAlterar.html do src/main/recourses/template
        return "ClienteAlterar";
    }

    /**
     * Mapeamento da consulta de cliente.
     *
     * @param clienteFrm Objeto do formulário.
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @PostMapping("/ClienteConsultar")
    public String clienteConsultar(@ModelAttribute ClienteFrm clienteFrm, Model model) {
        //Consulta o cliente
        Cliente cliente = clienteServico.getClientePeloId(clienteFrm.getClienteId());
        if (cliente != null) {
            //Copia os dados do formulário para a entidade
            nonNullCopyProperties(cliente, clienteFrm);
            clienteFrm.setMensagem("Cliente encontrado.");
        } else {
            clienteFrm.setMensagem("Cliente não encontrado.");
            clienteFrm.setClienteId(-1);
        }

        model.addAttribute(CLIENTE, clienteFrm);
        //Carrega o arquivo ClienteConsultar.html do src/main/recourses/template
        return "ClienteConsultar";
    }

    /**
     * Mapeamento da exclusão de cliente.
     *
     * @param clienteFrm Objeto do formulário.
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @PostMapping("/ClienteExcluir")
    public String clienteExcluir(@ModelAttribute ClienteFrm clienteFrm, Model model) {
        Cliente cliente = new Cliente();
        //Copia os dados do formulário para a entidade
        nonNullCopyProperties(clienteFrm, cliente);
        int resultado = clienteServico.excluir(cliente);
        if (resultado != 0) {
            clienteFrm.setMensagem("Exclusão realizada com sucesso.");
        } else {
            clienteFrm.setMensagem("Exclusão não realizada.");
        }
        model.addAttribute(CLIENTE, clienteFrm);
        //Carrega o arquivo ClienteExcluir.html do src/main/recourses/template
        return "ClienteExcluir";
    }

    /**
     * Mapeamento do caminho para o formulário de incluir cliente.
     *
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @GetMapping("/FrmClienteIncluir")
    public String frmClienteIncluir(Model model) {
        model.addAttribute(CLIENTE, new ClienteFrm());
        return "FrmClienteIncluir";
    }

    /**
     * Mapeamento do caminho para o formulário de alterar cliente.
     *
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @GetMapping("/FrmClienteAlterar")
    public String frmClienteAlterar(Model model) {
        model.addAttribute(CLIENTE, new ClienteFrm());
        return "FrmClienteAlterar";
    }

    /**
     * Mapeamento do caminho para o formulário de excluir cliente.
     *
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @GetMapping("/FrmClienteExcluir")
    public String frmClienteExcluir(Model model) {
        model.addAttribute(CLIENTE, new ClienteFrm());
        return "FrmClienteExcluir";
    }

    /**
     * Mapeamento do caminho para o formulário de consultar cliente.
     *
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @GetMapping("/FrmClienteConsultar")
    public String frmClienteConsultar(Model model) {
        model.addAttribute(CLIENTE, new ClienteFrm());
        return "FrmClienteConsultar";
    }

    /**
     * Mapeamento do caminho para o formulário de listar cliente.
     *
     * @param model Modelo utilizado pelo formulário.
     * @return Uma string com o caminho.
     */
    @GetMapping("/FrmClienteListar")
    public String frmClienteListar(Model model) {
        model.addAttribute("clientes", clienteServico.getLista());
        return "FrmClienteListar";
    }
}
