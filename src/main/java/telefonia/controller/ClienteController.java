package telefonia.controller;

import java.util.ArrayList;

import telefonia.model.bo.ClienteBO;
import telefonia.model.vo.Cliente;
import telefonia.model.vo.Endereco;
import telefonia.model.vo.Telefone;

public class ClienteController {

	private static final int TAMANHO_MINIMO_CAMPO_NOME = 1;
	private static final int TAMANHO_MAXIMO_CAMPO_NOME = 50;

	private static final int TAMANHO_MINIMO_CAMPO_SOBRENOME = 1;
	private static final int TAMANHO_MAXIMO_CAMPO_SOBRENOME = 100;

	private static final int TAMANHO_MINIMO_CAMPO_CPF = 11;
	private static final int TAMANHO_MAXIMO_CAMPO_CPF = 11;

	private ClienteBO bo = new ClienteBO();

	public String salvar(String nome, String sobrenome, String cpf, Endereco endereco) {

		String msg = "";
		
		if (endereco!= null) {
			
		verificarCampo("Nome", nome, TAMANHO_MINIMO_CAMPO_NOME, TAMANHO_MAXIMO_CAMPO_NOME, true);
		verificarCampo("Sobrenome", sobrenome, TAMANHO_MINIMO_CAMPO_SOBRENOME, TAMANHO_MAXIMO_CAMPO_SOBRENOME, true);
		verificarCampo("CPF", cpf, TAMANHO_MINIMO_CAMPO_CPF, TAMANHO_MAXIMO_CAMPO_CPF, true);
		
			
		}else {
			msg = "Informe um endereço.";
		}

		if (msg.isEmpty()) {
			Cliente cliente = new Cliente(nome, sobrenome, cpf, null, endereco);
			bo.salvar(cliente);
		}

		return msg;

	}

	private String verificarCampo(String nomeDoCampo, String valor, int tamanhoMinimo, int tamanhoMaximo,
			boolean obrigatorio) {
		String msgValidacao = "";

		if (obrigatorio && valor != null && !valor.isEmpty() || valor.length() < tamanhoMinimo
				|| valor.length() > tamanhoMaximo) {
			msgValidacao = nomeDoCampo + " deve possuir pelo menos " + tamanhoMinimo + " e no máximo " + tamanhoMaximo
					+ " caracteres \n";
		}
		return msgValidacao;
	}

	public ArrayList<Cliente> listarClientes() {
		return bo.consultarTodos();
	}

	public String excluirPorCpf(String cpf) {
		String msg = "";

		verificarCampo("CPF", cpf, TAMANHO_MINIMO_CAMPO_CPF, TAMANHO_MAXIMO_CAMPO_CPF, true);
		if (msg.isEmpty()) {
			bo.excluirPorCpf(cpf);
		}
		return msg;

	}

}
