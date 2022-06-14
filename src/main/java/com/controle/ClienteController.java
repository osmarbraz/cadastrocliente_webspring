package com.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class ClienteController {

    @Autowired
    private ClienteServico clienteServico;

    @RequestMapping("/")
    public String menu() {
        return "menu";
    }

    @PostMapping("/ClienteIncluir")
    public String ClienteIncluir(@ModelAttribute ClienteFrm clienteFrm, Model model) {
        model.addAttribute("cliente", clienteFrm);
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
    public String ClienteAltear(@ModelAttribute ClienteFrm clienteFrm, Model model) {
        model.addAttribute("cliente", clienteFrm);
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
    public String ClienteConsultar(@ModelAttribute ClienteFrm clienteFrm, Model model) {
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

        model.addAttribute("cliente", clienteFrm);
        return "ClienteConsultar";
    }

    @PostMapping("/ClienteExcluir")
    public String ClienteExcluir(@ModelAttribute ClienteFrm clienteFrm, Model model) {
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
    public String FrmClienteIncluir(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteIncluir";
    }

    @GetMapping("/FrmClienteAlterar")
    public String FrmClienteAlterar(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteAlterar";
    }

    @GetMapping("/FrmClienteExcluir")
    public String FrmClienteExcluir(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteExcluir";
    }

    @GetMapping("/FrmClienteConsultar")
    public String FrmClienteConsultar(Model model) {
        model.addAttribute("cliente", new ClienteFrm());
        return "FrmClienteConsultar";
    }

    @GetMapping("/FrmClienteListar")
    public String FrmClienteListar(Model model) {
        model.addAttribute("clientes", clienteServico.getLista());
        return "FrmClienteListar";
    }
}
