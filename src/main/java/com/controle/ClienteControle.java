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
    
    public ClienteControle(ClienteServico clienteServico){
        this.clienteServico = clienteServico;
    }

    @GetMapping("/")
    public String menu() {
        return "menu";
    }

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
        return "ClienteIncluir";
    }

    @PostMapping("/ClienteAlterar")
    public String clienteAltear(@ModelAttribute ClienteFrm clienteFrm, Model model) {
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
        return "ClienteAlterar";
    }

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
        return "ClienteConsultar";
    }

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
        model.addAttribute("cliente", clienteFrm);
        return "ClienteExcluir";
    }

    @GetMapping("/FrmClienteIncluir")
    public String frmClienteIncluir(Model model) {
        model.addAttribute(CLIENTE, new ClienteFrm());
        return "FrmClienteIncluir";
    }

    @GetMapping("/FrmClienteAlterar")
    public String frmClienteAlterar(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteAlterar";
    }

    @GetMapping("/FrmClienteExcluir")
    public String frmClienteExcluir(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteExcluir";
    }

    @GetMapping("/FrmClienteConsultar")
    public String frmClienteConsultar(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteConsultar";
    }

    @GetMapping("/FrmClienteListar")
    public String frmClienteListar(Model model) {
        model.addAttribute("clientes", clienteServico.getLista());
        return "FrmClienteListar";
    }
}
